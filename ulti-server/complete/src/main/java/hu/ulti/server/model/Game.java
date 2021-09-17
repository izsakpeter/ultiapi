package hu.ulti.server.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private int startingValue;
	private Player player;
	private boolean isRoundStarted = false;
	private boolean isPlayReadyToStart = false;
	private int lastCallerId;
	private int activePlayer;
	private Strike round = new Strike();
	private List<Integer> call = new ArrayList<Integer>();
	private List<Integer> previousCall = new ArrayList<Integer>();
	private String errorMessage = "";
	private long lastModificationTimeStamp = 0;
	private boolean isGameOver = false;
	private List<Result> resultList = new ArrayList<Result>();
	private boolean isFirstTurn = false;
	private List<Say> says = new ArrayList<Say>();
	private List<Hand> hands = new ArrayList<Hand>();

	public Game() {
		this.startingValue = 0;
	}

	public Game(int startingValue, Player player, boolean isRoundStarted, boolean isPlayReadyToStart, int lastCallerId,
			int activePlayer, Strike round, List<Integer> call, List<Integer> previousCall, String errorMessage,
			long lastModificationTimeStamp, boolean isGameOver, List<Result> resultList, boolean isFirstTurn,
			List<Say> says, List<Hand> hands) {
		this.startingValue = startingValue;
		this.player = player;
		this.isRoundStarted = isRoundStarted;
		this.isPlayReadyToStart = isPlayReadyToStart;
		this.lastCallerId = lastCallerId;
		this.activePlayer = activePlayer;
		this.round = round;
		this.call = call;
		this.previousCall = previousCall;
		this.errorMessage = errorMessage;
		this.lastModificationTimeStamp = lastModificationTimeStamp;
		this.isGameOver = isGameOver;
		this.resultList = resultList;
		this.isFirstTurn = isFirstTurn;
		this.says = says;
		this.hands = hands;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getStartingValue() {
		return startingValue;
	}

	public void setStartingValue(int startingValue) {
		this.startingValue = startingValue;
	}

	public List<Integer> getPreviousCall() {
		return previousCall;
	}

	public void setPreviousCall(List<Integer> previousCall) {
		this.previousCall = previousCall;
	}

	public boolean isRoundStarted() {
		return isRoundStarted;
	}

	public void setRoundStarted(boolean isRoundStarted) {
		this.isRoundStarted = isRoundStarted;
	}

	public int getLastCallerId() {
		return lastCallerId;
	}

	public void setLastCallerId(int lastCallerId) {
		this.lastCallerId = lastCallerId;
	}

	public int getActivePlayer() {
		return activePlayer;
	}

	public void setActivePlayer(int activePlayer) {
		this.activePlayer = activePlayer;
	}

	public Strike getRound() {
		return round;
	}

	public void setRound(Strike round) {
		this.round = round;
	}

	public boolean isPlayReadyToStart() {
		return isPlayReadyToStart;
	}

	public void setPlayReadyToStart(boolean isPlayReadyToStart) {
		this.isPlayReadyToStart = isPlayReadyToStart;
	}

	public List<Integer> getCall() {
		return call;
	}

	public void setCall(List<Integer> call) {
		this.call = call;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public long getLastModificationTimeStamp() {
		return lastModificationTimeStamp;
	}

	public void setLastModificationTimeStamp(long lastModificationTimeStamp) {
		this.lastModificationTimeStamp = lastModificationTimeStamp;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

	public List<Result> getResultList() {
		return resultList;
	}

	public void setResultList(List<Result> resultList) {
		this.resultList = resultList;
	}

	public boolean isFirstTurn() {
		return isFirstTurn;
	}

	public void setFirstTurn(boolean isFirstTurn) {
		this.isFirstTurn = isFirstTurn;
	}

	public List<Say> getSays() {
		return says;
	}

	public void setSays(List<Say> says) {
		this.says = says;
	}

	public List<Hand> getHands() {
		return hands;
	}

	public void setHands(List<Hand> hands) {
		this.hands = hands;
	}

	@Override
	public Game clone() {
		try {
			return (Game) super.clone();
		} catch (Exception e) {
			return new Game(this.startingValue, this.player, this.isRoundStarted, this.isPlayReadyToStart,
					this.lastCallerId, this.activePlayer, this.round, this.call, this.previousCall, this.errorMessage,
					this.lastModificationTimeStamp, this.isGameOver, this.resultList, this.isFirstTurn, this.says, this.hands);
		}
	}
	
	public void addSayToList(Say say) {
		this.says.add(say);
	}
}
