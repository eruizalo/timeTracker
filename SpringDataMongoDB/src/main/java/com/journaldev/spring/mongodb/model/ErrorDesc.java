package com.journaldev.spring.mongodb.model;

public class ErrorDesc {

	private int errorCode;
	private String errorDesc;
	private String errorFiller;
	
	public ErrorDesc(){};
	
	public ErrorDesc(int errorCode, String errorDesc, String errorFiller){
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
		this.errorFiller = errorFiller;
	}
	
	
	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	/**
	 * @return the errorDesc
	 */
	public String getErrorDesc() {
		return errorDesc;
	}
	/**
	 * @param errorDesc the errorDesc to set
	 */
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	
	/**
	 * @return the errorFiller
	 */
	public String getErrorFiller() {
		return errorFiller;
	}
	/**
	 * @param errorFiller the errorFiller to set
	 */
	public void setErrorFiller(String errorFiller) {
		this.errorFiller = errorFiller;
	}
	
	
}
