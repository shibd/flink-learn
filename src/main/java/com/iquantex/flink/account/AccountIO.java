package com.iquantex.flink.account;

import com.iquantex.flink.account.protobuf.AccountRequest;
import com.iquantex.flink.account.protobuf.AccountResponse;
import org.apache.flink.statefun.sdk.io.EgressIdentifier;
import org.apache.flink.statefun.sdk.io.EgressSpec;
import org.apache.flink.statefun.sdk.io.IngressIdentifier;
import org.apache.flink.statefun.sdk.io.IngressSpec;
import org.apache.flink.statefun.sdk.kafka.KafkaEgressBuilder;
import org.apache.flink.statefun.sdk.kafka.KafkaEgressSerializer;
import org.apache.flink.statefun.sdk.kafka.KafkaIngressBuilder;
import org.apache.flink.statefun.sdk.kafka.KafkaIngressDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public final class AccountIO {

	static final IngressIdentifier<AccountRequest> ACCOUNT_INGRESS_ID =
			new IngressIdentifier<>(AccountRequest.class, "apache", "greet-ingress");

	static final EgressIdentifier<AccountResponse> ACCOUNT_EGRESS_ID =
			new EgressIdentifier<>("apache", "greet-ingress", AccountResponse.class);

	private final String kafkaAddress;


	public AccountIO(String kafkaAddress) {
		this.kafkaAddress = Objects.requireNonNull(kafkaAddress);
	}

	IngressSpec<AccountRequest> getIngressSpec() {
		return KafkaIngressBuilder.forIdentifier(ACCOUNT_INGRESS_ID)
				.withKafkaAddress(kafkaAddress)
				.withTopic("names")
				.withDeserializer(AccountKafkaDeserializer.class)
				.withProperty(ConsumerConfig.GROUP_ID_CONFIG, "greetings")
				.build();
	}

	EgressSpec<AccountResponse> getEgressSpec() {
		return KafkaEgressBuilder.forIdentifier(ACCOUNT_EGRESS_ID)
				.withKafkaAddress(kafkaAddress)
				.withSerializer(AccountIO.AccountKafkaSerializer.class)
				.build();
	}

	private static final class AccountKafkaDeserializer
			implements KafkaIngressDeserializer<AccountRequest> {

		private static final long serialVersionUID = 1L;

		@Override
		public AccountRequest deserialize(ConsumerRecord<byte[], byte[]> input) {
			String accountCode = new String(input.key(), StandardCharsets.UTF_8);
			String amt = new String(input.value(), StandardCharsets.UTF_8);

			return AccountRequest.newBuilder().setAccountCode(accountCode).setAmt(Integer.parseInt(amt)).build();
		}
	}

	private static final class AccountKafkaSerializer implements KafkaEgressSerializer<AccountResponse> {

		private static final long serialVersionUID = 1L;

		@Override
		public ProducerRecord<byte[], byte[]> serialize(AccountResponse response) {
			byte[] value = response.getRetMsg().getBytes(StandardCharsets.UTF_8);

			return new ProducerRecord<>("greetings", value);
		}
	}
}
