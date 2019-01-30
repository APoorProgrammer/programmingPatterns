package creational;

import java.util.List;

import static constants.Constants.*;

/**
 * 
 * @author David de Miguel Otero
 *
 * Nowadays we have a library as Lombok and we don't necessary handwriting it but is good know how to works.
 * It allow us to create a constructor to certain object which have a consider number of parameters.
 * 
 * In our example we have a class that have too many arguments and we decided to use Builder pattern to solve it.
 *
 */
public class BuilderPatternMain {

	public static void main(String[] args) {
		SuccessfulTeam successfulTeam = new SuccessfulTeam.Builder(NEW_TEAM).build();
		//In our example you can observe is mandatory at least include the team's name to create an instance of object.
		System.out.println(successfulTeam.toString());
	}

}

class SuccessfulTeam {
	
	private String name;
	private Double budget;
	private YouthTeam youthTeam;
	private Integer supporters;
	private String stadium;
	private List<PlayerTeam> players;
	
	private SuccessfulTeam() {}
	
	public static class Builder {
		
		private String name;
		private Double budget;
		private YouthTeam youthTeam;
		private Integer supporters;
		private String stadium;
		private List<PlayerTeam> players;
		
		public Builder(String name) {
			this.name = name;
		}
		public Builder setBudget(Double budget) {
			this.budget = budget;
			return this;
		}
		public Builder setYouthTeam(YouthTeam youthTeam) {
			this.youthTeam = youthTeam;
			return this;
		}
		public Builder setSupporters(Integer supporters) {
			this.supporters = supporters;
			return this;
		}
		public Builder setStadium(String stadium) {
			this.stadium = stadium;
			return this;
		}
		public Builder setPlayers(List<PlayerTeam> players) {
			this.players = players;
			return this;
		}
		public SuccessfulTeam build() {
			SuccessfulTeam myTeam = new SuccessfulTeam();
			myTeam.setBudget(this.budget);
			myTeam.setName(this.name);
			myTeam.setYouthTeam(this.youthTeam);
			myTeam.setSupporters(this.supporters);
			myTeam.setStadium(this.stadium);
			myTeam.setPlayers(this.players);
			return myTeam;
		}
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public YouthTeam getYouthTeam() {
		return youthTeam;
	}

	public void setYouthTeam(YouthTeam youthTeam) {
		this.youthTeam = youthTeam;
	}

	public Integer getSupporters() {
		return supporters;
	}

	public void setSupporters(Integer supporters) {
		this.supporters = supporters;
	}

	public String getStadium() {
		return stadium;
	}

	public void setStadium(String stadium) {
		this.stadium = stadium;
	}

	public List<PlayerTeam> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayerTeam> players) {
		this.players = players;
	}
	
	@Override
	public String toString() {
		return "Our team name is: "+this.name.toUpperCase();
	}
	
}

interface YouthTeam {
}

class MyYouthTeam implements YouthTeam{
}

interface PlayerTeam {
}

class MyPlayerTeam implements PlayerTeam{
}
