package structural;

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
	void singAntonioPuertaInPizjuan();
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
		LEVANTE, BARCELONA, SEVILLA, AT_MADRID, BARCELONA_B, SEVILLA_AT
	}
	
	boolean buyBroadcastTeamRights(TEAMS team, Double quantity);
}