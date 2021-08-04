package hu.ulti.model;

import org.json.JSONObject;

public class Strike {

	private int card1Id;
	private int card2Id;
	private int card3Id;

	public Strike() {
	}

	public Strike(JSONObject jsonObj) {
		this.card1Id = jsonObj.getInt("card1Id");
		this.card2Id = jsonObj.getInt("card2Id");
		this.card3Id = jsonObj.getInt("card3Id");
	}

	public int getCard1Id() {
		return card1Id;
	}

	public void setCard1Id(int card1Id) {
		this.card1Id = card1Id;
	}

	public int getCard2Id() {
		return card2Id;
	}

	public void setCard2Id(int card2Id) {
		this.card2Id = card2Id;
	}

	public int getCard3Id() {
		return card3Id;
	}

	public void setCard3Id(int card3Id) {
		this.card3Id = card3Id;
	}
}