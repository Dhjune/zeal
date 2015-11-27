package org.zeal.rpc.common;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.zeal.rpc.filter.TranctionFilter;

public class ZealTransactionManager {
	
	private TransactionPool transactionPool;
	
	private TransactionManager transactionManager;
	
	private DataSource dataSource;
	
	public static ThreadLocal<Connection> localConn = new ThreadLocal<Connection>();
	
	private DataSourceTransactionManager dataSourceTransactionManager;
	
	private static ThreadLocal<LocalTransaction> transaction = new ThreadLocal<LocalTransaction>();
	
	
	private static ThreadLocal<String> localTrancId = new ThreadLocal<String>();
	
	public Connection  getConn(int type,boolean isReentrant){
		Connection  conn = null;
		try{
			conn = localConn.get();
			if(conn==null){
				conn =dataSource.getConnection();
				localConn.set(conn);
			}			
		}catch(SQLException e){			
		}
		return conn;				
	}
	
	public LocalTransaction getLocalTransaction(){
		
		LocalTransaction localTransaction = null;
		try {
			String trancId = TranctionFilter.getLocalTrancId();
			if(trancId!=null && !trancId.equals("")){
				
				if(transactionPool.pool.containsKey(trancId)){
					localTransaction = transactionPool.pool.get(trancId);
					return (LocalTransaction) transactionPool.pool.get(trancId);
				}else{
					localTransaction = new LocalTransaction(dataSourceTransactionManager);
					transactionPool.pool.put(trancId, localTransaction);
					return 	localTransaction;				
				}				
			}else{				
				if(transaction.get()!=null){
					return transaction.get();
				}else{
					localTransaction = new LocalTransaction(dataSourceTransactionManager);
					transaction.set(localTransaction);
					return 	localTransaction;
				}										
			}
		} catch (Exception e) {			
			return null;
		}
	}

	public TransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(TransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public TransactionPool getTransactionPool() {
		return transactionPool;
	}

	public void setTransactionPool(TransactionPool transactionPool) {
		this.transactionPool = transactionPool;
	}

	

	public DataSourceTransactionManager getDataSourceTransactionManager() {
		return dataSourceTransactionManager;
	}

	public void setDataSourceTransactionManager(DataSourceTransactionManager dataSourceTransactionManager) {
		this.dataSourceTransactionManager = dataSourceTransactionManager;
	}

	public static ThreadLocal<LocalTransaction> getTransaction() {
		return transaction;
	}

	public static void setTransaction(ThreadLocal<LocalTransaction> transaction) {
		ZealTransactionManager.transaction = transaction;
	}

	public static ThreadLocal<String> getLocalTrancId() {
		return localTrancId;
	}

	public static void setLocalTrancId(ThreadLocal<String> localTrancId) {
		ZealTransactionManager.localTrancId = localTrancId;
	}

}
