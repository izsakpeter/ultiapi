package hu.ulti.server.modelOld;

public class Say {

	private int playerId;
	private boolean have40 = false;
	private boolean have120 = false;
	private boolean have220 = false;
	private boolean have320 = false;
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

	public Say() {
	}

	public Say(int playerId, Request req) {
		this.playerId = playerId;
		this.have40 = req.isHave40();
		this.have120 = req.isHave120();
		this.have220 = req.isHave220();
		this.have320 = req.isHave320();
	}

	public Say(Request req) {
		this.playerId = req.getId();
		this.kontraId = req.getKontraId();
		this.kontraPassz = req.isKontraPassz();
		this.kontra40100 = req.isKontra40100();
		this.kontraUlti = req.isKontraUlti();
		this.kontraBetli = req.isKontraBetli();
		this.kontraDuri = req.isKontraDuri();
		this.kontraDuriSz = req.isKontraDuriSz();
		this.kontra20100 = req.isKontra20100();
		this.kontraBetliTer = req.isKontraBetliTer();
		this.kontraDuriTer = req.isKontraDuriTer();
		this.kontraDuriTerSz = req.isKontraDuriTerSz();
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
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
