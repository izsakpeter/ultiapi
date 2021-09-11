package hu.ulti.server.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

	public static Hand fillHandWithMinusOne(Player player) {
		Hand hand = new Hand();
		hand.setId(player.getId());
		List<UuidWithCardId> list = new ArrayList<UuidWithCardId>();

		for (int i = 0; i < player.getHand().size(); i++) {
			UUID uuid = UUID.randomUUID();
			list.add(new UuidWithCardId(uuid.toString(), -1));
		}
		hand.setList(list);

		return hand;
	}
	
	public static Hand setHandWithCardes(Player player) {		
		Hand hand = new Hand();
		hand.setId(player.getId());
		List<UuidWithCardId> list = new ArrayList<UuidWithCardId>();
		
		for (int i = 0; i < player.getHand().size(); i++) {
			UUID uuid = UUID.randomUUID();
			list.add(new UuidWithCardId(uuid.toString(), player.getHand().get(i).getId()));
		}
		
		hand.setList(list);
		
		return hand;
	}
}
