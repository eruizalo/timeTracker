package com.timetracker.model;

public class ErrorDesc {

	private int errorCode;
	private String errorDesc;
	private Exception exception;
	
	//public ErrorDesc(){};
	
	public ErrorDesc(int errorCode, String errorDesc, Exception errorException){
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
		this.setException(errorException);
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
	 * @return the exception
	 */
	public Exception getException() {
		return exception;
	}
	/**
	 * @param exception the exception to set
	 */
	public void setException(Exception exception) {
		this.exception = exception;
	}
}
