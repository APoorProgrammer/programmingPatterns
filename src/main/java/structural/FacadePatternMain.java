package structural;

import static constants.Constants.ACCEPTED;
import static constants.Constants.ATMADRID_MIN_OFFER;
import static constants.Constants.AT_MADRID_CRYING;
import static constants.Constants.BARCELONA_MIN_OFFER;
import static constants.Constants.BARCELONA_VINDICATION;
import static constants.Constants.LEVANTE_CELEBRATION;
import static constants.Constants.MAFIA_COMMISSION;
import static constants.Constants.SEVILLA_CELEBRATION;
import static constants.Constants.SEVILLA_MIN_OFFER;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class FacadePatternMain {

	/**
	 * 
	 * @author David de Miguel Otero
	 * 
	 * This pattern is used when we want to centralize a set of similar interfaces creating an unique interface
	 * which unified the susbsystems.
	 * 
	 * In our case we are simulating a negotiation between TV platform(the client) which wants to buy 
	 * LaLiga's broadcast's rights
	 * In this example there will be many teams with similar interface and instead of use each them we are going to
	 * create a LaLiga interface which works as a facade between the TV buyer and the teams
	 * 
	 */
	
	public static void main(String[] args) {

		LaLigaNegotiator laLigaNegotiator = new LaLigaNegotiator();
		
		laLigaNegotiator.buyBroadcastTeamRights(LaLiga.TEAMS.BARCELONA, BARCELONA_MIN_OFFER-9);
		laLigaNegotiator.buyBroadcastTeamRights(LaLiga.TEAMS.SEVILLA, SEVILLA_MIN_OFFER);
		laLigaNegotiator.buyBroadcastTeamRights(LaLiga.TEAMS.BARCELONA, BARCELONA_MIN_OFFER+18);
		laLigaNegotiator.buyBroadcastTeamRights(LaLiga.TEAMS.AT_MADRID, ATMADRID_MIN_OFFER+1);
		laLigaNegotiator.buyBroadcastTeamRights(LaLiga.TEAMS.LEVANTE, 100.5);
		laLigaNegotiator.buyBroadcastTeamRights(LaLiga.TEAMS.AT_MADRID, ATMADRID_MIN_OFFER+MAFIA_COMMISSION);

	}

}

interface Team {
}

interface SevillaTeam {
	void celebrateEuropaLeague();
}

interface BarcelonaTeam {
	void singIndependenciaInCampNou();
}

interface LevanteTeam {
	void calebratePromotion();
}

interface AtMadridTeam {
	void cryingMinute93();
}

interface LaLiga {
	public enum TEAMS {
		LEVANTE, BARCELONA, SEVILLA, AT_MADRID
	}
	boolean buyBroadcastTeamRights(TEAMS team, Double quantity);
}

class Sevilla implements SevillaTeam, Team {

	public static boolean getBroadcastTeamRights(Double quantity) {
		if(quantity < SEVILLA_MIN_OFFER) {
			System.out.println("Not enough!!");
			return !ACCEPTED;
		}
		System.out.println("Trato y olé!!");
		return ACCEPTED;
	}

	@Override
	public void celebrateEuropaLeague() {
		System.out.println(SEVILLA_CELEBRATION);
	}
	
}

class Barcelona implements BarcelonaTeam, Team {

	public static boolean someoneOffersBuyYourRigths(Double quantity) {
		if(quantity <= BARCELONA_MIN_OFFER) {
			System.out.println("We want more");
			return !ACCEPTED;
		}
		System.out.println("Ok!!!, with this money we will pay Messi's taxes");
		return ACCEPTED;
	}

	public void singIndependenciaInCampNou() {
		System.out.println(BARCELONA_VINDICATION);
	}
	
}

class AtMadrid implements AtMadridTeam, Team {

	public static boolean sellOurBroadcastRights(Double quantity) {
		if(quantity < (ATMADRID_MIN_OFFER+MAFIA_COMMISSION)) {
			System.out.println("We don't accept that");
			return !ACCEPTED;
		}
		System.out.println("More money to Cerezo and MAG...");
		return ACCEPTED;	}

	@Override
	public void cryingMinute93() {
		System.out.println(AT_MADRID_CRYING);
	}
	
}

class Levante implements LevanteTeam, Team {

	public static boolean buyRigths(Double quantity) {
		System.out.println("Yeah!!!");
		return ACCEPTED;
	}

	@Override
	public void calebratePromotion() {
		System.out.println(LEVANTE_CELEBRATION);
		
	}
	
}

class LaLigaNegotiator implements LaLiga {

	@Override
	public boolean buyBroadcastTeamRights(TEAMS team, Double quantity) {
		System.out.print(team.toString()+": ");
		if(team.equals(TEAMS.BARCELONA)) {
			return Barcelona.someoneOffersBuyYourRigths(quantity);
		}
		if(team.equals(TEAMS.AT_MADRID)) {
			return AtMadrid.sellOurBroadcastRights(quantity);
		}
		if(team.equals(TEAMS.SEVILLA)) {
			return Sevilla.getBroadcastTeamRights(quantity);
		}
		if(team.equals(TEAMS.LEVANTE)) {
			return Levante.buyRigths(quantity);
		}
		return false;
	}
}

