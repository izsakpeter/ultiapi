package hu.ulti.server.model;

import hu.ulti.server.Helper;

public class SayMsg {

	@SuppressWarnings("unused")
	private String id;
	
	private int playerId;
	private int kontraId;
	private int callId;
	private String otherSay;

	public SayMsg(int playerId, int kontraId, int callId) {
		this.id = Helper.getUUid();
		this.playerId = playerId;
		this.kontraId = kontraId;
		this.callId = callId;
		this.otherSay = "-1";
	}

	public SayMsg(int playerId, String otherSay) {
		this.id = Helper.getUUid();
		this.playerId = playerId;
		this.otherSay = otherSay;
		this.kontraId = -1;
		this.callId = -1;
	}

	public SayMsg(int playerId, int kontraId, int callId, String otherSay) {
		this.id = Helper.getUUid();
		this.playerId = playerId;
		this.kontraId = kontraId;
		this.callId = callId;
		this.otherSay = otherSay;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public int getKontraId() {
		return kontraId;
	}

	public void setKontraId(int kontraId) {
		this.kontraId = kontraId;
	}

	public int getCallId() {
		return callId;
	}

	public void setCallId(int callId) {
		this.callId = callId;
	}

	public String getOtherSay() {
		return otherSay;
	}

	public void setOtherSay(String otherSay) {
		this.otherSay = otherSay;
	}
}
