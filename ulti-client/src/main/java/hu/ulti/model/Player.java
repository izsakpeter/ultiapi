package hu.ulti.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Player {

	private int id;
	private boolean isColorOrder;
	private List<Card> hand = new ArrayList<>();
	private boolean isCallOk;
	private List<Strike> strikes = new ArrayList<Strike>();
	private boolean isColorForced = false;

	public Player() {
	}

	public Player(JSONObject jsonObj) {
		this.id = jsonObj.getInt("id");
		this.isColorOrder = jsonObj.getBoolean("colorOrder");
		this.isCallOk = jsonObj.getBoolean("callOk");
		this.isColorForced = jsonObj.getBoolean("colorForced");

		JSONArray handArray = jsonObj.getJSONArray("hand");
		for (int i = 0; i < handArray.length(); i++) {
			this.hand.add(new Card(handArray.getJSONObject(i)));
		}

		JSONArray strikesArray = jsonObj.getJSONArray("strikes");
		for (int i = 0; i < strikesArray.length(); i++) {
			this.strikes.add(new Strike(strikesArray.getJSONObject(i)));
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isColorOrder() {
		return isColorOrder;
	}

	public void setColorOrder(boolean isColorOrder) {
		this.isColorOrder = isColorOrder;
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}

	public boolean isCallOk() {
		return isCallOk;
	}

	public void setCallOk(boolean isCallOk) {
		this.isCallOk = isCallOk;
	}

	public List<Strike> getStrikes() {
		return strikes;
	}

	public void setStrikes(Strike strike) {
		this.strikes.add(strike);
	}

	public boolean isColorForced() {
		return isColorForced;
	}

	public void setColorForced(boolean isColorForced) {
		this.isColorForced = isColorForced;
	}
	
	
}
