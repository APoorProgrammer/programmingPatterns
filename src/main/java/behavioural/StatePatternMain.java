package behavioural;

import static constants.Constants.*;

/**
 * 
 * @author David de Miguel Otero
 * 
 * State Pattern allows change the behavior of an object(the context) according the value of its state's dependency.
 * When this state change, we can use the same object(the context) with a total distinct behavior.
 * 
 * In our case, we are going to have Team Player(the context) who are going to have a fitness status(the state).
 * His state could change among Fit, Improving, LowForm and Injured and the behavior of player will change.  
 * 
 */
public class StatePatternMain {

	public static void main(String[] args) {
		TeamPlayer teamPlayer = new TeamPlayer(new Improving());
		teamPlayer.howIFeel();
		teamPlayer.iFeelBetter();
		teamPlayer.howIFeel();
		teamPlayer.iFeelBetter();
		teamPlayer.howIFeel();
		teamPlayer.iFeelWorse();
		teamPlayer.howIFeel();
		teamPlayer.iFeelWorse();
		teamPlayer.howIFeel();
	}

}

interface FitnessForm {
	
	void improveYourlevel(TeamPlayer player);
	void iThinkSomethingBadHappens(TeamPlayer player);
	void getStatus();
	
}


class TeamPlayer {
	private FitnessForm fitnessForm;
	
	TeamPlayer(FitnessForm fitnessForm){
		this.fitnessForm = fitnessForm;
	}
	
	public void howIFeel() {
		fitnessForm.getStatus();
	}
	
	public void iFeelBetter() {
		fitnessForm.improveYourlevel(this);
	}
	
	public void iFeelWorse() {
		fitnessForm.iThinkSomethingBadHappens(this);
	}
	
	public void setFitnessForm(FitnessForm fitnessForm) {
		this.fitnessForm = fitnessForm;
	}
	
}

class Fit implements FitnessForm{

	@Override
	public void improveYourlevel(TeamPlayer player) {
		System.out.println("I'm in my best fitness form. I can't be better!!!");
	}

	@Override
	public void iThinkSomethingBadHappens(TeamPlayer player) {
		System.out.println("I think i have got worse...!!!");
		player.setFitnessForm(new Improving());
	}

	@Override
	public void getStatus() {
		System.out.println(FIT+"Ouh!!!, I run at 25Km/h!!!");
	}
}

class Improving implements FitnessForm{

	@Override
	public void improveYourlevel(TeamPlayer player) {
		System.out.println("Yeah, I think I have reached my best fitness form !!!");
		player.setFitnessForm(new Fit());
	}

	@Override
	public void iThinkSomethingBadHappens(TeamPlayer player) {
		System.out.println("I have to train more...");
		player.setFitnessForm(new LowForm());
	}

	@Override
	public void getStatus() {
		System.out.println(IMPROVING+"I run 18Km/h!!!");
		
	}
}

class LowForm implements FitnessForm{

	@Override
	public void improveYourlevel(TeamPlayer player) {
		System.out.println("Good. This starts to improve... ");
		player.setFitnessForm(new Improving());
	}

	@Override
	public void iThinkSomethingBadHappens(TeamPlayer player) {
		System.out.println("Oh, oh, something brokes...");
		player.setFitnessForm(new LowForm());
	}

	@Override
	public void getStatus() {
		System.out.println(LOW_FORM+"I only can walk...this is a disaster");
	}
}

class Injured implements FitnessForm {

	@Override
	public void improveYourlevel(TeamPlayer player) {
		System.out.println("Well, I'm not injured yet... ");
		player.setFitnessForm(new LowForm());
	}

	@Override
	public void iThinkSomethingBadHappens(TeamPlayer player) {
		System.out.println("I'm in my worst fitness form. I can't be worse!!!");
	}

	@Override
	public void getStatus() {
		System.out.println(INJURED+"I'm injured, couldn't do anything.");
	}
	
}