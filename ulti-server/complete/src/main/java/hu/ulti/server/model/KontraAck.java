package hu.ulti.server.model;

public class KontraAck {

	private boolean isSaid = false;
	private int ackBy = -1;

	public boolean isSaid() {
		return isSaid;
	}

	public void setSaid(boolean isSaid) {
		this.isSaid = isSaid;
	}

	public int getAckBy() {
		return ackBy;
	}

	public void setAckBy(int ackBy) {
		this.ackBy = ackBy;
	}
}
