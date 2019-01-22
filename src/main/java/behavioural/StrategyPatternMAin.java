package behavioural;

public class StrategyPatternMAin {

	/**
	 * 
	 * @author David de Miguel Otero
	 * 
	 * Strategy pattern allows us to determinate in runtime execution what implementation of an interface
	 * we want to use.
	 * 
	 * In our example we are going to set diverse behaviors in the same situation, shot a free-kick, and
	 * depends on who throw the ball(classes which implements the interface FreeKick) the result will be different.
	 * 
	 * Go on...
	 * 
	 */
	
	public static void main(String[] args) {

		Team team = new Team();
		//Our team receive a fault. And we have the oportunity to score a goal.
		//What happens depends on if the freekick is thrown by CR7 or Sergio Ramos...
		team.freeKick(new CR7());
		team.freeKick(new SergioRamos());
	}

}

class Team {
	
	void freeKick(Player player) {
		player.shot();
	}
	
}

interface Player {
	void shot();
}

class CR7 implements Player{
	public void shot() {
		System.out.println("CR7's shot: I'm CR7 the best player in the universe. I hit very strong the ball and it go to the wall or out of the pitch");
		System.out.println();
	}
}



class SergioRamos implements Player {
	public void shot() {
		System.out.println("Sergio Ramos's shot: I score a fantastic goal. Fuck you Neuer and Lovren!!!");
		System.out.println();
	}
}