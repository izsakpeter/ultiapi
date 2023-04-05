package hu.ulti.server.modelOld;

import java.util.List;

public class Call {

	private int callId;
	private List<Kontra> kontra;
	
	public Call() {
	}

	public Call(int callId, List<Kontra> kontra) {
		this.callId = callId;
		this.kontra = kontra;
	}

	public int getCallId() {
		return callId;
	}

	public void setCallId(int callId) {
		this.callId = callId;
	}

	public List<Kontra> getKontra() {
		return kontra;
	}

	public void setKontra(List<Kontra> kontra) {
		this.kontra = kontra;
	}
}