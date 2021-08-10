package hu.ulti.views;

import java.util.ArrayList;
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
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouteAlias;

import hu.ulti.Helper;
import hu.ulti.Request;
import hu.ulti.model.Call;
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

	private List<Integer> talonList = new ArrayList<Integer>();

	@SuppressWarnings("serial")
	public UlticlientView() {
		addClassName("ulticlient-view");

		VerticalLayout layout = new VerticalLayout();
		add(layout);

		HorizontalLayout loginLayout = new HorizontalLayout();

		TextField loginTF = new TextField();
		Button loginButton = new Button("ok");

		loginButton.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
			@Override
			public void onComponentEvent(ClickEvent<Button> event) {

				JSONObject json = Request.joinReq(Integer.parseInt(loginTF.getValue()));

				VerticalLayout hand1Vp = new VerticalLayout();

				Button changeOrderButton = new Button("rendezés");
				changeOrderButton.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
					private static final long serialVersionUID = 7162213404334728757L;

					@Override
					public void onComponentEvent(ClickEvent<Button> event) {

						JSONObject jsonOrderChange = Request.changeOrder(game.getPlayer().getId(),
								!game.getPlayer().isColorOrder());
						game = new Game(jsonOrderChange);
						refreshCards();
					}
				});
				hand1Vp.add(changeOrderButton);

				game = new Game(json);
				refreshCards();
				hand1Vp.add(cardsHP);
				hand1Vp.add(talonHP);
				layout.add(hand1Vp);

				if (game.getActivePlayer() == game.getPlayer().getId() && game.getPlayer().getHand().size() == 10) {

					if (game.getStartingValue() == 0) {
						Dialog dialog = new Dialog();

						RadioButtonGroup<String> startingValue = new RadioButtonGroup<>();
						startingValue.setLabel("Mire veszed fel a talont?");
						startingValue.setItems(Call.MAKK, Call.ZOLD, Call.TOK, Call.PIROS);
						startingValue.setValue(Call.MAKK);

						dialog.add(startingValue);

						Button buttonOK = new Button("OK");
						buttonOK.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
							private static final long serialVersionUID = 5729248453300980960L;

							@Override
							public void onComponentEvent(ClickEvent<Button> event) {
								JSONObject setStartingValue = Request.setStartingValue(game.getPlayer().getId(),
										Helper.getSelectedId(startingValue.getValue()));
								game = new Game(setStartingValue);
								refreshCards();
								dialog.close();
							}
						});

						dialog.add(buttonOK);

						dialog.open();
					} else {

						VerticalLayout navVp = new VerticalLayout();

						Button joinButton = new Button("Felvesz");
						joinButton.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
							private static final long serialVersionUID = 5729248453300980960L;

							@Override
							public void onComponentEvent(ClickEvent<Button> event) {
								JSONObject joinObj = Request.join(game.getPlayer().getId(), true);
								game = new Game(joinObj);
								refreshCards();
							}
						});
						
						Button passzButton = new Button("Passz");
						passzButton.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
							private static final long serialVersionUID = 5729248453300980960L;

							@Override
							public void onComponentEvent(ClickEvent<Button> event) {
								JSONObject joinObj = Request.join(game.getPlayer().getId(), false);
								game = new Game(joinObj);
								refreshCards();
							}
						});

						navVp.add(joinButton);
						navVp.add(passzButton);

						hand1Vp.add(navVp);
					}
				}
			}
		});

		loginLayout.add(loginTF);
		loginLayout.add(loginButton);

		layout.add(loginLayout);
	}

	@SuppressWarnings("serial")
	private void refreshCards() {
		List<Card> orderedcards = Helper.getOrderedHand(game.getPlayer().getHand(), game.getPlayer().isColorOrder());

		cardsHP.removeAll();
		talonHP.removeAll();

		for (Card card : orderedcards) {
			Image image = Helper.getCardImg(card.getOrderColorId());
			
			if (game.getActivePlayer() == game.getPlayer().getId()) {
				image.addClickListener(new ComponentEventListener<ClickEvent<Image>>() {
					@Override
					public void onComponentEvent(ClickEvent<Image> event) {
						talonHP.add(image);
						talonList.add(card.getOrderColorId());

						if (talonList.size() == 2) {
							showValuesDialog();
						}
					}
				});
			}
			
			cardsHP.add(image);
		}
	}

	private void showValuesDialog() {
		Dialog dialog = new Dialog();

		VerticalLayout layout = new VerticalLayout();
		
		Label errorLabel = new Label();
		layout.add(errorLabel);

		RadioButtonGroup<String> color = new RadioButtonGroup<>();
		color.setLabel("Mondás");
		color.setItems(Call.MAKK, Call.ZOLD, Call.TOK, Call.PIROS);
		color.setValue(Call.MAKK);
		layout.add(color);

		CheckboxGroup<String> value = new CheckboxGroup<>();
		value.setItems(Call.PASSZ, Call.SZAZ40, Call.ULTI, Call.BETLI, Call.DURI_SZINES, Call.DURI_SZINTELEN,
				Call.SZAZ20, Call.BETLI_TERITETT, Call.DURI_SZINES_TERITETT, Call.DURI_SZINTELEN_TERITETT);
		value.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
		color.setValue(Call.PASSZ);
		layout.add(value);

		Button buttonOK = new Button("OK");
		buttonOK.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
			private static final long serialVersionUID = 2;

			@Override
			public void onComponentEvent(ClickEvent<Button> event) {
				List<Integer> callList = Call.getCallList(color.getValue(), value.getSelectedItems());
				JSONObject callObject = Request.call(game.getPlayer().getId(), callList, talonList);
				game = new Game(callObject);

				if (game.getPlayer().isCallOk()) {
					refreshCards();
					dialog.close();
				} else {
					errorLabel.setText("rossz hivás");
				}
			}
		});
		layout.add(buttonOK);

		dialog.add(layout);

		dialog.open();
	}
}
