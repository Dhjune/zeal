package org.zeal.rpc.registry;

import java.net.URL;

public abstract class AbstractRegistry implements Registry{
	
	private  URL registryUrl;
	
	
	public AbstractRegistry(URL url){
		this.registryUrl = url;
	}

	public URL getRegistryUrl() {
		return registryUrl;
	}

	public void setRegistryUrl(URL registryUrl) {
		this.registryUrl = registryUrl;
	}
	
	

}
