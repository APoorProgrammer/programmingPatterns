package creational;

public class AbstractFactoryPatternMain {

	/**
	 * 
	 * @author David de Miguel Otero
	 * 
	 * This pattern is used when we have similar classes that creates similar objects but we don't specify a concrete 
	 * class for any one of them.
	 * 
	 * In our example we want a factory to get a team that have which is necessary to win Champions League.
	 * We create RealMadrid's factory(first implementation of factory) and 
	 * Bayern Munich's factory(other implementation of factory) that extends 
	 * the abstract factory ChampionsLeagueWinnerTeam.
	 * 
	 * A winner team needs two things a great goal keeper which implements GreatSavedGoalKeeper(first product) 
	 * and a great forward which implements GreatScoreGoalsForward(second product).
	 * This needs will be implemented by, in Real Madrid's case, Keylor Navas and CR7 classes, and in Bayern Munich's case
	 * by Neuer and Lewandovski classes.
	 * 
	 */
	public static void main(String[] args) {
		ChampionsLeagueWinnerTeam realMadrid = new RealMadrid();
		System.out.println("Why Real Madrid can win the Champions league?: ");
		realMadrid.greatForward().score();
		realMadrid.greatGoalKeeper().saveBall();
		System.out.println();
		ChampionsLeagueWinnerTeam bayernMunich = new BayernMunich();
		System.out.println("Why Bayern Munich can win the Champions league?: ");
		bayernMunich.greatForward().score();
		bayernMunich.greatGoalKeeper().saveBall();
	}

}

interface GreatSavedGoalKeeper {
	public void saveBall();
}

interface GreatScoreGoalsForward {
	public void score();
}

abstract class ChampionsLeagueWinnerTeam{
	
	public abstract GreatSavedGoalKeeper greatGoalKeeper();
	
	public abstract GreatScoreGoalsForward greatForward();
}

class CristianoRonaldo implements GreatScoreGoalsForward {
	@Override
	public void score() {
		System.out.println("I'm CR7 and I can score 17 goals in a season.");
	}
}

class KeylorNavas implements GreatSavedGoalKeeper {
	@Override
	public void saveBall() {
		System.out.println("I'm Keylor Navas and I can be the least unbeaten goal keeper in three champions.");
	}
}

class RealMadrid extends ChampionsLeagueWinnerTeam {
	@Override
	public GreatSavedGoalKeeper greatGoalKeeper() {
		return new KeylorNavas();
	}
	@Override
	public GreatScoreGoalsForward greatForward() {
		return new CristianoRonaldo();
	}
}

class Lewandovski implements GreatScoreGoalsForward {
	@Override
	public void score() {
		System.out.println("I'm Lewandovski and I can score 4 goals to the best team in the world in the same match.");
	}
}

class Neuer implements GreatSavedGoalKeeper {
	@Override
	public void saveBall() {
		System.out.println("I'm Neuer and I can socre 17 goals in a season.");
	}
}

class BayernMunich extends ChampionsLeagueWinnerTeam{
	@Override
	public GreatSavedGoalKeeper greatGoalKeeper() {
		return new Neuer();
	}
	@Override
	public GreatScoreGoalsForward greatForward() {
		return new Lewandovski();
	}
}
