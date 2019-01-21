package structural;

import java.util.ArrayList;
import java.util.List;

public class AdapterPatternMain {

	/**
	 * @author David de Miguel Otero
	 * 
	 * Adapter pattern is used to allow a client which works with a class which implements a certain interface,
	 * use other classes which implements an incompatible interface that couldn't be used if them weren't adapted.
	 * 
	 * In our example, we are going to have a list of RealMadrid's players and two interfaces:
	 * Each of them has its own methods.
	 * One is RealMadridPlayer. It will be implements by a ZinedineZidane's class .
	 * The other will be BarcelonaPlayer. It will be implements LuisFigo's class.
	 * 
	 * What happens if we want Luis Figo left Barcelona's team and sign with Real Madrid's team?
	 * Do we have to create a new Luis Figo class? this is no possible, only one Luis Figo can exists... 
	 * 
	 * Well, is this case where our adapter class Florentino Perez appears...
	 * 
	 */
	
	public static void main(String[] args) {
		
		//We create a list of Real Madrid Players.
		List<RealMadridPlayer> realMadridPlayers = new ArrayList<>();
		
	}

}

interface RealMadridPlayer {
	
	Double getSalary();
	
	Integer getAge();
	
	String getPosition();
	
	String getHalaMadrid();
	
}

class ZinedineZidane implements RealMadridPlayer{

	private Double salary;
	private Integer age;
	private String position;
	private String message;
	
	@Override
	public Double getSalary() {
		return this.salary;
	}

	@Override
	public Integer getAge() {
		return this.age;
	}

	@Override
	public String getPosition() {
		return this.position;
	}

	@Override
	public String getHalaMadrid() {
		return this.message;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
}
	
