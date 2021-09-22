package hu.ulti.server.model;

public class Say {

	private int playerId;
	private boolean have40 = false;
	private boolean have120 = false;
	private boolean have220 = false;
	private boolean have320 = false;
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
	private boolean ackKontra = false;
	
	private boolean rekontraPassz = false;
	private boolean rekontra40100 = false;
	private boolean rekontraUlti = false;
	private boolean rekontraBetli = false;
	private boolean rekontraDuri = false;
	private boolean rekontraDuriSz = false;
	private boolean rekontra20100 = false;
	private boolean rekontraBetliTer = false;
	private boolean rekontraDuriTer = false;
	private boolean rekontraDuriTerSz = false;

	public Say() {
	}

	public Say(int playerId, boolean have40, boolean have120, boolean have220, boolean have320, boolean kontraPassz,
			boolean kontra40100, boolean kontraUlti, boolean kontraBetli, boolean kontraDuri, boolean kontraDuriSz,
			boolean kontra20100, boolean kontraBetliTer, boolean kontraDuriTer, boolean kontraDuriTerSz,
			boolean ackKontra) {
		this.playerId = playerId;
		this.have40 = have40;
		this.have120 = have120;
		this.have220 = have220;
		this.have320 = have320;
		this.kontraPassz = kontraPassz;
		this.kontra40100 = kontra40100;
		this.kontraUlti = kontraUlti;
		this.kontraBetli = kontraBetli;
		this.kontraDuri = kontraDuri;
		this.kontraDuriSz = kontraDuriSz;
		this.kontra20100 = kontra20100;
		this.kontraBetliTer = kontraBetliTer;
		this.kontraDuriTer = kontraDuriTer;
		this.kontraDuriTerSz = kontraDuriTerSz;
		this.ackKontra = ackKontra;
	}
	
	public Say(int playerId, boolean rekontraPassz, boolean rekontra40100, boolean rekontraUlti, boolean rekontraBetli,
			boolean rekontraDuri, boolean rekontraDuriSz, boolean rekontra20100, boolean rekontraBetliTer,
			boolean rekontraDuriTer, boolean rekontraDuriTerSz) {
		super();
		this.playerId = playerId;
		this.rekontraPassz = rekontraPassz;
		this.rekontra40100 = rekontra40100;
		this.rekontraUlti = rekontraUlti;
		this.rekontraBetli = rekontraBetli;
		this.rekontraDuri = rekontraDuri;
		this.rekontraDuriSz = rekontraDuriSz;
		this.rekontra20100 = rekontra20100;
		this.rekontraBetliTer = rekontraBetliTer;
		this.rekontraDuriTer = rekontraDuriTer;
		this.rekontraDuriTerSz = rekontraDuriTerSz;		
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

	public boolean isAckKontra() {
		return ackKontra;
	}

	public void setAckKontra(boolean ackKontra) {
		this.ackKontra = ackKontra;
	}

	public boolean isRekontraPassz() {
		return rekontraPassz;
	}

	public void setRekontraPassz(boolean rekontraPassz) {
		this.rekontraPassz = rekontraPassz;
	}

	public boolean isRekontra40100() {
		return rekontra40100;
	}

	public void setRekontra40100(boolean rekontra40100) {
		this.rekontra40100 = rekontra40100;
	}

	public boolean isRekontraUlti() {
		return rekontraUlti;
	}

	public void setRekontraUlti(boolean rekontraUlti) {
		this.rekontraUlti = rekontraUlti;
	}

	public boolean isRekontraBetli() {
		return rekontraBetli;
	}

	public void setRekontraBetli(boolean rekontraBetli) {
		this.rekontraBetli = rekontraBetli;
	}

	public boolean isRekontraDuri() {
		return rekontraDuri;
	}

	public void setRekontraDuri(boolean rekontraDuri) {
		this.rekontraDuri = rekontraDuri;
	}

	public boolean isRekontraDuriSz() {
		return rekontraDuriSz;
	}

	public void setRekontraDuriSz(boolean rekontraDuriSz) {
		this.rekontraDuriSz = rekontraDuriSz;
	}

	public boolean isRekontra20100() {
		return rekontra20100;
	}

	public void setRekontra20100(boolean rekontra20100) {
		this.rekontra20100 = rekontra20100;
	}

	public boolean isRekontraBetliTer() {
		return rekontraBetliTer;
	}

	public void setRekontraBetliTer(boolean rekontraBetliTer) {
		this.rekontraBetliTer = rekontraBetliTer;
	}

	public boolean isRekontraDuriTer() {
		return rekontraDuriTer;
	}

	public void setRekontraDuriTer(boolean rekontraDuriTer) {
		this.rekontraDuriTer = rekontraDuriTer;
	}

	public boolean isRekontraDuriTerSz() {
		return rekontraDuriTerSz;
	}

	public void setRekontraDuriTerSz(boolean rekontraDuriTerSz) {
		this.rekontraDuriTerSz = rekontraDuriTerSz;
	}
}
