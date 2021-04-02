package com.iquantex.flink.account;

import com.iquantex.flink.account.protobuf.AccountRequest;
import org.apache.flink.statefun.sdk.io.Router;


public final class AccountRouter implements Router<AccountRequest> {
	@Override
	public void route(AccountRequest message, Downstream<AccountRequest> downstream) {
		downstream.forward(AccountStatefulFunction.ACCOUNT_TYPE, message.getAccountCode(), message);
	}
}
