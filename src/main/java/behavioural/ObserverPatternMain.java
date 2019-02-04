package behavioural;

import static constants.Constants.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObserverPatternMain {

	public static void main(String[] args) {
		
		BayernMunchen bayern = new BayernMunchen();
		Arsenal arsenal = new Arsenal();
		
		LucasHernandez lc = new LucasHernandez();
		DenisSuarez ds = new DenisSuarez();
		
		System.out.println();
		System.out.println("LUCAS INFORM TO BAYERN HE IS FOR SALE...");
		System.out.println("DENIS INFORM TO ARSENAL HE IS FOR SALE...");
		lc.addBuyerClub(bayern);
		ds.addBuyerClub(arsenal);
		bayern.whatDoYouKnowAbout(ds.getName());
		bayern.whatDoYouKnowAbout(lc.getName());
		arsenal.whatDoYouKnowAbout(ds.getName());
		arsenal.whatDoYouKnowAbout(lc.getName());
		
		System.out.println();
		System.out.println("LUCAS INFORM HE IS NOT FOR SALE...");
		lc.addBuyerClub(arsenal);
		lc.setState(ForSalePlayerState.NOT_FOR_SALE);
		bayern.whatDoYouKnowAbout(ds.getName());
		bayern.whatDoYouKnowAbout(lc.getName());
		arsenal.whatDoYouKnowAbout(ds.getName());
		arsenal.whatDoYouKnowAbout(lc.getName());
		
		System.out.println();
		System.out.println("DENIS INFORM ARSENAL HE IS NOT GOING TO PLAY TO HIS TEAM...");
		ds.removeBuyerClub(arsenal);
		bayern.whatDoYouKnowAbout(ds.getName());
		bayern.whatDoYouKnowAbout(lc.getName());
		arsenal.whatDoYouKnowAbout(ds.getName());
		arsenal.whatDoYouKnowAbout(lc.getName());
	}

}

enum ForSalePlayerState {
	FOR_SALE, NOT_FOR_SALE, LISTEN_TO_BID, NOT_PLAY_TO_YOU
}

interface ForSalePlayer {

	public void addBuyerClub(BuyerClub bc);
	public void removeBuyerClub(BuyerClub bc);
	public void notifyBuyerClub();
	
}

interface BuyerClub {
	public void update(String playerName, ForSalePlayerState state);
}

class LucasHernandez implements ForSalePlayer{

	private final String name;
	private List<BuyerClub> clubs;
	private ForSalePlayerState state;
	
	public LucasHernandez() {
		name = LUCAS_HERNANDEZ;
		clubs = new ArrayList<>();
		state = ForSalePlayerState.FOR_SALE;
	}
	
	public ForSalePlayerState getState() {
		return state;
	}

	public void setState(ForSalePlayerState state) {
		this.state = state;
		notifyBuyerClub();
	}

	public String getName() {
		return name;
	}

	@Override
	public void addBuyerClub(BuyerClub bc) {
		clubs.add(bc);
		bc.update(getName(), getState());
	}

	@Override
	public void removeBuyerClub(BuyerClub bc) {
		clubs.remove(bc);
		bc.update(getName(), ForSalePlayerState.NOT_PLAY_TO_YOU);
	}

	@Override
	public void notifyBuyerClub() {
		for(BuyerClub bc : clubs) {
			bc.update(getName(), getState());
		}
	}
	
}

class DenisSuarez implements ForSalePlayer{

	private final String name;
	private List<BuyerClub> clubs;
	private ForSalePlayerState state;
	
	public DenisSuarez() {
		name = DENIS_SUÁREZ;
		clubs = new ArrayList<>();
		state = ForSalePlayerState.FOR_SALE;
	}
	
	public ForSalePlayerState getState() {
		return state;
	}

	public void setState(ForSalePlayerState state) {
		this.state = state;
		notifyBuyerClub();
	}

	public String getName() {
		return name;
	}

	@Override
	public void addBuyerClub(BuyerClub bc) {
		clubs.add(bc);
		bc.update(getName(), getState());
	}

	@Override
	public void removeBuyerClub(BuyerClub bc) {
		clubs.remove(bc);
		bc.update(getName(), ForSalePlayerState.NOT_PLAY_TO_YOU);
	}

	@Override
	public void notifyBuyerClub() {
		for(BuyerClub bc : clubs) {
			bc.update(getName(), getState());
		}
	}
	
}

class BayernMunchen implements BuyerClub{

	private Map<String, ForSalePlayerState> playersToBuy;
	
	public BayernMunchen() {
		playersToBuy = new HashMap<>();
	}
	
	@Override
	public void update(String playerName, ForSalePlayerState state) {
		if(state.equals(ForSalePlayerState.NOT_PLAY_TO_YOU)) {
			playersToBuy.remove(playerName);
		}else {
			playersToBuy.put(playerName, state);
		}
	}
	
	public void whatDoYouKnowAbout(String playerName) {
		ForSalePlayerState playerState = playersToBuy.get(playerName);
		if(null != playerState) {
			System.out.println("Bayern knows that "+playerName+" is "+playerState);
		}else {
			System.out.println("Bayern doesn't know anything about "+playerName+"...");
		}
	}
	
}

class Arsenal implements BuyerClub{

	private Map<String, ForSalePlayerState> playersToBuy;
	
	public Arsenal() {
		playersToBuy = new HashMap<>();
	}
	
	@Override
	public void update(String playerName, ForSalePlayerState state) {
		if(state.equals(ForSalePlayerState.NOT_PLAY_TO_YOU)) {
			playersToBuy.remove(playerName);
		}else {
			playersToBuy.put(playerName, state);
		}
	}
	
	public void whatDoYouKnowAbout(String playerName) {
		ForSalePlayerState playerState = playersToBuy.get(playerName);
		if(null != playerState) {
			System.out.println("Arsenal know that "+playerName+" is "+playerState);
		}else {
			System.out.println("Arsenal like to know something about "+playerName+" but...");
		}
	}
	
}
