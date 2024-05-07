package com.example.cfinal.vo;

public class GenericResponseVo {

	private boolean success;
	private String message;

	public GenericResponseVo(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public GenericResponseVo() {
		super();
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
