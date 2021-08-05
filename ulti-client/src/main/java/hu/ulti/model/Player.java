package hu.ulti.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Player {

	private int id;
	private boolean isColorOrder;
	private List<Card> hand = new ArrayList<>();
	private int forcedColorId = 0;
	private boolean isCallOk = true;
	private List<Strike> strikes = new ArrayList<Strike>();

	public Player() {
	}

	public Player(JSONObject jsonObj) {
		this.id = jsonObj.getInt("id");
		this.isColorOrder = jsonObj.getBoolean("colorOrder");
		this.forcedColorId = jsonObj.getInt("forcedColorId");
		this.isCallOk = jsonObj.getBoolean("callOk");

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

	public int getForcedColorId() {
		return forcedColorId;
	}

	public void setForcedColorId(int forcedColorId) {
		this.forcedColorId = forcedColorId;
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
}
