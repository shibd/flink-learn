package io.streamnative.examples.flink;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

import java.util.concurrent.TimeUnit;

/**
 * @author baozi
 */

public class PulsarProducerExample {

    public static void main(String[] args) throws PulsarClientException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://localhost:6650")
                .build();

        Producer<byte[]> producer = client.newProducer().topic("wordcount_input").create();

        for (int i = 0; i < 10; i++) {
            producer.newMessage().deliverAfter(1L, TimeUnit.MINUTES).value("hello world baozi".getBytes()).send();
        }
    }
}
