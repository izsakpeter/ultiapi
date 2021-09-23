package hu.ulti.server.model;

import java.util.List;

public class Request {

	private int id;
	private Long lastTimeStamp;
	private boolean colorOrder;
	private int value;
	private List<Call> call;
	private List<Integer> talonid;
	private boolean isjoin;
	private int cardid;
	private boolean bluff4020;
	private boolean have40;
	private boolean have120;
	private boolean have220;
	private boolean have320;

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

	public List<Call> getCall() {
		return call;
	}

	public void setCall(List<Call> call) {
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

	public boolean isHave40() {
		return have40;
	}

	public void setHave40(boolean have40) {
		this.have40 = have40;
	}

	public boolean isHave120() {
		return have120;
	}

	public void setHave120(boolean have120) {
		this.have120 = have120;
	}

	public boolean isHave220() {
		return have220;
	}

	public void setHave220(boolean have220) {
		this.have220 = have220;
	}

	public boolean isHave320() {
		return have320;
	}

	public void setHave320(boolean have320) {
		this.have320 = have320;
	}
}
