package hu.ulti.server.model;

import java.util.List;

public class Request {

	private int id;
	private Long lastTimeStamp;
	private boolean colorOrder;
	private int value;
	private List<Integer> call;
	private List<Integer> talonid;
	private boolean isjoin;
	private int cardid;
	private boolean bluff4020;

	public Request() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getLastTimeStamp() {
		return lastTimeStamp;
	}

	public void setLastTimeStamp(Long lastTimeStamp) {
		this.lastTimeStamp = lastTimeStamp;
	}

	public boolean isColorOrder() {
		return colorOrder;
	}

	public void setColorOrder(boolean colorOrder) {
		this.colorOrder = colorOrder;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public List<Integer> getCall() {
		return call;
	}

	public void setCall(List<Integer> call) {
		this.call = call;
	}

	public List<Integer> getTalonid() {
		return talonid;
	}

	public void setTalonid(List<Integer> talonid) {
		this.talonid = talonid;
	}

	public boolean isIsjoin() {
		return isjoin;
	}

	public void setIsjoin(boolean isjoin) {
		this.isjoin = isjoin;
	}

	public int getCardid() {
		return cardid;
	}

	public void setCardid(int cardid) {
		this.cardid = cardid;
	}

	public boolean isBluff4020() {
		return bluff4020;
	}

	public void setBluff4020(boolean bluff4020) {
		this.bluff4020 = bluff4020;
	}
}
