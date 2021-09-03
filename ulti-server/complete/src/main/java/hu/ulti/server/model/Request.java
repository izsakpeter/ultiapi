package hu.ulti.server.model;

public class Request {

	private int id;

	private Long lastTimeStamp;

	private boolean colorOrder;

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

}
