package org.zeal.rpc.filter;
import org.zeal.rpc.Invoker;
import org.zeal.rpc.Result;
import org.zeal.rpc.RpcInvocation;
import org.zeal.rpc.common.TransactionState;
import org.zeal.rpc.common.ZealTransactionManager;
import org.zeal.rpc.registry.support.ZookClient;
import org.zeal.rpc.registry.support.ZookClientFactory;
import org.zeal.rpc.registry.zookeeper.TransactionWatcher;

import com.alibaba.fastjson.JSON;

public class TranctionFilter implements Filter{
	
	private ZookClient zookClient;
	
	private TransactionWatcher watcher;
	
	private Invoker nextInvoker;
	
	private Filter nextFilter;
			
	public TranctionFilter(){
		
		zookClient = ZookClientFactory.getZookClient();
		
	}

	public Result invoke(Invoker<?> invoker, RpcInvocation invocation) {
		String tId = invocation.getTrancId();
//		TransactionState state =null;
//		ThreadLocal<String> localTrancId = ZealTransactionManager.getLocalTrancId();
//		String path  = "";
//		if(tId!=null && !tId.equals("")){
//			localTrancId.set(tId);
//			path += "/"+tId;
//			byte[] data = zookClient.getData(path);
//			if(data!=null&&data.length>0){
//				state = JSON.parseObject(new String(data), TransactionState.class);
//				state.setTotal_txnum(state.getTotal_txnum()+1);
//				state.setVersion(state.getVersion()+1);
//				zookClient.setData(path, JSON.toJSONString(state).getBytes(), state.getVersion());
//			}else{
//				state = new TransactionState();
//				zookClient.create(path,JSON.toJSONString(state).getBytes(), true);
//			}
//			zookClient.addPathWatcher(path, watcher);
//		}
		inTransac(tId);
		return  invoker.invoke(invocation);
	}
	
	private void inTransac(String tId){
		TransactionState state =null;
		ThreadLocal<String> localTrancId = ZealTransactionManager.getLocalTrancId();
		String path  = "";
		if(tId!=null && !tId.equals("")){
			localTrancId.set(tId);
			path += "/"+tId;
			byte[] data = zookClient.getData(path);
			if(data!=null&&data.length>0){
				state = JSON.parseObject(new String(data), TransactionState.class);
				state.setTotal_txnum(state.getTotal_txnum()+1);
				state.setVersion(state.getVersion()+1);
				zookClient.setData(path, JSON.toJSONString(state).getBytes(), state.getVersion());
			}else{
				state = new TransactionState();
				zookClient.create(path,JSON.toJSONString(state).getBytes(), true);
			}
			zookClient.addPathWatcher(path, watcher);
		}
	}
	
	public static String getLocalTrancId(){
		ThreadLocal<String> localTrancId = ZealTransactionManager.getLocalTrancId();
		return localTrancId.get();
	}

	public ZookClient getZookClient() {
		return zookClient;
	}

	public void setZookClient(ZookClient zookClient) {
		this.zookClient = zookClient;
	}

	public Invoker getNextInvoker() {
		return nextInvoker;
	}

	public void setNextInvoker(Invoker nextInvoker) {
		this.nextInvoker = nextInvoker;
	}

	public Filter getNextFilter() {
		return nextFilter;
	}

	public void setNextFilter(Filter nextFilter) {
		this.nextFilter = nextFilter;
	}

}
