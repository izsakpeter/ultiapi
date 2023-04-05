package hu.ulti.server.modelOld;

public class Strike {

	private int round;
	private int card1Id = -1;
	private int card2Id = -1;
	private int card3Id = -1;
	private int card1PlayerId = 0;
	private int card2PlayerId = 0;
	private int card3PlayerId = 0;

	public Strike() {
	}

	public Strike(int round, int card1Id, int card2Id, int card3Id, int card1PlayerId, int card2PlayerId,
			int card3PlayerId) {
		this.round = round;
		this.card1Id = card1Id;
		this.card2Id = card2Id;
		this.card3Id = card3Id;
		this.card1PlayerId = card1PlayerId;
		this.card2PlayerId = card2PlayerId;
		this.card3PlayerId = card3PlayerId;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public int getCard1Id() {
		return card1Id;
	}

	public void setCard1Id(int card1Id) {
		this.card1Id = card1Id;
	}

	public int getCard2Id() {
		return card2Id;
	}

	public void setCard2Id(int card2Id) {
		this.card2Id = card2Id;
	}

	public int getCard3Id() {
		return card3Id;
	}

	public void setCard3Id(int card3Id) {
		this.card3Id = card3Id;
	}

	public int getCard1PlayerId() {
		return card1PlayerId;
	}

	public void setCard1PlayerId(int card1PlayerId) {
		this.card1PlayerId = card1PlayerId;
	}

	public int getCard2PlayerId() {
		return card2PlayerId;
	}

	public void setCard2PlayerId(int card2PlayerId) {
		this.card2PlayerId = card2PlayerId;
	}

	public int getCard3PlayerId() {
		return card3PlayerId;
	}

	public void setCard3PlayerId(int card3PlayerId) {
		this.card3PlayerId = card3PlayerId;
	}

	public void addCardToStrike(int cardId, int playerId) {
		if (this.getCard1Id() == -1) {
			this.setCard1Id(cardId);
			this.setCard1PlayerId(playerId);
		} else if (this.getCard2Id() == -1) {
			this.setCard2Id(cardId);
			this.setCard2PlayerId(playerId);
		} else if (this.getCard3Id() == -1) {
			this.setCard3Id(cardId);
			this.setCard3PlayerId(playerId);
		}
	}

	public void clearStrike() {
		this.setCard1Id(-1);
		this.setCard2Id(-1);
		this.setCard3Id(-1);
		this.setCard1PlayerId(-1);
		this.setCard2PlayerId(-1);
		this.setCard3PlayerId(-1);
	}
}