package hu.ulti.model;

import org.json.JSONObject;

public class Card {
	private int orderColorId;
	private int orderColorlessId;
	private String color;
	private String value;
	private int colorId;

	public Card() {
	}

	public Card(JSONObject jsonObj) {
		this.orderColorId = jsonObj.getInt("orderColorId");
		this.orderColorlessId = jsonObj.getInt("orderColorlessId");
		this.color = jsonObj.getString("color");
		this.value = jsonObj.getString("value");
		this.colorId = jsonObj.getInt("colorId");
	}

	public int getOrderColorId() {
		return orderColorId;
	}

	public void setOrderColorId(int orderColorId) {
		this.orderColorId = orderColorId;
	}

	public int getOrderColorlessId() {
		return orderColorlessId;
	}

	public void setOrderColorlessId(int orderColorlessId) {
		this.orderColorlessId = orderColorlessId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getColorId() {
		return colorId;
	}

	public void setColorId(int colorId) {
		this.colorId = colorId;
	}
}
