package structural;

public class DecoratorPatternMain {

	/**
	 * @author David de Miguel Otero
	 * 
	 * Decorator pattern allow us to add an extra behavior an object implements some interface.
	 * This way gets by creating a new class, The Decorator, which implements the same interface and 
	 * has this as a object's dependency.
	 * So all new classes extends the decorator will be able to implement the default behavior implemented and
	 * add an extra behavior.
	 * 
	 * In our example we are going to create a interface called GreatPlayer.
	 * This interface is going to have some methods which will be implemented by a class which name will be Maradona
	 * 
	 * But few years later, appears an other great player that will have the same characteristics than Maradona but 
	 * with better performance...this will be Messi.
	 * Messi appears implementing our Decorator class which will be ExtendingMaradona.
	 * Messi's class have the same behavior of Maradona, but with betters characteristics.
	 * 
	 * See what's happens...
	 * 
	 */
	
	public static void main(String[] args) {

		//We create a Maradona instance and show its abilities.
		GreatPlayer maradona = new Maradona();
		showGreatPlayerCharacteristics(maradona);
		//We create a Messi instance and show its abilities.
		GreatPlayer messi = new Messi(maradona);
		showGreatPlayerCharacteristics(messi);
		
		//As you can see we can extends in runtime execution the behaviour of GreatPlayer with Decorator pattern
		// MOVEMENTS: I have a great dribbling
		// SHOT: I have a great left shot
		// ASSIST: I'm very individualist
		// AWARDS: I have a golden ball

		// MOVEMENTS: I have a great dribbling but I do it very very fast 
		// SHOT: I have a great left shot and I score too many goals
		// ASSIST: I try to assist my partners
		// AWARDS: I have a golden ball and four more and five golden boots
	}
	
	public static void showGreatPlayerCharacteristics(GreatPlayer greatPlayer) {
		System.out.println("MOVEMENTS: "+greatPlayer.greatMovement());
		System.out.println("SHOT: "+greatPlayer.shot());
		System.out.println("ASSIST: "+greatPlayer.assist());
		System.out.println("AWARDS: "+greatPlayer.awards());
		System.out.println();
	}
}

interface GreatPlayer {
	
	String greatMovement();
	
	String shot();

	String assist();
	
	String awards();

}

class Maradona implements GreatPlayer {

	@Override
	public String greatMovement() {
		return "I have a great dribbling";
	}

	@Override
	public String shot() {
		return "I have a great left shot";
	}

	@Override
	public String assist() {
		return "I'm very individualist";
	}

	@Override
	public String awards() {
		return "I have a golden ball";
	}
	
}

abstract class ExtendingMaradona implements GreatPlayer {
	
	protected GreatPlayer greatPlayer;
	
	public ExtendingMaradona(GreatPlayer greatPlayer) {
		this.greatPlayer = greatPlayer;
	}
	
}

class Messi extends ExtendingMaradona {

	public Messi(GreatPlayer greatPlayer) {
		super(greatPlayer);
	}

	@Override
	public String greatMovement() {
		StringBuilder myMovement = new StringBuilder(greatPlayer.greatMovement());
		return myMovement.append(" but I do it very very fast ").toString();
	}

	@Override
	public String shot() {
		StringBuilder myShot = new StringBuilder(greatPlayer.shot());
		return myShot.append(" and I score too many goals").toString();
	}

	@Override
	public String assist() {
		return "I try to assist my partners";
	}

	@Override
	public String awards() {
		StringBuilder myAwards = new StringBuilder(greatPlayer.awards());
		return myAwards.append(" and four more and five golden boots").toString();
	}
	
}

