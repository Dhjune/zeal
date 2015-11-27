package org.zeal.rpc.registry.support;

import java.util.concurrent.locks.ReentrantLock;

public class ZookClientFactory {
	
	private static ZookClient zookClient = null;
	
	private static String zookUrl;
	
	private static ReentrantLock lock =  new ReentrantLock();
	
	public static ZookClient getZookclient() {
		if(zookClient==null){
			getInstance();
		}
		return zookClient;
	}
	
	public static void getInstance(){
		try{
			lock.lock();
			zookClient =  new ZookClient(zookUrl);			
		}catch(Exception e){
			
		}finally{
			lock.unlock();
		}
		
	}

	public static ZookClient getZookClient() {
		return zookClient;
	}

	public static void setZookClient(ZookClient zookClient) {
		ZookClientFactory.zookClient = zookClient;
	}

	public static String getZookUrl() {
		return zookUrl;
	}

	public static void setZookUrl(String zookUrl) {
		ZookClientFactory.zookUrl = zookUrl;
	}
	
	
	
}
