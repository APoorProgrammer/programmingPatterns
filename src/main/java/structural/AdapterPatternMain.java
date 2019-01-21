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

		
	}

}
