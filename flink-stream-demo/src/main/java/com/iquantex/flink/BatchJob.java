package com.iquantex.flink;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class BatchJob {

	public static void main(String[] args) throws Exception {
		// 创建批处理环境
		final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

		// 从文件中读取数据
		DataSet<String> dataSet = env.readTextFile("./flink-stream-demo/hello.txt");

		DataSet<Tuple2<String, Integer>> result = dataSet
				// 将数据包装成一个二元组
				.flatMap(new MyFunction())
				// 使用二元组的第一个参数分组
				.groupBy(0)
				// 对二元组的第二个参数求和
				.sum(1);

		// 打印到控制台
		result.print();
	}

	public static class MyFunction implements FlatMapFunction<String, Tuple2<String, Integer>> {

		@Override
		public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
			collector.collect(Tuple2.of(s, 1));
		}
	}
}
