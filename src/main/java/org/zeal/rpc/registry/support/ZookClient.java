package org.zeal.rpc.registry.support;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZookClient implements BaseClient{
	
	private ZooKeeper zooKeeper;
	
	private String zooKeeperUrl;
	
	public ZookClient(String url){
		try {
			zooKeeperUrl = url;
			zooKeeper = new ZooKeeper(url,5000,null);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
	}
	
	public void create(String path,byte[] data,boolean ephemeral){
		int i = path.lastIndexOf('/');
		if (i > 0) {
			create(path.substring(0, i),data, false);
		}
		if(ephemeral) createEphemeral(path,data);
		else createPersistent(path,data);
	}
	
	
	public void delete(String path){
		try {
			zooKeeper.delete(path, -1);
		} catch (InterruptedException e) {
			
		} catch (KeeperException e) {
						
		}
	}
	
	public Stat exits(String path){
		Stat stat = null;
		try {
			stat =  zooKeeper.exists(path, false);
		} catch (KeeperException e) {
			
		} catch (InterruptedException e) {
			
		}
		return stat;
	}
	
	
	public List<String> getChildren(String path){
		List<String> childs = null;
		 try {
			 childs = zooKeeper.getChildren(path, false);
		} catch (KeeperException e) {
			
		} catch (InterruptedException e) {
			
		}
		 return childs;
	}
	
	public byte[] getData(String path){
		byte[] data= null;
		try {
			data = zooKeeper.getData(path, false, null);
		} catch (KeeperException e) {
			
		} catch (InterruptedException e) {
			
		}
		return data;
	}
	
	public void setData(String path,byte[] data,int version){
		try {
			zooKeeper.setData(path, data, version);
			
		} catch (KeeperException e) {			
			e.printStackTrace();
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
	}
	
	
	public void addWatcher(Watcher watcher){
		
		zooKeeper.register(watcher);
		
	}
	
	public void addPathWatcher(String path,Watcher watcher){
		try {
			
			zooKeeper.exists(path, watcher);
			
		} catch (KeeperException e) {
			
			e.printStackTrace();
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
			
		}
	}
		
	public void createPersistent(String path,byte[] bytes){
		try {
			zooKeeper.create(path, null, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		} catch (KeeperException e) {
		
		} catch (InterruptedException e) {
			
		}
	}
	
	public void createEphemeral(String path,byte[] bytes){
		try {
			zooKeeper.create(path, bytes, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		} catch (KeeperException e) {
			
		} catch (InterruptedException e) {
			
		}
	}
	

	public ZooKeeper getZooKeeper() {
		return zooKeeper;
	}

	public void setZooKeeper(ZooKeeper zooKeeper) {
		this.zooKeeper = zooKeeper;
	}

	public String getZooKeeperUrl() {
		return zooKeeperUrl;
	}

	public void setZooKeeperUrl(String zooKeeperUrl) {
		this.zooKeeperUrl = zooKeeperUrl;
	}

}
