package org.zeal.rpc.core;

import javax.transaction.Transaction;
import javax.transaction.TransactionManager;

import org.w3c.dom.views.AbstractView;
import org.zeal.rpc.common.LocalTransaction;
import org.zeal.rpc.common.TransactionPool;
import org.zeal.rpc.common.ZealTransactionManager;
import org.zeal.rpc.filter.TranctionFilter;

public abstract class AbstractRpcTranctional implements RpcTranctional{
	
	private ZealTransactionManager transactionManager;
	
	public LocalTransaction getLocalTransaction(){
		
		 return transactionManager.getLocalTransaction();					
	}
			
	public ZealTransactionManager getTransactionManager() {
		
		return transactionManager;
		
	}

	public void setTransactionManager(ZealTransactionManager transactionManager) {
		
		this.transactionManager = transactionManager;
	}
}
