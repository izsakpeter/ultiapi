package hu.ulti.views;

import java.util.List;

import org.json.JSONObject;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouteAlias;

import hu.ulti.Helper;
import hu.ulti.Request;
import hu.ulti.model.Card;
import hu.ulti.model.Game;

@PageTitle("ulti-client")
@Route(value = "ulti")
@RouteAlias(value = "")
public class UlticlientView extends Div {

	private static final long serialVersionUID = 1L;
	private static Game game;
	private static Game game2;
	private static Game game3;

	public UlticlientView() {
		addClassName("ulticlient-view");

		VerticalLayout layout = new VerticalLayout();
		add(layout);

		JSONObject json1 = Request.joinReq(1);
		JSONObject json2 = Request.joinReq(2);
		JSONObject json3 = Request.joinReq(3);

		json1 = Request.joinReq(1);
		json2 = Request.joinReq(2);

		game = new Game(json1);
		List<Card> cards = game.getPlayer().getHand();
		VerticalLayout hand1Vp = new VerticalLayout();
		HorizontalLayout hand1Hp = new HorizontalLayout();
		
		Button button1 = new Button("rendezés");
		button1.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
			private static final long serialVersionUID = 7162213404334728757L;

			@Override
			public void onComponentEvent(ClickEvent<Button> event) {
				
				JSONObject jsonOrderChange = Request.changeOrder(game.getPlayer().getId(), !game.getPlayer().isColorOrder());
				game = new Game(jsonOrderChange);
				List<Card> cardsOrderChange = Helper.getOrderedHand(game.getPlayer().getHand(), game.getPlayer().isColorOrder());
				
				hand1Hp.removeAll();
				
				for (int i = 0; i < cardsOrderChange.size(); i++) {
					hand1Hp.add(Helper.getCardImg(cardsOrderChange.get(i).getOrderColorId()));
				}
			}
		});
		hand1Vp.add(button1);
		
		hand1Vp.add(hand1Hp);

		for (int i = 0; i < cards.size(); i++) {
			hand1Hp.add(Helper.getCardImg(cards.get(i).getOrderColorId()));
		}
		layout.add(hand1Vp);
		
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
	
	}
}
