package com.zyc.budget.data;

import java.util.HashMap;
import java.util.Map;

public enum BudgetType {
	BUDGET("budget", "预算数"),
	OCCUPY("occupy", "占用数"),
	ACTUAL("actual", "发生数");
	
	private String value;
	private String text;
	
	private BudgetType(String value, String text) {
		this.value = value;
		this.text = text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public static Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();
		for (BudgetType type : BudgetType.values()) {
			map.put(type.getValue(), type.getText());
		}
		return map;
	}

}
