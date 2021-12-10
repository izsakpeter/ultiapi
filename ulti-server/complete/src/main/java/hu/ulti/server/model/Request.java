package hu.ulti.server.model;

import java.util.List;

public class Request {

	private int id;
	private Long lastTimeStamp;
	private boolean colorOrder;
	private int value;
	private List<Call> call;
	private List<Integer> talonid;
	private boolean isjoin;
	private int cardId;
	private boolean bluff4020;
	private boolean have40;
	private boolean have120;
	private boolean have220;
	private boolean have320;
	private int kontraId = 0;
	private boolean kontraPassz = false;
	private boolean kontra40100 = false;
	private boolean kontraUlti = false;
	private boolean kontraBetli = false;
	private boolean kontraDuri = false;
	private boolean kontraDuriSz = false;
	private boolean kontra20100 = false;
	private boolean kontraBetliTer = false;
	private boolean kontraDuriTer = false;
	private boolean kontraDuriTerSz = false;

	public Request() {
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

	public boolean isColorOrder() {
		return colorOrder;
	}

	public void setColorOrder(boolean colorOrder) {
		this.colorOrder = colorOrder;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public List<Call> getCall() {
		return call;
	}

	public void setCall(List<Call> call) {
		this.call = call;
	}

	public List<Integer> getTalonid() {
		return talonid;
	}

	public void setTalonid(List<Integer> talonid) {
		this.talonid = talonid;
	}

	public boolean isIsjoin() {
		return isjoin;
	}

	public void setIsjoin(boolean isjoin) {
		this.isjoin = isjoin;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public boolean isBluff4020() {
		return bluff4020;
	}

	public void setBluff4020(boolean bluff4020) {
		this.bluff4020 = bluff4020;
	}

	public boolean isHave40() {
		return have40;
	}

	public void setHave40(boolean have40) {
		this.have40 = have40;
	}

	public boolean isHave120() {
		return have120;
	}

	public void setHave120(boolean have120) {
		this.have120 = have120;
	}

	public boolean isHave220() {
		return have220;
	}

	public void setHave220(boolean have220) {
		this.have220 = have220;
	}

	public boolean isHave320() {
		return have320;
	}

	public void setHave320(boolean have320) {
		this.have320 = have320;
	}

	public boolean isKontraPassz() {
		return kontraPassz;
	}

	public void setKontraPassz(boolean kontraPassz) {
		this.kontraPassz = kontraPassz;
	}

	public boolean isKontra40100() {
		return kontra40100;
	}

	public void setKontra40100(boolean kontra40100) {
		this.kontra40100 = kontra40100;
	}

	public boolean isKontraUlti() {
		return kontraUlti;
	}

	public void setKontraUlti(boolean kontraUlti) {
		this.kontraUlti = kontraUlti;
	}

	public boolean isKontraBetli() {
		return kontraBetli;
	}

	public void setKontraBetli(boolean kontraBetli) {
		this.kontraBetli = kontraBetli;
	}

	public boolean isKontraDuri() {
		return kontraDuri;
	}

	public void setKontraDuri(boolean kontraDuri) {
		this.kontraDuri = kontraDuri;
	}

	public boolean isKontraDuriSz() {
		return kontraDuriSz;
	}

	public void setKontraDuriSz(boolean kontraDuriSz) {
		this.kontraDuriSz = kontraDuriSz;
	}

	public boolean isKontra20100() {
		return kontra20100;
	}

	public void setKontra20100(boolean kontra20100) {
		this.kontra20100 = kontra20100;
	}

	public boolean isKontraBetliTer() {
		return kontraBetliTer;
	}

	public void setKontraBetliTer(boolean kontraBetliTer) {
		this.kontraBetliTer = kontraBetliTer;
	}

	public boolean isKontraDuriTer() {
		return kontraDuriTer;
	}

	public void setKontraDuriTer(boolean kontraDuriTer) {
		this.kontraDuriTer = kontraDuriTer;
	}

	public boolean isKontraDuriTerSz() {
		return kontraDuriTerSz;
	}

	public void setKontraDuriTerSz(boolean kontraDuriTerSz) {
		this.kontraDuriTerSz = kontraDuriTerSz;
	}

	public int getKontraId() {
		return kontraId;
	}

	public void setKontraId(int kontraId) {
		this.kontraId = kontraId;
	}
}
