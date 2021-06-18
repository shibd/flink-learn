/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.streamnative.examples.flink;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.Optional;
import java.util.Properties;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.pulsar.FlinkPulsarSink;
import org.apache.flink.streaming.connectors.pulsar.FlinkPulsarSource;
import org.apache.flink.streaming.connectors.pulsar.config.RecordSchemaType;
import org.apache.flink.streaming.connectors.pulsar.internal.JsonSer;
import org.apache.flink.streaming.util.serialization.PulsarSerializationSchema;
import org.apache.flink.streaming.util.serialization.PulsarSerializationSchemaWrapper;

/**
 * A Flink streaming job that does word counting.
 */
public class PulsarStreamingWordCount {

    public static void main(String[] args) throws Exception {

        // Set up the streaming execution environment
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.getConfig().setRestartStrategy(RestartStrategies.fixedDelayRestart(4, 10000));
        env.enableCheckpointing(5000);
        env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime);

        String brokerServiceUrl = "pulsar://localhost:6650";
        String adminServiceUrl = "http://localhost:8080";
        String inputTopic = "wordcount_input";
        String outputTopic = "wordcount_output";
        int parallelism = 1;
        Properties properties = new Properties();
        properties.setProperty("topic", inputTopic);

        FlinkPulsarSource<String> source = new FlinkPulsarSource<>(
                brokerServiceUrl,
                adminServiceUrl,
                new SimpleStringSchema(),
                properties
        ).setStartFromEarliest();

        DataStream<String> stream = env.addSource(source);
        DataStream<WordCount> wc = stream
                .flatMap((FlatMapFunction<String, WordCount>) (line, collector) -> {
                    for (String word : line.split("\\s")) {
                        collector.collect(new WordCount(word, 1));
                    }
                })
                .returns(WordCount.class)
                .keyBy((KeySelector<WordCount, String>) wordCount -> wordCount.getWord())
                .timeWindow(Time.seconds(5))
                .reduce((ReduceFunction<WordCount>) (c1, c2) ->
                        new WordCount(c1.word, c1.count + c2.count));

        if (null != outputTopic) {
            PulsarSerializationSchema<WordCount> pulsarSerialization = new PulsarSerializationSchemaWrapper.Builder<>(JsonSer.of(WordCount.class))
                    .usePojoMode(WordCount.class, RecordSchemaType.JSON)
                    .setTopicExtractor(person -> null)
                    .setDeliverAtExtractor(wordCount -> {
                        // set world == "flink" delay 10s.
                        if (wordCount.getWord().equals("flink")) {
                            return Optional.of(System.currentTimeMillis() + 10000L);
                        }
                        return Optional.empty();
                    })
                    .build();
            wc.addSink(new FlinkPulsarSink<>(
                    brokerServiceUrl,
                    adminServiceUrl,
                    Optional.of(outputTopic),
                    new Properties(),
                    pulsarSerialization
                    )
            );
        } else {
            wc.print().setParallelism(parallelism);
        }

        env.execute("Pulsar Streaming WordCount");

    }

    /**
     * Data type for words with count.
     */
    public static class WordCount {

        public String word;
        public long count;

        public WordCount() {
        }

        public WordCount(String word, long count) {
            this.word = word;
            this.count = count;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public long getCount() {
            return count;
        }

        public void setCount(long count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return "WordCount{" +
                    "word='" + word + '\'' +
                    ", count=" + count +
                    '}';
        }
    }

}
