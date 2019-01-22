package behavioural;

import static constants.Constants.MAX;
import static constants.Constants.MAX_PAYED_OFFER;
import static constants.Constants.MIN;
import static constants.Constants.MIN_AVERAGE;
import static constants.Constants.SIGNED;

import java.text.DecimalFormat;


public class ChainOfResponsibilityPatternMain {

	/**
	 * 
	 * @author David de Miguel Otero
	 * 
	 * Chain of responsibility pattern allow us that a sequence of objects that are linked treats a request.
	 * 
	 * In our example we are going to receive a possible sign in player to Real Madrid team. 
	 * First is only a option. It is going to have same qualities and abilities.
	 * It's going to pass through an scout's exam and then it's going to be evaluated by the football's director.
	 * At last the player was evaluated by the board in cost terms, in concrete by the president.
	 * 
	 * We are going to simulate the hiring of Messi by F.C. Barcelona.
	 * We implemented a Random class who simulate the assessment of each link in the chain.
	 * 
	 */
	
	public static void main(String[] args) {
		
		Messi player = new Messi();
		HiringChain scout = new Scout();
		HiringChain footaballDirector = new FootaballDirector();
		HiringChain president = new President();
		
		scout.setNextLinkChain(footaballDirector);
		footaballDirector.setNextLinkChain(president);
		president.setNextLinkChain(null);
		
		scout.callNextEvaluator(player);
	}

}

class Messi {
	
	private Double dribbling;
	private Double shot;
	private Double assist;
	private Double cost;
	
	public Double getDribbling() {
		return dribbling;
	}
	public void setDribbling(Double dribbling) {
		this.dribbling = dribbling;
	}
	public Double getShot() {
		return shot;
	}
	public void setShot(Double shot) {
		this.shot = shot;
	}
	public Double getAssist() {
		return assist;
	}
	public void setAssist(Double assist) {
		this.assist = assist;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	
}

interface HiringChain {
	
	//we can use a constructor to set the next link in the chain but then it isn't reusable
	void setNextLinkChain(HiringChain next);
	void callNextEvaluator(Messi player);
	boolean evaluatePlayer(Messi player);
	
}

class Scout implements HiringChain {

	private HiringChain next;
	private final DecimalFormat df = new DecimalFormat();
	
	@Override
	public void setNextLinkChain(HiringChain next) {
		this.next = next;
	}

	@Override
	public void callNextEvaluator(Messi player) {
		evaluatePlayer(player);
		if(null != next) {
			next.callNextEvaluator(player);
		}else {
			System.out.println("Messi won't be signed by F.C.Barcelona");
			System.out.println();
		}
	}

	@Override
	public boolean evaluatePlayer(Messi player) {
		System.out.println("Scout says: ");
		player.setCost(Math.floor(RandomValues.getValue(MIN, MAX)));
		player.setDribbling(Math.floor(RandomValues.getValue(MIN, MAX)));
		System.out.println("Messi has a DRIBLLING score of "+player.getDribbling());
		player.setShot(Math.floor(RandomValues.getValue(MIN, MAX)));
		System.out.println("Messi has a SHOT score of "+player.getShot());
		player.setAssist(Math.floor(RandomValues.getValue(MIN, MAX)));
		System.out.println("Messi has an ASSIST score of "+player.getAssist());		
		System.out.println();
		return true;
	}
	
	private static class RandomValues {
		
		public static Double getValue(Double min, Double max) {
			return Math.random()*(max-min)+1;
		}
		
	}
	
}

class FootaballDirector implements HiringChain {
	
	private HiringChain next;

	@Override
	public void setNextLinkChain(HiringChain next) {
		this.next = next;
	}

	@Override
	public void callNextEvaluator(Messi player) {
		if(evaluatePlayer(player) && null != next) {
			next.callNextEvaluator(player);
		}else {
			System.out.println("Messi won't be signed by F.C.Barcelona");
			System.out.println();
		}		
	}
	
	@Override
	public boolean evaluatePlayer(Messi player) {
		Double average = (player.getDribbling()+player.getAssist()+player.getShot())/3;
		System.out.println("Football Director says: ");
		System.out.println("Messi has an AVERAGE score of "+average+" and has to have an minium average of "+MIN_AVERAGE+" to hire to us");
		System.out.println();
		if(average>=MIN_AVERAGE) {
			return SIGNED;
		}
		return !SIGNED;
	}
	
}

class President implements HiringChain {
	
	private HiringChain next;

	@Override
	public void setNextLinkChain(HiringChain next) {
		this.next = next;
	}

	@Override
	public void callNextEvaluator(Messi player) {
		if(evaluatePlayer(player)) {
			System.out.println("We hire D10S!!!!");
		}else {
			System.out.println("Fire the scout, please...");
			System.out.println();
		}		
	}
	
	@Override
	public boolean evaluatePlayer(Messi player) {
		System.out.println("President says: ");
		System.out.println("Messi has an COST of "+player.getCost()+" and has to have an maxium cost of "+MAX_PAYED_OFFER+" to hire to us");
		if(MAX_PAYED_OFFER >= player.getCost()) {
			return SIGNED;
		}
		return !SIGNED;
	}
	
}

