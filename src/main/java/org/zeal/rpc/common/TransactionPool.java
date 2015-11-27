package org.zeal.rpc.common;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.transaction.Transaction;

public class TransactionPool {
	
	public static final ConcurrentHashMap<String,LocalTransaction> pool = new ConcurrentHashMap<String, LocalTransaction>();
	
	public static LocalTransaction getTransaction(String trancId){
		
		return pool.get(trancId);
		
	}
	
	public static void setTransaction(String trancId,LocalTransaction transaction){
		if(exits(trancId)){
			LocalTransaction transac =  pool.get(trancId);			
		}
		pool.put(trancId, transaction);
	}
	
	private static boolean exits(String trancId){
		
		return pool.containsKey(trancId);
		
		
	}

}
