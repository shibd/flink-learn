package com.iquantex.flink.account;

import org.apache.flink.statefun.sdk.spi.StatefulFunctionModule;

import java.util.Map;

public class AccountModule implements StatefulFunctionModule {
	private static final String KAFKA_KEY = "kafka-address";

	private static final String DEFAULT_KAFKA_ADDRESS = "kafka-broker:9092";


	@Override
	public void configure(Map<String, String> globalConfiguration, Binder binder) {
		String kafkaAddress = globalConfiguration.getOrDefault(KAFKA_KEY, DEFAULT_KAFKA_ADDRESS);
		AccountIO accountIO = new AccountIO(kafkaAddress);

		binder.bindIngress(accountIO.getIngressSpec());
		binder.bindIngressRouter(AccountIO.ACCOUNT_INGRESS_ID, new AccountRouter());

		binder.bindEgress(accountIO.getEgressSpec());

		binder.bindFunctionProvider(AccountStatefulFunction.ACCOUNT_TYPE, unused -> new AccountStatefulFunction());
	}
}
