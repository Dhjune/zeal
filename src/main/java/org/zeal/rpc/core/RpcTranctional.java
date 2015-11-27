package org.zeal.rpc.core;

import javax.transaction.Transaction;

public interface RpcTranctional {
		
	public void commit(Transaction trancsation);

}
