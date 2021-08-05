package hu.ulti;

import hu.ulti.model.Game;

public class Temp {
	/*  további 2 játékos lapjai
	private static Game game2;
	private static Game game3;
	
	game2 = new Game(json2);
	List<Card> cards2 = game2.getPlayer().getHand();
	VerticalLayout hand2Vp = new VerticalLayout();
	HorizontalLayout hand2Hp = new HorizontalLayout();
	
	Button button2 = new Button("rendezés");
	button2.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
		private static final long serialVersionUID = 7162213404334728757L;

		@Override
		public void onComponentEvent(ClickEvent<Button> event) {
			
			JSONObject jsonOrderChange = Request.changeOrder(game2.getPlayer().getId(), !game2.getPlayer().isColorOrder());
			game2 = new Game(jsonOrderChange);
			List<Card> cardsOrderChange2 = Helper.getOrderedHand(game2.getPlayer().getHand(), game2.getPlayer().isColorOrder());
			
			hand2Hp.removeAll();
			
			for (int i = 0; i < cardsOrderChange2.size(); i++) {
				hand2Hp.add(Helper.getCardImg(cardsOrderChange2.get(i).getOrderColorId()));
			}
		}
	});
	hand2Vp.add(button2);
	
	hand2Vp.add(hand2Hp);

	for (int i = 0; i < cards2.size(); i++) {
		hand2Hp.add(Helper.getCardImg(cards2.get(i).getOrderColorId()));
	}
	layout.add(hand2Vp);

	game3 = new Game(json3);
	List<Card> cards3 = game3.getPlayer().getHand();
	VerticalLayout hand3Vp = new VerticalLayout();
	HorizontalLayout hand3Hp = new HorizontalLayout();
	
	Button button3 = new Button("rendezés");
	button3.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
		private static final long serialVersionUID = 7162213404334728757L;

		@Override
		public void onComponentEvent(ClickEvent<Button> event) {
			
			JSONObject jsonOrderChange = Request.changeOrder(game3.getPlayer().getId(), !game3.getPlayer().isColorOrder());
			game3 = new Game(jsonOrderChange);
			List<Card> cardsOrderChange3 = Helper.getOrderedHand(game3.getPlayer().getHand(), game3.getPlayer().isColorOrder());
			
			hand3Hp.removeAll();
			
			for (int i = 0; i < cardsOrderChange3.size(); i++) {
				hand3Hp.add(Helper.getCardImg(cardsOrderChange3.get(i).getOrderColorId()));
			}
		}
	});
	hand3Vp.add(button3);
	
	hand3Vp.add(hand3Hp);

	for (int i = 0; i < cards3.size(); i++) {
		hand3Hp.add(Helper.getCardImg(cards3.get(i).getOrderColorId()));
	}
	layout.add(hand3Vp);
*/

}
