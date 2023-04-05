package hu.ulti.server.modelOld;

public class UuidWithCardId {

	private String uuid;
	private int cardId;

	public UuidWithCardId(String uuid, int cardId) {
		this.uuid = uuid;
		this.cardId = cardId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
}
