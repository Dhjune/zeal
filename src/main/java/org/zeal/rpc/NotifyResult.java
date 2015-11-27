package org.zeal.rpc;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transaction;

public class NotifyResult implements Result {

	private Object result;

	private Throwable exception;

	private Map<String, String> attachments = new HashMap<String, String>();
		
	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Throwable getException() {
		return exception;
	}

	public void setException(Throwable exception) {
		this.exception = exception;
	}

	public Map<String, String> getAttachments() {
		return attachments;
	}

	public void setAttachments(Map<String, String> attachments) {
		this.attachments = attachments;
	}

}
