package com.iquantex.flink;

import org.apache.flink.api.common.JobExecutionResult;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamUtils;
import org.apache.flink.streaming.api.datastream.IterativeStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author baozi
 */
public class DataStreamAPILearn {

    public static void main(String[] args) throws Exception {
        learn1();
    }

    /**
     * dataStream debug
     * @throws Exception
     */
    private static void learn3() throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();


        DataStream<Tuple2<String, Integer>> myResult = env.fromElements(Tuple2.of("a", 1), Tuple2.of("b", 1),  Tuple2.of("c", 1));
        Iterator<Tuple2<String, Integer>> myOutput = DataStreamUtils.collect(myResult);
        while (myOutput.hasNext()) {
            Tuple2<String, Integer> next = myOutput.next();
            System.out.println(next);
        }
    }

    /**
     * dataStream  iterate
     * @throws Exception
     */
    private static void learn2() throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();
        DataStream<Long> someIntegers = env.generateSequence(10, 10);

        IterativeStream<Long> iteration = someIntegers.iterate();

        DataStream<Long> minusOne = iteration.map(new MapFunction<Long, Long>() {
            @Override
            public Long map(Long value) throws Exception {
                System.out.println(value);
                return value - 2;
            }
        });

        DataStream<Long> stillGreaterThanZero = minusOne.filter(new FilterFunction<Long>() {
            @Override
            public boolean filter(Long value) throws Exception {
                return (value > 0);
            }
        });
        iteration.closeWith(stillGreaterThanZero);


        DataStream<Long> lessThanZero = minusOne.filter(new FilterFunction<Long>() {
            @Override
            public boolean filter(Long value) throws Exception {
                return (value <= 0);
            }
        });

        lessThanZero.print();
        env.execute();
    }

    /**
     * data stream Operators
     * @throws Exception
     */
    private static void learn1() throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        DataStream<String> input = env.fromCollection(Arrays.asList("a 1 2", "a 2 1", "a 3 4", "a 5 3", "b 4 3", "b 1 4", "b 2 1"));
        input.flatMap(new FlatMapFunction<String, Tuple3<String, Integer, Integer>>() {
            @Override
            public void flatMap(String s, Collector<Tuple3<String, Integer, Integer>> collector) throws Exception {
                String[] s1 = s.split(" ");
                collector.collect(Tuple3.of(s1[0], Integer.valueOf(s1[1]), Integer.valueOf(s1[2])));
            }
        }).keyBy(0).window(TumblingEventTimeWindows.of(Time.seconds(5))).reduce(new ReduceFunction<Tuple3<String, Integer, Integer>>() {
            @Override
            public Tuple3<String, Integer, Integer> reduce(Tuple3<String, Integer, Integer> value1, Tuple3<String, Integer, Integer> value2) throws Exception {
                return Tuple3.of(value1.f0, value1.f1 + value2.f1, value2.f2);
            }
        }).print();

        JobExecutionResult test = env.execute("test");
    }
}
