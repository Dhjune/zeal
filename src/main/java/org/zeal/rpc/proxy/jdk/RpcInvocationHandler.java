package org.zeal.rpc.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import org.zeal.rpc.proxy.RpcHandler;

/**
 * 客户端生成伪调用对象，---rpc客户端生成
 * @author jiqing
 * date:2015年10月30日 下午2:08:46
 */
public class RpcInvocationHandler implements RpcHandler,InvocationHandler{
		
	private Class clazz;
	
	public RpcInvocationHandler(){
		super();
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
		return null;
		
	}


	public Class getClazz() {
		return clazz;
	}

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}
	
	

}
