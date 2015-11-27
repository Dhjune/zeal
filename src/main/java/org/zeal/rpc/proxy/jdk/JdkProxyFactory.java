package org.zeal.rpc.proxy.jdk;

import java.lang.reflect.Proxy;

import org.zeal.rpc.proxy.proxyFactory;


public class JdkProxyFactory implements proxyFactory{
	
	

	public <T> T getProxy(Class<T> clazz) {
		RpcInvocationHandler handler = new  RpcInvocationHandler();
		
		T t = (T) Proxy.newProxyInstance(JdkProxyFactory.class.getClassLoader(), new Class[] {clazz}, handler);
		
		return t;
	}

}
