package behavioural;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

	}
	
}

interface FIFA {
	
	boolean sendTransfer(Transfer transferPlayer);
	boolean receiveTransfer(Transfer transferPlayer);
	
}

class FIFAMediator implements FIFA {

	List<Team> teams;
	Set<Transfer> transfers;
	
	public FIFAMediator() {
		this.teams = new ArrayList<>();
		this.transfers = new HashSet<>();
	}
	
	public FIFAMediator(List<Team> teams) {
		this.teams = teams;
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
	private String seller;
	private String buyer;
	
	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public Transfer(String namePlayer, Double value) {
		this.namePlayer = namePlayer;
		this.value = value;
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

//Could use abstract class too
interface Team {
	Boolean sendTransfer(Transfer transferPlayer, String buyer, String seller);
	void checkTransfer(Transfer transferPlayer);
	Boolean sign(Transfer transfer, Boolean signed);
}

class RealMadridBuyerTeam implements Team {
	
	private FIFA mediator;
	private String name;
	
	public RealMadridBuyerTeam(FIFA mediator, String name) {
		this.mediator = mediator;
		this.name = name;
	}
	
	public Boolean sendTransfer(Transfer transferPlayer, String buyer, String seller) {
		return false;
	}
	
	public void checkTransfer(Transfer transferPlayer) {
		System.out.println("We are "+this.name+" and we want to know if "+transferPlayer.getNamePlayer()+ "'s documentation is OK ");
		if(mediator.receiveTransfer(transferPlayer)) {
			System.out.println("We hire "+transferPlayer.getNamePlayer()+"!!!!");
		}else {
			System.out.println("Oh jeez, dawn Manchester United!!!!, the fax fails again....");
		}
	}

	@Override
	public Boolean sign(Transfer transfer, Boolean signed) {
		transfer.setSignedByBuyer(signed);
		return signed;
	}

}

class ManchesterUnitedSellerTeam implements Team {
	
	private FIFA mediator;
	private String name;
	
	public ManchesterUnitedSellerTeam(FIFA mediator, String name) {
		this.mediator = mediator;
		this.name = name;
	}
	
	public Boolean sendTransfer(Transfer transferPlayer, String buyer, String seller) {
		System.out.println("We are "+buyer+" and we want to sell "+transferPlayer.getNamePlayer()+ " to "+seller);
		transferPlayer.setSeller(seller);
		transferPlayer.setBuyer(buyer);
		return mediator.sendTransfer(transferPlayer);
	}
	
	public void checkTransfer(Transfer transferPlayer) {
	}
	
	@Override
	public Boolean sign(Transfer transfer, Boolean signed) {
		transfer.setSignedBySeller(signed);
		return signed;
	}
}

