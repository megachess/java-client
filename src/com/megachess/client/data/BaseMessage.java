package com.megachess.client.data;

/**
 * Base Message
 * 
 * @author nmarsollier
 *
 * @param <T>
 */
public abstract class BaseMessage<T> {
	private String action;
	private T data;

	public BaseMessage(String action, T data) {
		this.action = action;
		this.data = data;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
