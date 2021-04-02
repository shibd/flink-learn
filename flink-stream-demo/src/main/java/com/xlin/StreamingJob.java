/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xlin;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.runtime.state.FunctionInitializationContext;
import org.apache.flink.runtime.state.FunctionSnapshotContext;
import org.apache.flink.runtime.state.memory.MemoryStateBackend;
import org.apache.flink.streaming.api.checkpoint.CheckpointedFunction;
import org.apache.flink.streaming.api.checkpoint.ListCheckpointed;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.streaming.connectors.kafka.internals.KafkaTopicPartition;
import org.apache.flink.util.Collector;
import scala.Int;

/**
 * Skeleton for a Flink Streaming Job.
 *
 * <p>For a tutorial how to write a Flink streaming application, check the
 * tutorials and examples on the <a href="http://flink.apache.org/docs/stable/">Flink Website</a>.
 *
 * <p>To package your application into a JAR file for execution, run
 * 'mvn clean package' on the command line.
 *
 * <p>If you change the name of the main class (with the public static void main(String[] args))
 * method, change the respective entry in the POM.xml file (simply search for 'mainClass').
 */
public class StreamingJob {

	public static void main(String[] args) throws Exception {
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//		env.setStateBackend(new MemoryStateBackend());
//		env.enableCheckpointing(50000);
		env.setParallelism(10);

		env.setRestartStrategy(RestartStrategies.fixedDelayRestart(1, Time.seconds(10)));
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "10.116.18.51:9092");
		props.setProperty("group.id", "flink-group");

		//数据源配置，是一个kafka消息的消费者
		FlinkKafkaConsumer011<String> consumer =
				new FlinkKafkaConsumer011<>("topic001", new SimpleStringSchema(), props);

		consumer.setStartFromLatest();
		env.addSource(consumer)
				.flatMap(new MyFunction())
				.keyBy(0)
				.sum(1)
				.print();

		env.execute("Flink-Kafka demo");
	}

	public static class MyFunction implements FlatMapFunction<String, Tuple2<String, Integer>>, ListCheckpointed<Integer> {
		int total;

		@Override
		public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
			if (s.equals("error")) {
				throw new Exception();
			}
			total++;
			collector.collect(Tuple2.of(s, 1));
		}

		@Override
		public List<Integer> snapshotState(long l, long l1) throws Exception {
			System.out.println("snapshotState...");
			return Collections.singletonList(total);
		}

		// 发生故障时
		@Override
		public void restoreState(List<Integer> list) throws Exception {
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < list.size(); i++) {
				s.append("idx:<").append(i).append(">  val:<").append(list.get(0)).append(">");
			}
			System.out.println("restoreState...  " + s);
			for (Integer integer : list) {
				total += integer;
			}
			System.out.println("恢复到的状态total为：" + total);
		}
	}
}
