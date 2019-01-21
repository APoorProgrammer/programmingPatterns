package structural;

import static constants.Constants.FIGO_DORSAL;
import static constants.Constants.FIGO_SALARY;
import static constants.Constants.HALA_MADRID;
import static constants.Constants.VISCA_BARCA;
import static constants.Constants.ZIDANE_NUMBER;
import static constants.Constants.ZIDANE_SALARY;

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
		
		//We create a ZinedineZidane instance
		RealMadridPlayer zidane = new ZinedineZidane();
		//And configure it
		zidane.setSalary(ZIDANE_SALARY);
		zidane.setNumber(ZIDANE_NUMBER);
		zidane.setHalaMadrid(HALA_MADRID);
		//We add Zidane as Real Madrid Player
		realMadridPlayers.add(zidane);
		//And check it is added
		realMadridPlayers.stream().forEach(p -> System.out.println(p.toString()));

		//We create a LuisFigo instance
		BarcelonaPlayer figo = new LuisFigo();
		//And configure it
		figo.setSalary(FIGO_SALARY);
		figo.setDorsal(FIGO_DORSAL);
		figo.setViscaBarca(VISCA_BARCA);
		//But now how we added Figo to Real Madrid players...
		
		//Then appears our FlorentinoPerezAdapter
		
		
	}

}

interface RealMadridPlayer {
	
	void setSalary(Double salary);
	Double getSalary();

	void setHalaMadrid(String halaMadrid);
	String getHalaMadrid();
	
	void setNumber(Integer number);
	Integer getNumber();
	
}

class ZinedineZidane implements RealMadridPlayer{

	private Double salary;
	private String halaMadrid;
	private Integer number;

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Override
	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
	public Double getSalary() {
		return this.salary;
	}

	@Override
	public String getHalaMadrid() {
		return this.halaMadrid;
	}
	
	@Override	
	public void setHalaMadrid(String halaMadrid) {
		this.halaMadrid = halaMadrid;
	}

	@Override
	public String toString(){
		return "I the number "+getNumber()+" in Real Madrid and I say to supporters: "+getHalaMadrid();
		
	}

	
}
	
interface BarcelonaPlayer {
	
	void setSalary(Double salary);
	Double getSalary();

	void setViscaBarca(String viscaBarca);
	String getViscaBarca();
	
	void setDorsal(Integer dorsal);
	Integer getDorsal();
	
}

class LuisFigo implements BarcelonaPlayer{

	private Double salary;
	private String viscaBarca;
	private Integer dorsal;
	
	@Override
	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Integer getDorsal() {
		return dorsal;
	}

	public void setDorsal(Integer dorsal) {
		this.dorsal = dorsal;
	}

	@Override
	public Double getSalary() {
		return this.salary;
	}
	@Override
	public void setViscaBarca(String viscaBarca) {
		this.viscaBarca = viscaBarca;
	}
	@Override
	public String getViscaBarca() {
		return viscaBarca;
	}
	
	@Override
	public String toString(){
		return "I the number"+getDorsal()+" in Barcelona and I say to supporters: "+getViscaBarca();
		
	}
	
}