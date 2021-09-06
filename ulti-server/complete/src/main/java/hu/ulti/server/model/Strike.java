package hu.ulti.server.model;

public class Strike {

	private int card1Id = -1;
	private int card2Id = -1;
	private int card3Id = -1;

	public Strike() {
	}

	public Strike(int card1Id, int card2Id, int card3Id) {
		this.card1Id = card1Id;
		this.card2Id = card2Id;
		this.card3Id = card3Id;
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

	public void addCardToStrike(int cardId) {
		if (this.getCard1Id() == -1)
			this.setCard1Id(cardId);
		else if (this.getCard2Id() == -1)
			this.setCard2Id(cardId);
		else if (this.getCard3Id() == -1)
			this.setCard3Id(cardId);
	}
	
	public void clearStrike() {
		this.setCard1Id(-1);
		this.setCard2Id(-1);
		this.setCard3Id(-1);
	}
}