package hu.ulti.server.modelOld;

public class Kontra {

	private int playerId;
	private KontraAck kontra;
	private KontraAck rekontra;
	private KontraAck szupKontra;
	private KontraAck szupRekontra;
	private KontraAck maxKontra;

	public Kontra(int playerId, KontraAck kontra, KontraAck rekontra, KontraAck szupKontra, KontraAck szupRekontra,
			KontraAck maxKontra) {
		super();
		this.playerId = playerId;
		this.kontra = kontra;
		this.rekontra = rekontra;
		this.szupKontra = szupKontra;
		this.szupRekontra = szupRekontra;
		this.maxKontra = maxKontra;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public KontraAck getKontra() {
		return kontra;
	}

	public void setKontra(KontraAck kontra) {
		this.kontra = kontra;
	}

	public KontraAck getRekontra() {
		return rekontra;
	}

	public void setRekontra(KontraAck rekontra) {
		this.rekontra = rekontra;
	}

	public KontraAck getSzupKontra() {
		return szupKontra;
	}

	public void setSzupKontra(KontraAck szupKontra) {
		this.szupKontra = szupKontra;
	}

	public KontraAck getSzupRekontra() {
		return szupRekontra;
	}

	public void setSzupRekontra(KontraAck szupRekontra) {
		this.szupRekontra = szupRekontra;
	}

	public KontraAck getMaxKontra() {
		return maxKontra;
	}

	public void setMaxKontra(KontraAck maxKontra) {
		this.maxKontra = maxKontra;
	}
}
