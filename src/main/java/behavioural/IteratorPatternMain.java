package behavioural;

import java.util.ArrayList;
import java.util.List;

import behavioural.DiversePlayerTeam.POSITION;

public class IteratorPatternMain {

	/**
	 * 
	 * @author David de Miguel Otero
	 * 
	 * This pattern is used to traverse a object's sequence without exposing the underlying data structure.
	 * 
	 * In our example we are going to create a iterator that allow to transverse a list of players,
	 * but we only want to print the defenses.
	 * We have to create a general class to represent all player and then create a interface iterator(PlayerIterator) 
	 * and its implementation to traverse this collection of players and what conditions we get this elements.
	 * There will be a class who implements interface to create a Iterator(ManagePlayer) 
	 * but at all times we don't know how the iterator is implemented.
	 * Last we create a ManageDefenses to implements the select what implementation of Iterator class
	 * we want. 
	 * 
	 */
	
	public static void main(String[] args) {
		List<DiversePlayerTeam> players = initPlayerList();
		
		ManagePlayer onlyDefenses = new ManageTeamDefenses(players);
		PlayerIterator iterator = onlyDefenses.createIterator();
		while(iterator.hasNext()) {
			DiversePlayerTeam player = (DiversePlayerTeam)iterator.next();
			player.toString();
			System.out.println(player.toString());
		}
	}
	
	public static List<DiversePlayerTeam> initPlayerList() {
		List<DiversePlayerTeam> players = new ArrayList<>();
		DiversePlayerTeam pepe = new DiversePlayerTeam.Builder(POSITION.DEFENSE, "Pepe").build();
		players.add(pepe);
		DiversePlayerTeam modric = new DiversePlayerTeam.Builder(POSITION.MIDFIELD, "Modric").build();
		players.add(modric);
		DiversePlayerTeam nacho = new DiversePlayerTeam.Builder(POSITION.DEFENSE, "Nacho").build();
		players.add(nacho);
		DiversePlayerTeam ramos= new DiversePlayerTeam.Builder(POSITION.DEFENSE, "Ramos").build();
		players.add(ramos);
		DiversePlayerTeam varane = new DiversePlayerTeam.Builder(POSITION.DEFENSE, "Varane").build();
		players.add(varane);
		DiversePlayerTeam bale = new DiversePlayerTeam.Builder(POSITION.FORWARD, "Bale").build();
		players.add(bale);
		DiversePlayerTeam lucazVazquez = new DiversePlayerTeam.Builder(POSITION.FORWARD, "Lucas Vázquez").build();
		players.add(lucazVazquez);	
		return players;
	}
}

class DiversePlayerTeam{
	
	public enum POSITION {
		GOALKEEPER, DEFENSE, MIDFIELD, FORWARD
	}
	private POSITION position;
	private String name;
	private Integer age;
	
	private DiversePlayerTeam() {}
	
	public POSITION getPosition() {
		return position;
	}
	public void setPosition(POSITION position) {
		this.position = position;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public static class Builder  {
		private POSITION position;
		private String name;
		private Integer age;
		
		public Builder(POSITION position, String name) {
			this.position = position;
			this.name = name;
		}
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		public Builder setAge(Integer age) {
			this.age = age;
			return this;
		}
		public DiversePlayerTeam build() {
			DiversePlayerTeam player = new DiversePlayerTeam();
			player.setAge(this.age);
			player.setName(this.name);
			player.setPosition(this.position);
			return player;
		}
	}
	
	@Override
	public String toString() {
		return "My name is "+this.name+" and I'm "+this.age+" years old and my position is "+this.position.toString();
	}
	
}

interface PlayerIterator {
	boolean hasNext();
	Object next();
}

interface ManagePlayer {
	PlayerIterator createIterator();
}

class DefenseIterator implements PlayerIterator {
	
	private final DiversePlayerTeam.POSITION reference = DiversePlayerTeam.POSITION.DEFENSE;
	private List<DiversePlayerTeam> players;
	private int position = 0;
	
	public DefenseIterator(List<DiversePlayerTeam> players) {
		this.players = players;
	}
	
	@Override
	public boolean hasNext() {
		while(position<players.size()) {
			if(players.get(position).getPosition().equals(reference)) {
				return true;
			}else {
				position++;
			}
		}
		return false;
	}

	@Override
	public Object next() {
		if(hasNext()) {
			Object o = players.get(position);
			position++;
			return o;
		}
		return null;
	}
}

class ManageTeamDefenses implements ManagePlayer{

	private List<DiversePlayerTeam> players;
	
	public ManageTeamDefenses(List<DiversePlayerTeam> players) {
		this.players = players;
	}
	@Override
	public PlayerIterator createIterator() {
		return new DefenseIterator(players);
	}
	
}

