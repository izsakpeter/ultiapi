package hu.ulti.views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
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
	private HorizontalLayout cardsHP = new HorizontalLayout();
	private HorizontalLayout talonHP = new HorizontalLayout();
	
	private List<Integer> talon = new ArrayList<Integer>();

	private static final int PLAYER_1_ID = 1;
	
	private static int storedIndex = 0;

	public UlticlientView() {
		addClassName("ulticlient-view");

		VerticalLayout layout = new VerticalLayout();
		add(layout);

		JSONObject json1 = Request.joinReq(PLAYER_1_ID);
		JSONObject json2 = Request.joinReq(2);
		JSONObject json3 = Request.joinReq(3);

		json1 = Request.joinReq(1);
		json2 = Request.joinReq(2);

		VerticalLayout hand1Vp = new VerticalLayout();

		Button button1 = new Button("rendezés");
		button1.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
			private static final long serialVersionUID = 7162213404334728757L;

			@Override
			public void onComponentEvent(ClickEvent<Button> event) {

				JSONObject jsonOrderChange = Request.changeOrder(game.getPlayer().getId(),
						!game.getPlayer().isColorOrder());
				refreshCards(jsonOrderChange);
			}
		});
		hand1Vp.add(button1);

		refreshCards(json1);
		hand1Vp.add(cardsHP);
		
		hand1Vp.add(talonHP);

		layout.add(hand1Vp);

		if (game.getActivePlayer() == PLAYER_1_ID && game.getPlayer().getHand().size() == 10) {

			Dialog dialog = new Dialog();

			RadioButtonGroup<String> startingValue = new RadioButtonGroup<>();
			startingValue.setLabel("Mire veszed fel a talont?");
			startingValue.setItems("makk", "zold", "tok", "piros");
			startingValue.setValue("makk");

			dialog.add(startingValue);

			Button buttonOK = new Button("OK");
			buttonOK.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
				private static final long serialVersionUID = 5729248453300980960L;

				@Override
				public void onComponentEvent(ClickEvent<Button> event) {
					JSONObject setStartingValue = Request.setStartingValue(PLAYER_1_ID,
							Helper.getSelectedId(startingValue.getValue()));
					refreshCards(setStartingValue);
					dialog.close();
				}
			});

			dialog.add(buttonOK);

			dialog.open();
		}

	}

	@SuppressWarnings("serial")
	private void refreshCards(JSONObject jsonObj) {
		game = new Game(jsonObj);
		List<Card> orderedcards = Helper.getOrderedHand(game.getPlayer().getHand(), game.getPlayer().isColorOrder());

		cardsHP.removeAll();
		
		for (Card card : orderedcards) {
			Image image = Helper.getCardImg(card.getOrderColorId());
			image.addClickListener(new ComponentEventListener<ClickEvent<Image>>() {
				@Override
				public void onComponentEvent(ClickEvent<Image> event) {
					talonHP.add(image);
					talon.add(card.getOrderColorId());
					
					if (talon.size() == 2) {
						showValuesDialog();
					}
				}
			});
			
			cardsHP.add(image);
		}
	}
	
	private void showValuesDialog() {
		Dialog dialog = new Dialog();
		
		VerticalLayout layout = new VerticalLayout();
		
		RadioButtonGroup<String> color = new RadioButtonGroup<>();
		color.setLabel("Mondás");
		color.setItems("makk", "zold", "tok", "piros");
		color.setValue("makk");
		layout.add(color);
		
		CheckboxGroup<String> value = new CheckboxGroup<>();
		value.setItems("passz", "40-100", "ulti", "betli", "duri szines", "20-100",
				"duri szintelen", "teritett betli", "teritett duri szines", "teritett duri szintelen" );
		value.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
		layout.add(value);
		
		List<Integer> call = Helper.getCallList(color.getValue(), value.getValue());

		Button buttonOK = new Button("OK");
		buttonOK.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
			private static final long serialVersionUID = 2;

			@Override
			public void onComponentEvent(ClickEvent<Button> event) {
				
				Request.call(PLAYER_1_ID, call, talon);
				
				//JSONObject callObject = Request.call(PLAYER_1_ID, call, talon);
				//refreshCards(callObject);
				dialog.close();
			}
		});
		layout.add(buttonOK);

		dialog.add(layout);
		
		dialog.open();
	}
}
