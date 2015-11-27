package org.zeal.rpc.registry.zookeeper;

import java.util.concurrent.locks.ReentrantLock;

import org.zeal.rpc.registry.AbstractRegistryFactory;

public class ZookeeperRegistryFactory extends AbstractRegistryFactory{
	
	
    private static final ReentrantLock LOCK = new ReentrantLock();
	
}
