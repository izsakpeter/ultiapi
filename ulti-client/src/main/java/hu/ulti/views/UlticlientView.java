package hu.ulti.views;

import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouteAlias;

import hu.ulti.Request;
import hu.ulti.model.Card;
import hu.ulti.model.Game;

@PageTitle("ulti-client")
@Route(value = "ulti")
@RouteAlias(value = "")
public class UlticlientView extends Div {
    
	private static final long serialVersionUID = 1L;

	public UlticlientView() {
        addClassName("ulticlient-view");
        
        VerticalLayout layout = new VerticalLayout();
        add(layout);
        
        layout.add(new Text("ulti"));
        
        JSONObject json1 = Request.joinReq(1);
        JSONObject json2 = Request.joinReq(2);
        JSONObject json3 = Request.joinReq(3);
        
        json1 = Request.joinReq(1);
        json2 = Request.joinReq(2);
        
        
        Game game = new Game(json1);
        
        List<Card> cards = game.getPlayer().getHand();
        
        String cardList1 = "";
        
        for (int i = 0; i < cards.size(); i++) {
			cardList1 += cards.get(i).getOrderColorId() + " ";
		}

        layout.add(new Label(cardList1));
        
        Game game2 = new Game(json2);
        List<Card> cards2 = game2.getPlayer().getHand();
        String cardList2 = "";
        
        for (int i = 0; i < cards2.size(); i++) {
			cardList2 += cards2.get(i).getOrderColorId() + " ";
		}

        layout.add(new Label(cardList2));
        
        Game game3 = new Game(json3);
        List<Card> cards3 = game3.getPlayer().getHand();
        
        String cardList3 = "";
        
        for (int i = 0; i < cards3.size(); i++) {
			cardList3 += cards3.get(i).getOrderColorId() + " ";
		}

        layout.add(new Label(cardList3));
        
    }
}
