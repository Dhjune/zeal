package org.zeal.rpc.registry.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.zeal.rpc.common.LocalTransaction;
import org.zeal.rpc.common.TransactionPool;
import org.zeal.rpc.common.TransactionState;

import com.alibaba.fastjson.JSON;
/**
 * zookeeper 用于分布式事务节点的处理
 * @author jiqing
 * date:2015年10月29日 下午4:06:21
 */
public class TransactionWatcher implements Watcher{

	public void process(WatchedEvent event) {
		;
		ZooKeeper zooKeeper =null;
		try {
			byte[] bytes =  zooKeeper.getData(event.getPath(), false, null);
			String str = new String(bytes);
			TransactionState state = JSON.parseObject(str, TransactionState.class);
			if(state.getTotal_txnum()>0){
				if(state.getReal_txnum()>0&&state.getReal_txnum()<=state.getTotal_txnum()){
					LocalTransaction transaction =  TransactionPool.pool.get(state.getTrancNo());
					transaction.commit();
				}
			}
			if(state.getReal_txnum()>0){
				
			}
			state.addVersion();
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
