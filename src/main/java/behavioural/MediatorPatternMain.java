package behavioural;

import static constants.Constants.BUYER;
import static constants.Constants.SELLER;
import static constants.Constants.SIGNED;

import java.util.HashSet;
import java.util.Set;

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
	 * We simulate the transfer of De Gea to Real Madrid from Manchester United
	 * 
	 * Interface FIFA works as our Mediator. Its connect objects which implements Team interface.
	 * 
	 */
	
	public static void main(String[] args) {
		
		FIFA mediator = new FIFAMediator();
		
		Transfer deGeaTransfer = new Transfer("De Gea", 12.45);
		
		ManchesterUnitedSellerTeam manchester = new ManchesterUnitedSellerTeam(mediator);
		RealMadridBuyerTeam realMadrid= new RealMadridBuyerTeam(mediator, BUYER);
		//Before signed the transfer
		manchester.sendTransfer(deGeaTransfer, BUYER, SELLER);
		realMadrid.checkTransfer(deGeaTransfer);
		
		manchester.sign(deGeaTransfer);
		realMadrid.sign(deGeaTransfer);
		
		//After they agree and signed the transfer
		manchester.sendTransfer(deGeaTransfer, BUYER, SELLER);
		realMadrid.checkTransfer(deGeaTransfer);

	}
	
}

interface FIFA {
	
	boolean sendTransfer(Transfer transferPlayer);
	boolean receiveTransfer(Transfer transferPlayer);
	
}

class FIFAMediator implements FIFA {

	private Set<Transfer> transfers;
	
	public FIFAMediator() {
		this.transfers = new HashSet<>();
	}
	
	@Override
	public boolean sendTransfer(Transfer transferPlayer) {
		if(transferPlayer.getSignedByBuyer() && transferPlayer.getSignedBySeller()) {
			return transfers.add(transferPlayer);
		}
		return false;
	}

	@Override
	public boolean receiveTransfer(Transfer transferPlayer) {
		return transfers.contains(transferPlayer);
	}
	
}

class Transfer {
	
	private String namePlayer;
	private Double value;
	private Boolean signedBySeller;
	private Boolean signedByBuyer;

	public Transfer(String namePlayer, Double value) {
		this.namePlayer = namePlayer;
		this.value = value;
		this.signedBySeller = false;
		this.signedByBuyer = false;
	}
	
	public Boolean getSignedBySeller() {
		return signedBySeller;
	}
	public void setSignedBySeller(Boolean signedBySeller) {
		this.signedBySeller = signedBySeller;
	}
	public Boolean getSignedByBuyer() {
		return signedByBuyer;
	}
	public void setSignedByBuyer(Boolean signedByBuyer) {
		this.signedByBuyer = signedByBuyer;
	}
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

class RealMadridBuyerTeam {
	
	private FIFA mediator;
	private String name;
	
	public RealMadridBuyerTeam(FIFA mediator, String name) {
		this.mediator = mediator;
		this.name = name;
	}
	
	public void checkTransfer(Transfer transferPlayer) {
		System.out.println("We are "+this.name+" and we want to know if "+transferPlayer.getNamePlayer()+ "'s documentation is OK ");
		if(mediator.receiveTransfer(transferPlayer)) {
			System.out.println("We hire "+transferPlayer.getNamePlayer()+"!!!!");
		}else {
			System.out.println("Oh jeez, dawn Manchester United!!!!, the fax fails again....");
		}
		System.out.println();
	}

	public Boolean sign(Transfer transfer) {
		transfer.setSignedByBuyer(SIGNED);
		return SIGNED;
	}

}

class ManchesterUnitedSellerTeam{
	
	private FIFA mediator;
	
	public ManchesterUnitedSellerTeam(FIFA mediator) {
		this.mediator = mediator;
	}
	
	public Boolean sendTransfer(Transfer transferPlayer, String buyer, String seller) {
		System.out.println("We are "+seller+" and we want to sell "+transferPlayer.getNamePlayer()+ " to "+buyer);
		System.out.println();
		return mediator.sendTransfer(transferPlayer);
	}
	
	public Boolean sign(Transfer transfer) {
		transfer.setSignedBySeller(SIGNED);
		return SIGNED;
	}
}

