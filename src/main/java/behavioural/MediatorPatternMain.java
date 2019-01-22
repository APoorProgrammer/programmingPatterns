package behavioural;

public class MediatorPatternMain {

	/**
	 * 
	 * @author David de Miguel Otero
	 * 
	 * Mediator pattern allow us to abstract the form what a set of disparate objects communicate each other.
	 * 
	 * Instead of define a specific form to communicate an object to other, Meadiator pattern exposes a 
	 * interface's method's set and this objects uses that to communicate to the rest of them
	 * 
	 * In our example, we are going to simulate a transfer of a player.
	 * Instead of define many ways to transfer player between different teams all transfer are going to hub in FIFA 
	 * interface.
	 * We simulate the transfer of Ronaldo to Real Madrid from Internacionale
	 * 
	 */
	
	public static void main(String[] args) {

	}
	
}

interface FIFA {
	
	boolean sendTransfer(Transfer transferPlayer);
	Transfer receiveTransfer();
	
}

class Transfer {
	
	private String namePlayer;
	private Double value;
	
	public String getNamePlayer() {
		return namePlayer;
	}
	public void setNamePlayer(String namePlayer) {
		this.namePlayer = namePlayer;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
}