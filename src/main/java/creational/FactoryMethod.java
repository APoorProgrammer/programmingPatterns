package creational;

import java.util.EnumMap;
import java.util.Optional;
import java.util.function.Supplier;

import creational.YouthAcademyPlayer.POSITION;

public class FactoryMethod {

	/**
	 * 
	 * @author David de Miguel Otero
	 * 
	 * Factory method pattern is used when we want to abstract creations' object from client and defer 
	 * this creation in subclassses which implements it.
	 * 
	 * In our example we are going to create a youth academy's interface with EnumType which we can choose 
	 * what type of player we need for our foremost team(client).
	 * For our foremost team the formation(creation) is not interesting, it only need a player(object) 
	 * 
	 * of this type(EnumType)
	 * 
	 */
	
	public static void main(String[] args) {
		
		YouthAcademyPlayer goalkeeper = YouthAcademyFactory.getYouthAcademyPlayer(POSITION.GOALKEEPER);
		YouthAcademyFactory.getYouthAcademyPlayerDesciption(goalkeeper);
		
		YouthAcademyPlayer forward = YouthAcademyFactory.getYouthAcademyPlayer(POSITION.FORWARD);
		YouthAcademyFactory.getYouthAcademyPlayerDesciption(forward);
		
		YouthAcademyPlayer test = YouthAcademyFactory.getYouthAcademyPlayer(null);
		YouthAcademyFactory.getYouthAcademyPlayerDesciption(test);
		
	}

}

interface YouthAcademyPlayer {
	
	public enum POSITION { GOALKEEPER, DEFENSE, MIDFIELD, FORWARD}
	
	POSITION getPostion();
	String getBestCharacteristic();
	int getAge();
	
}


class GoalKeeper implements YouthAcademyPlayer {

	private int age;
	
	@Override
	public POSITION getPostion() {
		return POSITION.GOALKEEPER;
	}

	@Override
	public String getBestCharacteristic() {
		return "make great catches";
	}

	@Override
	public int getAge() {
		return this.age;		
	}
	
}

class Defense implements YouthAcademyPlayer {

	private int age;

	@Override
	public POSITION getPostion() {
		return POSITION.DEFENSE;
	}

	@Override
	public String getBestCharacteristic() {
		return "recover too many balls";
	}

	@Override
	public int getAge() {
		return this.age;		
	}
	
}

class MidField implements YouthAcademyPlayer {

	private int age;

	@Override
	public POSITION getPostion() {
		return POSITION.MIDFIELD;
	}

	@Override
	public String getBestCharacteristic() {
		return "has an exquisite ball handling";
	}

	@Override
	public int getAge() {
		return this.age;		
	}
	
}

class Forward implements YouthAcademyPlayer {

	private int age;

	@Override
	public POSITION getPostion() {
		return POSITION.FORWARD;
	}

	@Override
	public String getBestCharacteristic() {
		return "scores too many goals";
	}

	@Override
	public int getAge() {
		return this.age;		
	}
	
}

class YouthAcademyFactory {
	
	static EnumMap<YouthAcademyPlayer.POSITION, Supplier<YouthAcademyPlayer>> positions = initPositionMap();
	
	private static EnumMap<YouthAcademyPlayer.POSITION, Supplier<YouthAcademyPlayer>> initPositionMap() {
		positions = new EnumMap<>(YouthAcademyPlayer.POSITION.class);
		
		positions.put(YouthAcademyPlayer.POSITION.GOALKEEPER, GoalKeeper::new);
		positions.put(YouthAcademyPlayer.POSITION.DEFENSE, Defense::new);
		positions.put(YouthAcademyPlayer.POSITION.MIDFIELD, MidField::new);
		positions.put(YouthAcademyPlayer.POSITION.FORWARD, Forward::new);
		
		return positions;
	}
	
	public static YouthAcademyPlayer getYouthAcademyPlayer(YouthAcademyPlayer.POSITION position) {
		if(null != position) {
			return positions.get(position).get();
		}
		return null;
	}
	
	public static void getYouthAcademyPlayerDesciption(YouthAcademyPlayer player) {
		if(null == player) {
			System.out.println("We haven't this type of player...");
		}else {
			System.out.println("My position is "+player.getPostion()+" I am "+player.getAge()+" years old and I "+player.getBestCharacteristic());
		}
	}
}
