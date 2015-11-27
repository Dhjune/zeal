package org.zeal.rpc.common;
/**
 * 分布式集群事务声明
 * @author jiqing
 * date:2015年10月29日 下午5:48:43
 */
public class TransactionState {
	/**
	 * 事务编号
	 */
	private String trancNo;
	/**
	 * 分布式事务数
	 */
	private int total_txnum;
	/**
	 * 预提交事务数
	 */
	private int pre_txnum;
	/**
	 * 提交事务数
	 */
	private int real_txnum;
	/**
	 * 回滚事务数
	 */
	private int rall_txnum;
	/**
	 * 整个事务状态
	 */
	private int status = 1;
	/**
	 * 数据版本
	 */
	private int version;
	
	public void addVersion(){
		version++;
	}
	
	public int getTotal_txnum() {
		return total_txnum;
	}
	public void setTotal_txnum(int total_txnum) {
		this.total_txnum = total_txnum;
	}
	public int getPre_txnum() {
		return pre_txnum;
	}
	public void setPre_txnum(int pre_txnum) {
		this.pre_txnum = pre_txnum;
	}
	public int getReal_txnum() {
		return real_txnum;
	}
	public void setReal_txnum(int real_txnum) {
		this.real_txnum = real_txnum;
	}
	public int getRall_txnum() {
		return rall_txnum;
	}
	public void setRall_txnum(int rall_txnum) {
		this.rall_txnum = rall_txnum;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}

	public String getTrancNo() {
		return trancNo;
	}

	public void setTrancNo(String trancNo) {
		this.trancNo = trancNo;
	}
	
	

}
