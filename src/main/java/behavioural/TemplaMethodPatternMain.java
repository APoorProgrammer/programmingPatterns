package behavioural;

public class TemplaMethodPatternMain {

	/**
	 * 
	 * @author David de Miguel Otero
	 * 
	 * Template method pattern allow us to define a script of steps and defer this step's definition
	 * in subclasses
	 * 
	 * We are going to use an example about how a template method pattern is used when we have two classes 
	 * represents two concepts nearly.
	 * We create an abstract class named SportEvent which implements the Template Method and will have a 
	 * steps series.
	 * After that, we created two classes BasketballSportEvent and FootballSportEvent each them defines 
	 * specifically each step.
	 */
	
	public static void main(String[] args) {

	}
	
}

abstract class SportEvent{
	
	abstract void initEvent();
	
	abstract void intermediate();
	
	abstract void endEvent();
	
	public void celebrateEvent() {
		initEvent();
		intermediate();
		endEvent();
	}
	
}

class BasketballEvent extends SportEvent{

	@Override
	void initEvent() {
		System.out.println("All players are introduced by th speaker");
	}

	@Override
	void intermediate() {
		System.out.println("Audience take a break");
	}

	@Override
	void endEvent() {
		System.out.println("All players come back to the changing room");
		
	}
	
}

class FootballEvent extends SportEvent{

	@Override
	void initEvent() {
		System.out.println("All players go out to the football pitch in a single file by team");
	}

	@Override
	void intermediate() {
		System.out.println("Audience take a break");
	}

	@Override
	void endEvent() {
		System.out.println("The team which have lost the match protest to the referee. ");
	}
	
}
