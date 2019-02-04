package structural;

import static constants.Constants.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author David de Miguel Otero
 * 
 * This pattern allows us to use a class to represent another one.
 * Could be many types of proxies, virtual, remote or protection.
 * 
 * Proxy class wraps another class which have certain previous logic or has a expensive cost to create.
 * 
 * In our example we are going to create a FootballClub class that wants to sign up a FootballPlayer but to do that
 * FootballClub previously has to make a offer and will be the AgentClass(Proxy) if considered it or not to allow
 * FootballPlayer sign to this FootballClub. 
 *
 */
public class ProxyPatternMain {

	public static void main(String[] args) {
		FootballClub club = new FootballClub(CLUB_NAME);
		System.out.println("If we offer a salary under the minium to accept...");
		club.signPlayer(MIN_OFFER_TO_SIGN_PEPE-1, new FootballAgent(PEPE));
		System.out.println();
		System.out.println("Try again...");
		club.signPlayer(MIN_OFFER_TO_SIGN_CR7, new FootballAgent(CRISTINAO_RONALDO));
		System.out.println();
		System.out.println("Try with player which nobody can sign...");
		club.signPlayer(MIN_OFFER_TO_SIGN_CR7, new FootballAgent(UNKNOWN_PLAYER));
	}

}

interface FootballPlayer {
	public void signNewAgreement(Double offer,String clubName);
}

class ConcreteFootballPlayer implements FootballPlayer {
	
	private String name;
	
	public ConcreteFootballPlayer(String name) {
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public void signNewAgreement(Double offer,String clubName) {
		StringBuilder text = new StringBuilder(getName()+" SAYS: I will play to "); 
		System.out.println(text.append(clubName).append(" because they sign me by ").append(offer).append("€"));
	}
}

class FootballAgent implements FootballPlayer{
	
	private String playerName;
	
	Map<String, Double> agentMinOfferPlayers;
	Map<String, ConcreteFootballPlayer> agentPlayers;
	ConcreteFootballPlayer concreteFootballPlayer;
	
	public FootballAgent(String playerName) {
		this.playerName = playerName;
		agentMinOfferPlayers = new HashMap<>();
		agentMinOfferPlayers.put(PEPE, MIN_OFFER_TO_SIGN_PEPE);
		agentMinOfferPlayers.put(DIEGO_COSTA, MIN_OFFER_TO_SIGN_DIEGO_COSTA);
		agentMinOfferPlayers.put(CRISTINAO_RONALDO, MIN_OFFER_TO_SIGN_CR7);
		agentPlayers = new HashMap<>();
	}
	
	private void proxyCheck(String playerName) {
		if(null == agentPlayers.get(playerName)) {
			concreteFootballPlayer = new ConcreteFootballPlayer(playerName);
			agentPlayers.put(playerName, concreteFootballPlayer);
		}
	}
	
	private Double getMinPlayerOffer(String playerName, Double offer){
		Double minOffer = agentMinOfferPlayers.get(playerName);
		if(null == minOffer) {
			System.out.println("AGENT SAYS: This player is not represent by me...");
		}
		return (offer+1);
	}
	
	@Override
	public void signNewAgreement(Double offer, String clubName) {
		proxyCheck(playerName);
		if(offer<getMinPlayerOffer(playerName, offer)) {
			System.out.println("AGENT SAYS: The offer must be higher!!!");
		}else {
			agentPlayers.get(playerName).signNewAgreement(offer, clubName);
		}
	}
	
}

class FootballClub {
	
	private String name;
	
	public FootballClub(String name) {
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	
	public void signPlayer(Double offer, FootballPlayer player) {
		player.signNewAgreement(offer, getName());
	}
}
