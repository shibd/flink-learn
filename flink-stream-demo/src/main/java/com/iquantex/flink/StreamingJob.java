package com.iquantex.flink;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.runtime.state.memory.MemoryStateBackend;
import org.apache.flink.streaming.api.checkpoint.ListCheckpointed;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.util.Collector;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class StreamingJob {

	public static void main(String[] args) throws Exception {
		// 创建流处理环境
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		// 设置快照持久化方式，这里使用内存持久化
		env.setStateBackend(new MemoryStateBackend());
		// 设置触发checkpoint时间间隔为500ms一次
		env.enableCheckpointing(500);
		// 设置并行度
		env.setParallelism(10);

		// 设置任务异常后，flink的重启策略，这里设置为只尝试重启一次，每次间隔10s尝试重启一次
		env.setRestartStrategy(RestartStrategies.fixedDelayRestart(1, Time.seconds(10)));
		// 设置kafka的ip port
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "10.116.18.51:9092");
		props.setProperty("group.id", "flink-group");

		// 数据源配置，是一个kafka消息的消费者
		FlinkKafkaConsumer011<String> consumer =
				new FlinkKafkaConsumer011<>("topic001", new SimpleStringSchema(), props);

		// 设置kafka每次从最新位置消费
		consumer.setStartFromLatest();
		env.addSource(consumer)
				// 设置
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
			// 当消费的消息为'error'，则抛异常。为了测试flink重启策略
			if (s.equals("error")) {
				throw new Exception();
			}
			// 记录算子消费了多少数据
			total++;
			collector.collect(Tuple2.of(s, 1));
		}

		@Override
		public List<Integer> snapshotState(long l, long l1) throws Exception {
			// 触发checkpoint时，会调用这个方法
			System.out.println("snapshotState...");
			return Collections.singletonList(total);
		}

		@Override
		public void restoreState(List<Integer> list) throws Exception {
			// flink恢复数据时，会调用这个方法
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
