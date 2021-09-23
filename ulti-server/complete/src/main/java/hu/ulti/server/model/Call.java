package hu.ulti.server.model;

public class Call {

	private int callId;
	private Kontra kontra;

	public Call(int callId, Kontra kontra) {
		this.callId = callId;
		this.kontra = kontra;
	}

	public int getCallId() {
		return callId;
	}

	public void setCallId(int callId) {
		this.callId = callId;
	}

	public Kontra getKontra() {
		return kontra;
	}

	public void setKontra(Kontra kontra) {
		this.kontra = kontra;
	}
}