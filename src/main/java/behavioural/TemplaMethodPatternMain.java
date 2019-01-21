package behavioural;

import java.util.ArrayList;
import java.util.List;

import static constants.Constants.BASKETBALL;
import static constants.Constants.FOOTBALL;

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

		List<SportEvent> events = new ArrayList<>();

		SportEvent basketabllEvent = new BasketballEvent(BASKETBALL);
		SportEvent footballEvent = new FootballEvent(FOOTBALL);
		
		events.add(basketabllEvent);
		events.add(footballEvent);
		
		events.stream().forEach(x -> {x.celebrateEvent();System.out.println();});
		
		//We can see the structure of the script in a sport event is similar but is not the same
		//Welcome to a BASKETBALL match!!!
		//All players are introduced by th speaker
		//Audience take a break
		//All players come back to the changing room

		//Welcome to a FOOTABLL match!!!
		//All players go out to the football pitch in a single file by team
		//Audience take a break
		//The team which have lost the match protest to the referee. 
	}
	
}

abstract class SportEvent{
	
	private String name;
	
	SportEvent(String name){
		this.name=name;
	}
	
    void nameEvent(String name) {
		System.out.println("Welcome to a "+name+" match!!!");
	}

	abstract void initEvent();
	
	abstract void intermediate();
	
	abstract void endEvent();
	
	public void celebrateEvent() {
		nameEvent(name);
		initEvent();
		intermediate();
		endEvent();
	}
	
}

class BasketballEvent extends SportEvent{

	BasketballEvent(String name){
		super(name);
	}
	
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

	FootballEvent(String name){
		super(name);
	}
	
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
