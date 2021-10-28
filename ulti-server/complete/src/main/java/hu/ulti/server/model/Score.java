package hu.ulti.server.model;

public class Score {

	private int id;
	private int sumScore = 0;
	private int lastPartyScore = 0;

	public Score() {
	}

	public Score(int id) {
		this.id = id;
	}

	public Score(int id, int sumScore, int lastPartyScore) {
		this.id = id;
		this.sumScore = sumScore;
		this.lastPartyScore = lastPartyScore;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSumScore() {
		return sumScore;
	}

	public void setSumScore(int lastPartyScore) {
		this.sumScore += lastPartyScore;
	}

	public int getLastPartyScore() {
		return lastPartyScore;
	}

	public void setLastPartyScore(int lastPartyScore) {
		this.lastPartyScore += lastPartyScore;
	}

	public void resetLastPartyScore() {
		this.lastPartyScore = 0;
	}
}
