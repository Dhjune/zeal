package org.zeal.rpc.invoker;

import org.zeal.rpc.Invocation;
import org.zeal.rpc.Invoker;
import org.zeal.rpc.Result;
import org.zeal.rpc.RpcInvocation;
import org.zeal.rpc.filter.Filter;


public class FutureInvoker implements Invoker{
	
	private Filter nextFilter;
	
	private Invoker nextInvoker;
	
	public Class getInterface() {
		
		return null;
	}

	public Result invoke(RpcInvocation invocation) {
		
		return  nextFilter.invoke(nextInvoker, invocation);		
	}

}
