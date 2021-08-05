package hu.ulti.views;

import java.util.List;

import org.json.JSONObject;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
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
		
		
		if (game.getActivePlayer() == 1) {
			
			Dialog dialog = new Dialog();
			
			RadioButtonGroup<String> startingValue = new RadioButtonGroup<>();
			startingValue.setLabel("Mire veszed fel a talont?");
			startingValue.setItems("makk", "zold", "tok", "piros", "ANYÁDRA");
			startingValue.setValue("makk");
			
			dialog.add(startingValue);
			
			Button buttonOK = new Button("OK");
			buttonOK.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
				private static final long serialVersionUID = 5729248453300980960L;

				@Override
				public void onComponentEvent(ClickEvent<Button> event) {
					//Request.setStartingValue(1, startingValue.getValue().get)
					
					dialog.close();
				}
			});
			
			dialog.add(buttonOK);
			
			dialog.open();
		}
		
	}
}
