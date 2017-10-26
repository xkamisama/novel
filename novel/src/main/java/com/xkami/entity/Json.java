package com.xkami.entity;

public class Json {
	private int result;
	private String message;
	public int getResult() {
		return result;
	}
	public Json(int result, String message) {
		super();
		this.result = result;
		this.message = message;
	}
	public Json() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
