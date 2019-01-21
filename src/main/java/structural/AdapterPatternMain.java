package structural;

import static constants.Constants.BARCELONA_FIGO_DORSAL;
import static constants.Constants.FIGO_SALARY;
import static constants.Constants.HALA_MADRID;
import static constants.Constants.VISCA_BARCA;
import static constants.Constants.ZIDANE_NUMBER;
import static constants.Constants.ZIDANE_SALARY;
import static constants.Constants.RM_FIGO_DORSAL;
import static constants.Constants.ZIDANE_NAME;
import static constants.Constants.FIGO_NAME;

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
		//We create a list of Barcelona Players.
		List<BarcelonaPlayer> barcelonaPlayers = new ArrayList<>();
		
		//We create a ZinedineZidane instance
		RealMadridPlayer zidane = new ZinedineZidane();
		//And configure it
		zidane.setSalary(ZIDANE_SALARY);
		zidane.setNumber(ZIDANE_NUMBER);
		//We add Zidane as Real Madrid Player
		realMadridPlayers.add(zidane);
		//And check it is added
		realMadridPlayers.stream().forEach(p -> System.out.println(p.toString()));

		//We create a LuisFigo instance
		BarcelonaPlayer figoInBarcelona = new LuisFigo();
		//And configure it
		figoInBarcelona.setSalary(FIGO_SALARY);
		figoInBarcelona.setDorsal(BARCELONA_FIGO_DORSAL);
		//We add Figo as Barcelona Player
		barcelonaPlayers.add(figoInBarcelona);
		//And check it is added
		barcelonaPlayers.stream().forEach(p -> System.out.println(p.toString()));
		
		//But now how we added Figo to Real Madrid players...
		
		//Then appears our FlorentinoPerezAdapter
		RealMadridPlayer luisFigoNewRealMadridPlayer = new FlorentinoPerezAdapter(figoInBarcelona);
		//We add Figo as Real Madrid Player
		luisFigoNewRealMadridPlayer.setNumber(RM_FIGO_DORSAL);
		realMadridPlayers.add(luisFigoNewRealMadridPlayer);
		//And check it is added
		realMadridPlayers.stream().forEach(p -> System.out.println(p.toString()));
		
	}

}

interface RealMadridPlayer {
	
	String getName();
	
	void setSalary(Double salary);
	Double getSalary();

	String getHalaMadrid();
	
	void setNumber(Integer number);
	Integer getNumber();
	
}

class ZinedineZidane implements RealMadridPlayer{

	private Double salary;
	private Integer number;

	@Override
	public String getName() {
		return ZIDANE_NAME;
	}
	
	@Override
	public Integer getNumber() {
		return number;
	}
                           
	@Override
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
		return HALA_MADRID;
	}
	
	@Override
	public String toString(){
		return "I'm "+getName()+" and I'm the number "+getNumber()+" in Real Madrid and I say to supporters: "+getHalaMadrid();
	}

	
}
	
interface BarcelonaPlayer {
	
	String getNom();

	void setSalary(Double salary);
	Double getSalary();

	String getViscaBarca();
	
	void setDorsal(Integer dorsal);
	Integer getDorsal();
	
}

class LuisFigo implements BarcelonaPlayer{

	private Double salary;
	private Integer dorsal;
	
	@Override
	public String getNom() {
		return FIGO_NAME;
	}
	
	@Override
	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
	public Integer getDorsal() {
		return dorsal;
	}

	@Override
	public void setDorsal(Integer dorsal) {
		this.dorsal = dorsal;
	}

	@Override
	public Double getSalary() {
		return this.salary;
	}

	@Override
	public String getViscaBarca() {
		return VISCA_BARCA;
	}
	
	@Override
	public String toString(){
		return "I'm "+getNom()+" and I the dorsal "+getDorsal()+" in Barcelona and I say to supporters: "+getViscaBarca();
	}
	
}

class FlorentinoPerezAdapter implements RealMadridPlayer {
	
	BarcelonaPlayer barcelonaPlayer;
	
	public FlorentinoPerezAdapter(BarcelonaPlayer barcelonaPlayer) {
		this.barcelonaPlayer = barcelonaPlayer;
	}

	@Override
	public String getName() {
		return barcelonaPlayer.getNom();
	}
	
	@Override
	public void setSalary(Double salary) {
		barcelonaPlayer.setSalary(salary);
	}

	@Override
	public Double getSalary() {
		return barcelonaPlayer.getSalary();
	}

	@Override
	public String getHalaMadrid() {
		return HALA_MADRID;
	}

	@Override
	public void setNumber(Integer number) {
		barcelonaPlayer.setDorsal(number);
	}

	@Override
	public Integer getNumber() {
		return barcelonaPlayer.getDorsal();
	}
	
	@Override
	public String toString(){
		return "I'm "+getName()+" and I'm the number "+getNumber()+" in Real Madrid and I say to supporters: "+getHalaMadrid();
	}
	
}