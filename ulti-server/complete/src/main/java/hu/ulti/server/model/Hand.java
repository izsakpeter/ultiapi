package hu.ulti.server.model;

import java.util.List;

public class Hand {

	private int id;
	private List<UuidWithCardId> list;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<UuidWithCardId> getList() {
		return list;
	}

	public void setList(List<UuidWithCardId> list) {
		this.list = list;
	}
}
