package hu.ulti.server.model;

public class Say {

	private int playerId;
	private boolean have40 = false;
	private boolean have120 = false;
	private boolean have220 = false;
	private boolean have320 = false;

	public Say() {
	}

	public Say(int playerId, boolean have40, boolean have120, boolean have220, boolean have320) {
		this.playerId = playerId;
		this.have40 = have40;
		this.have120 = have120;
		this.have220 = have220;
		this.have320 = have320;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
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
