package hu.ulti.server.model;

public class KeepAlive {

	private int id;
	private Long lastTimeStamp;

	public KeepAlive() {
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

}
