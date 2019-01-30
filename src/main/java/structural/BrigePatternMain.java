package structural;

public class BrigePatternMain {

	/**
	 * 
	 * @author David de Miguel Otero
	 * 
	 * This pattern is used to separate abstraction to implementation.
	 * We will have an abstracted class where will be extended to fine-grained details and it will be composed by
	 * a references, interfaces, that will be implemented too.
	 * With this schema we can separate the concrete implementation of the abstract class and the implementation of
	 * members who compose this abstraction.
	 * 
	 * In our example we have a abstract class that is a coach's introduction and the Team(abstract class), 
	 * President and Coach(Interfaces) that compounds the first one(Team) are decoupled. 
	 * 
	 */
	
	public static void main(String[] args) {
		President florentino = new FlorentinoPerez();
		Coach lopetegui = new Lopetegui();
		TeamCoachIntroduction realMadrid = new RealMadrid(florentino, lopetegui);
		realMadrid.coachIntroduction();
		//If change realMadrid's coach...
		Coach solari = new Solari();
		realMadrid.setCoach(solari);
		realMadrid.coachIntroduction();
	}

}

interface President {
	void speech();
}

interface Coach {
	void speech();
}

abstract class TeamCoachIntroduction {
	final President president;
	Coach coach;
	
	public TeamCoachIntroduction(President president, Coach coach){
		this.president=president;
		this.coach=coach;
	}
	
	public void coachIntroduction(){
		president.speech();
		coach.speech();
	}

	public President getPresident() {
		return president;
	}

	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}
	
}

class FlorentinoPerez implements President {
	@Override
	public void speech() {
		System.out.println("He always dreams to be Real Madrid's coach");
	}
}

class Lopetegui implements Coach {
	@Override
	public void speech() {
		System.out.println("I'm Julen Lopetegui and I always dreams to be Real Madrid's coach");
	}
}

class Solari implements Coach {
	@Override
	public void speech() {
		System.out.println("I'm Santiago Solari and I always dreams to be Real Madrid's coach");
	}
}

class RealMadrid extends TeamCoachIntroduction{
	
	public RealMadrid(President president, Coach coach){
		super(president, coach);
	}
	
	@Override
	public void coachIntroduction(){
		System.out.println("Starts Real Madrid's coach introduction: ");
		super.coachIntroduction();
	}
}
