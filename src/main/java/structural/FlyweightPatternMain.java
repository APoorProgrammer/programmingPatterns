package structural;

import static constants.Constants.CR7;
import static constants.Constants.FIGO;
import static constants.Constants.MODRIC;
import static constants.Constants.MODRIC_NATIONALITY;
import static constants.Constants.RONALDO;
import static constants.Constants.ZIDANE;
import static constants.Constants.ZIDANE_COACH_CHAMPIONS_NUMBER;
import static constants.Constants.PATH;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlyweightPatternMain {

	/**
	 * @author David de Miguel Otero
	 * 
	 * Flyweigth Pattern is used when it's necessary to reload or create a set of objects which cost in terms of
	 * memory or time are serious.
	 * Flyweigth Pattern choose separate a group of properties as intrinsic and other as extrinsic. First one musn't 
	 * change and the last one will change when produces the object's reload event.
	 * 
	 *  In our example we are going simulate a group of players than pass the ball between them.
	 *  They are going to have a shared properties as Boolean haveWonGoldenBall or String Club.
	 *  Other properties as name, will change.
	 *  We are recreated a set of passes between them and show in last time how only five instances are created.
	 *  We are going to create a Random class to generate randomly what player receive the ball.
	 *  Out five players will be Zinedine Zidane, CR7, Ronaldo, Figo and Modric 
	 */
	
	private static String[] playerNames = {CR7, ZIDANE, FIGO, MODRIC, RONALDO};
	
	public static void main(String[] args) {

		SimulateRondo rondo = SimulateRondo.get();
		String playerNameWhoPassTheBall = getPlayerName();
		
		for(int pass = 0;pass<100; pass++) {
			String playerNameWhoReceiveTheBall = getPlayerName();
			while(playerNameWhoReceiveTheBall.equals(playerNameWhoPassTheBall)) {
				playerNameWhoReceiveTheBall = getPlayerName();
			}
			PassBallPlayer player = rondo.getPlayer(playerNameWhoPassTheBall);
			player.passTheBall(playerNameWhoReceiveTheBall);
			playerNameWhoPassTheBall = playerNameWhoReceiveTheBall;
		}

		rondo.printInstances();
	}
	
	private static String getPlayerName() {
		int index = RandomValues.getValue(0, 5);
		return playerNames[index];
	}
	
	private static class RandomValues {
		public static int getValue(Integer min, Integer max) {
			return Double.valueOf((Math.random()*(max-min))).intValue();
		}
		
	}

}

abstract class PassBallPlayer{
	
	public boolean iHaveTheBall = false;
	
	abstract void passTheBall(String namePlayerReceiveBall);
	abstract void  printNumerOfInstances();
	public void setIHaveTheBall(boolean haveTheBall) {
		this.iHaveTheBall = haveTheBall;
	}
}

class Cristiano extends PassBallPlayer {

	public static int numberOfInstances = 0;
	Boolean iAmTheMostHandsomeAndRichPlayer;
	
	public Cristiano() {
		this.iAmTheMostHandsomeAndRichPlayer = true;
		numberOfInstances++;
	}
	
	public void passTheBall(String namePlayerReceiveBall) {
		System.out.println(CR7+" No, I don't pass MY ball to "+namePlayerReceiveBall);
	}
	
	public void  printNumerOfInstances() {
		System.out.println(CR7+": "+numberOfInstances);
	}
	
}

class Zidane extends PassBallPlayer {

	public static int numberOfInstances = 0;
	Integer championsAsCoach;
	
	public Zidane() {
		this.championsAsCoach = ZIDANE_COACH_CHAMPIONS_NUMBER;
		numberOfInstances++;

	}
	
	public void passTheBall(String namePlayerReceiveBall) {
		System.out.println(ZIDANE+": I do my roulette and pass the ball to "+namePlayerReceiveBall);
	}
	
	public void  printNumerOfInstances() {
		System.out.println(ZIDANE+": "+numberOfInstances);
	}
	
}

class Modric extends PassBallPlayer {

	public static int numberOfInstances = 0;
	String nationality;
	
	public Modric() {
		this.nationality = MODRIC_NATIONALITY;
		numberOfInstances++;
	}
	
	public void passTheBall(String namePlayerReceiveBall) {
		System.out.println(MODRIC+": No, I don't pass MY ball to "+namePlayerReceiveBall);
	}
	
	public void  printNumerOfInstances() {
		System.out.println(MODRIC+": "+numberOfInstances);
	}
	
}

class Figo extends PassBallPlayer {

	public static int numberOfInstances = 0;
	Boolean iPlayToBarcelona;
	
	public Figo() {
		this.iPlayToBarcelona = true;
		numberOfInstances++;
	}
	
	public void passTheBall(String namePlayerReceiveBall) {
		System.out.println(FIGO+": I do a great dribbling and pass the ball to "+namePlayerReceiveBall);
	}
	
	public void  printNumerOfInstances() {
		System.out.println(FIGO+": "+numberOfInstances);
	}
	
}

class Ronaldo extends PassBallPlayer {

	public static int numberOfInstances = 0;
	Boolean valladolidPresident;
	
	public Ronaldo() {
		this.valladolidPresident = true;
		numberOfInstances++;
	}
	
	public void passTheBall(String namePlayerReceiveBall) {
		System.out.println(RONALDO+": I can score all goals I want but I prefer pass the ball to "+namePlayerReceiveBall);
	}
	
	public void  printNumerOfInstances() {
		System.out.println(RONALDO+": "+numberOfInstances);
	}
	
}

class SimulateRondo {
	
	private Map<String, PassBallPlayer> players;
	private static SimulateRondo simulateRondo;
	
	private SimulateRondo() {
		players = new HashMap<String, PassBallPlayer>();
	}
	
	public static SimulateRondo get() {
		if(null == simulateRondo) {
			simulateRondo = new SimulateRondo();
		}
		return simulateRondo;
	}
	
	public PassBallPlayer getPlayer(String name) {
		PassBallPlayer player = null;
		if(players.containsKey(name)) {
			player = players.get(name);
		}else {
			try {
				Class clazz = Class.forName(PATH+name);
				player = (PassBallPlayer)clazz.newInstance();
				players.put(name, player);
			}catch (Exception e) {
				System.out.println("Fail on creation player");
			}
		}
		//Change the state
		player.setIHaveTheBall(true);
		return player;
	}
	
	public void printInstances() {
		List<PassBallPlayer> playerList = new ArrayList<>(players.values());
		System.out.println("-------------------------------------------");
		System.out.println("--------------  INSTANCES  ----------------");
		System.out.println("-------------------------------------------");
		for(PassBallPlayer player : playerList) {
			player.printNumerOfInstances();
		}
	}
}



