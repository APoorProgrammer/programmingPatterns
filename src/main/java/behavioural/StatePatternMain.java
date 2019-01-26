package behavioural;

/**
 * 
 * @author David de Miguel Otero
 * 
 * State Pattern allows change the behavior of an object(the context) according the state of his state's dependency.
 * When this state change, we can use the same object with a total distinct behavior.
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
	FitnessForm fitnessForm;
	
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

	public FitnessForm getFitnessForm() {
		return fitnessForm;
	}

	public void setFitnessForm(FitnessForm fitnessForm) {
		this.fitnessForm = fitnessForm;
	}
	
}

class Fit implements FitnessForm{

	@Override
	public void improveYourlevel(TeamPlayer player) {
		System.out.println("Can't be better!!!");
	}

	@Override
	public void iThinkSomethingBadHappens(TeamPlayer player) {
		System.out.println("Oh, oh...!!!");
		player.setFitnessForm(new Improving());
	}

	@Override
	public void getStatus() {
		System.out.println("I run 100Km/h!!!");
	}
}

class Improving implements FitnessForm{

	@Override
	public void improveYourlevel(TeamPlayer player) {
		System.out.println("Yeah, I'm in my top form!!!");
		player.setFitnessForm(new Fit());
	}

	@Override
	public void iThinkSomethingBadHappens(TeamPlayer player) {
		System.out.println("It get worse... ");
		player.setFitnessForm(new LowForm());
	}

	@Override
	public void getStatus() {
		System.out.println("I run 75Km/h!!!");
		
	}
}

class LowForm implements FitnessForm{

	@Override
	public void improveYourlevel(TeamPlayer player) {
		System.out.println("Good... ");
		player.setFitnessForm(new Improving());
	}

	@Override
	public void iThinkSomethingBadHappens(TeamPlayer player) {
		getStatus();
	}

	@Override
	public void getStatus() {
		System.out.println("Can't be worse!!!");
	}
}