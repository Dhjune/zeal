package org.zeal.rpc;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;


public class RpcInvocation implements Serializable {

	private static final long serialVersionUID = 1L;

	private String methodName;

	private Class<?>[] parameterTypes;

	private Object[] arguments;
	
	private String trancId;

	private Map<String, String> attachments;

	private transient Invoker<?> invoker;

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Class<?>[] getParameterTypes() {
		return parameterTypes;
	}

	public void setParameterTypes(Class<?>[] parameterTypes) {
		this.parameterTypes = parameterTypes;
	}

	public Object[] getArguments() {
		return arguments;
	}

	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}

	public Map<String, String> getAttachments() {
		return attachments;
	}

	public void setAttachments(Map<String, String> attachments) {
		this.attachments = attachments;
	}

	public Invoker<?> getInvoker() {
		return invoker;
	}

	public void setInvoker(Invoker<?> invoker) {
		this.invoker = invoker;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTrancId() {
		return trancId;
	}

	public void setTrancId(String trancId) {
		this.trancId = trancId;
	}
	
	

}
