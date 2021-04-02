package com.iquantex.flink.account;

import com.iquantex.flink.account.protobuf.AccountRequest;
import com.iquantex.flink.account.protobuf.AccountResponse;
import org.apache.flink.statefun.sdk.Context;
import org.apache.flink.statefun.sdk.FunctionType;
import org.apache.flink.statefun.sdk.StatefulFunction;
import org.apache.flink.statefun.sdk.annotations.Persisted;
import org.apache.flink.statefun.sdk.state.PersistedValue;

/**
 * @author szj
 * @date 2021/3/31 17:56
 */
public class AccountStatefulFunction implements StatefulFunction {

	public static final FunctionType ACCOUNT_TYPE = new FunctionType("namespace", "account");

	@Persisted
	public final PersistedValue<Integer> accountAmt = PersistedValue.of("amt", Integer.class);

	@Override
	public void invoke(Context context, Object input) {
		AccountRequest accountRequest = (AccountRequest) input;
		Integer currentAmt = accountAmt.getOrDefault(1000);
		accountAmt.set(currentAmt + accountRequest.getAmt());
		AccountResponse accountResponse = AccountResponse.newBuilder().setRetMsg(String.format("account <%s>: amt<%s> --> <%s>.", accountRequest.getAccountCode(), currentAmt, accountAmt.get())).build();
		context.send(AccountIO.ACCOUNT_EGRESS_ID, accountResponse);
	}
}
