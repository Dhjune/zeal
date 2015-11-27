package org.zeal.rpc.common;

import java.util.concurrent.locks.ReentrantLock;

import javax.transaction.Transaction;
import javax.transaction.TransactionManager;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class LocalTransaction {
			
	private int count = 0;
		
	private ReentrantLock lock =  new ReentrantLock();
	
	private DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	
	private TransactionStatus status;
	
	private DataSourceTransactionManager txManager;
	
	public LocalTransaction(DataSourceTransactionManager txManager) {
		super();
		this.txManager = txManager;
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		status = txManager.getTransaction(def);
	}
	public void open(){
		try{
			lock.lock();
			count++;
		}catch(Exception e){			
		}finally {
			lock.unlock();
		}		
	}		
	
	public void reflesh(){
		status = txManager.getTransaction(def);
	}
	
	public void commit(boolean allcommit){
		try{
			lock.lock();
			count =0;
			if(count==0){				
				String txId = ZealTransactionManager.getLocalTrancId().get();
				if(txId!=null&&!txId.equals("")){
					//分布式事务，这个情况是没有分布式重入
					TransactionPool.pool.remove(txId);
				}else{
					//事务提交完毕之后，删除本地事务
					txManager.commit(status);
					ZealTransactionManager.getTransaction().set(null);
				}
				
			}
		}catch(Exception e){
			 //txManager.rollback(status);
		}finally {
			lock.unlock();
		}
	}
	
	public void commit(){
		try{
			lock.lock();
			count--;
			if(count==0){
				txManager.commit(status);
				 String txId = ZealTransactionManager.getLocalTrancId().get();
				if(txId!=null&&!txId.equals("")){
					//分布式事务，这个情况是没有分布式重入
					TransactionPool.pool.remove(txId);
				}else{
					//事务提交完毕之后，删除本地事务
					ZealTransactionManager.getTransaction().set(null);
				}
				
			}
		}catch(Exception e){
			 //txManager.rollback(status);
		}finally {
			lock.unlock();
		}
	}
	
	
}
