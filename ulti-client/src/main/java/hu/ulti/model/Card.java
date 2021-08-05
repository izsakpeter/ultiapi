package hu.ulti.model;

import org.json.JSONObject;

public class Card {
	private int orderColorId;
	private int orderColorlessId;
	private int colorId;

	public Card() {
	}

	public Card(JSONObject jsonObj) {
		this.orderColorId = jsonObj.getInt("orderColorId");
		this.orderColorlessId = jsonObj.getInt("orderColorlessId");
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

	public int getColorId() {
		return colorId;
	}

	public void setColorId(int colorId) {
		this.colorId = colorId;
	}
}
