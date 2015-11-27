package org.zeal.rpc.registry.zookeeper;

import java.net.URL;
import org.zeal.rpc.registry.AbstractRegistry;
import org.zeal.rpc.registry.support.ZookClient;

public class ZookeeperRegistry extends AbstractRegistry{
		
	private ZookClient zookClient;

	public ZookeeperRegistry(URL url, ZookClient zookClient) {
		super(url);
		this.setZookClient(zookClient);		
	}

	public ZookClient getZookClient() {
		return zookClient;
	}

	public void setZookClient(ZookClient zookClient) {
		this.zookClient = zookClient;
	}

}
