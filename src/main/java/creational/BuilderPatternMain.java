package creational;

public class BuilderPatternMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SuccessfulTeam successfulTeam = new SuccessfulTeam.Builder(5000.00).build();
	}

}

class SuccessfulTeam {
	
	private Double budget;
	private Boolean youthTeam;
	private Integer parters;
	private String stadiumName;
	
	private SuccessfulTeam() {};
	
	public static class Builder {
		private Double budget;
		private Boolean youthTeam;
		private Integer parters;
		private String stadiumName;
		
		public Builder(Double budget) {
			this.budget = budget;
		}
		public Builder setYouthTeam(Boolean youthTeam) {
			this.youthTeam = youthTeam;
			return this;
		}
		public Builder setParters(Integer parters) {
			this.parters = parters;
			return this;
		}
		public Builder setStadiumName(String stadiumName) {
			this.stadiumName = stadiumName;
			return this;
		}
		
		public SuccessfulTeam build() {
			SuccessfulTeam successfulTeam = new SuccessfulTeam();
			successfulTeam.setBudget(this.budget);
			successfulTeam.setParters(this.parters);
			successfulTeam.setStadiumName(this.stadiumName);
			successfulTeam.setYouthTeam(this.youthTeam);
			return successfulTeam;
		}
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public Integer getParters() {
		return parters;
	}

	public void setParters(Integer parters) {
		this.parters = parters;
	}

	public String getStadiumName() {
		return stadiumName;
	}

	public void setStadiumName(String stadiumName) {
		this.stadiumName = stadiumName;
	}

	public Boolean getYouthTeam() {
		return youthTeam;
	}

	public void setYouthTeam(Boolean youthTeam) {
		this.youthTeam = youthTeam;
	}
	
}