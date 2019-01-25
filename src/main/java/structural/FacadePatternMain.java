package structural;

import static constants.Constants.*;

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

	}

}

interface SevillaTeam {
	boolean getBroadcastTeamRights(Double quantity);
	void celebrateEuropaLeague();
}

interface BarcelonaTeam {
	boolean someoneOffersBuyYourRigths(Double quantity);
	void singIndependenciaInCampNou();
}

interface LevanteTeam {
	boolean buyRigths(Double quantity);
	void calebratePromotion();
}

interface AtMadridTeam {
	boolean sellOurBroadcastRights(Double quantity);
	void cryingMinute93();
}

interface LaLiga {
	public enum TEAMS {
		LEVANTE, BARCELONA, SEVILLA, AT_MADRID
	}
	boolean buyBroadcastTeamRights(TEAMS team, Double quantity);
}

class Sevilla implements SevillaTeam {

	@Override
	public boolean getBroadcastTeamRights(Double quantity) {
		if(quantity < SEVILLA_MIN_OFFER) {
			return !ACCEPTED;
		}
		return ACCEPTED;
	}

	@Override
	public void celebrateEuropaLeague() {
		System.out.println(SEVILLA_CELEBRATION);
	}
	
}

class Barcelona implements BarcelonaTeam {

	@Override
	public boolean someoneOffersBuyYourRigths(Double quantity) {
		if(quantity <= BARCELONA_MIN_OFFER) {
			return !ACCEPTED;
		}
		return ACCEPTED;
	}

	@Override
	public void singIndependenciaInCampNou() {
		System.out.println(BARCELONA_VINDICATION);
	}
	
}

class AtMadrid implements AtMadridTeam {

	@Override
	public boolean sellOurBroadcastRights(Double quantity) {
		if(quantity < (ATMADRID_MIN_OFFER+MAFIA_COMMISSION)) {
			return !ACCEPTED;
		}
		return ACCEPTED;	}

	@Override
	public void cryingMinute93() {
		System.out.println(AT_MADRID_CRYING);
	}
	
}

class Levante implements LevanteTeam {

	@Override
	public boolean buyRigths(Double quantity) {
		return ACCEPTED;
	}

	@Override
	public void calebratePromotion() {
		System.out.println(LEVANTE_CELEBRATION);
		
	}
	
}

