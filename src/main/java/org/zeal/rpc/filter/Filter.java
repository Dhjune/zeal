package org.zeal.rpc.filter;

import org.zeal.rpc.Invocation;
import org.zeal.rpc.Invoker;
import org.zeal.rpc.Result;
import org.zeal.rpc.RpcInvocation;


public interface Filter {
	public Result invoke(final Invoker<?> invoker, final RpcInvocation invocation);
}
