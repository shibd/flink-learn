package io.streamnative.examples.flink;

import org.apache.flink.streaming.connectors.pulsar.config.RecordSchemaType;
import org.apache.flink.streaming.connectors.pulsar.internal.JsonSer;
import org.apache.flink.streaming.util.serialization.PulsarSerializationSchema;
import org.apache.flink.streaming.util.serialization.PulsarSerializationSchemaWrapper;

import java.util.Optional;

/**
 * @author baozi
 */
public class Test {
    public static void main(String[] args) {
        PulsarSerializationSchema<PulsarStreamingWordCount.WordCount> pulsarSerialization = new PulsarSerializationSchemaWrapper.Builder<>(JsonSer.of(PulsarStreamingWordCount.WordCount.class))
                .usePojoMode(PulsarStreamingWordCount.WordCount.class, RecordSchemaType.JSON)
                .setTopicExtractor(person -> null)
                .setDeliverAtExtractor(wordCount -> {
                    // set world == "flink" delay 10s.
                    if (wordCount.getWord().equals("flink")) {
                        return Optional.of(System.currentTimeMillis() + 10000L);
                    }
                    return Optional.empty();
                })
                .build();
        System.out.println(pulsarSerialization.getSchema());
    }
}
