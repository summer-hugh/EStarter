package com.eiaao.bean;

public class SoftWareBean {

	private String softWareName;
	private String softWarePath;
	
	final public String getSoftWareName() {
		return softWareName;
	}
	
	final public void setSoftWareName(String softWareName) {
		this.softWareName = softWareName;
	}
	
	final public String getSoftWarePath() {
		return softWarePath;
	}
	
	final public void setSoftWarePath(String softWarePath) {
		this.softWarePath = softWarePath;
	}
	
	public SoftWareBean() {
	}

	public SoftWareBean(String softWareName, String softWarePath) {
		this.softWareName = softWareName;
		this.softWarePath = softWarePath;
	}

	@Override
	public String toString() {
		return "[软件名称："+getSoftWareName()+"---软件路径"+getSoftWarePath()+"]";
	}
}
