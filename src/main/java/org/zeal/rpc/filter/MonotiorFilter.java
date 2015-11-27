package org.zeal.rpc.filter;

import org.zeal.rpc.Invoker;
import org.zeal.rpc.Result;
import org.zeal.rpc.RpcInvocation;
/**
 * 计数拦截器，拦截链，执行链初始化，交由zeal初始化完成，用户可自定义。
 * @author jiqing
 * date:2015年10月30日 上午11:48:17
 */
public class MonotiorFilter implements Filter{
			
	public Result invoke(Invoker<?> invoker, RpcInvocation invocation) {
		
		return invoker.invoke(invocation);
		
	}

}
