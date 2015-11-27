package org.zeal.rpc.proxy;



public interface proxyFactory {
	<T> T getProxy(Class<T> clazz) ;
	
	
}
