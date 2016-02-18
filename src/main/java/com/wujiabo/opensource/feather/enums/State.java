package com.wujiabo.opensource.feather.enums;

public enum State {
	ACTIVE("0"), INACTIVE("1");

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private State(String value) {
		this.value = value;
	}
}
