package behavioural;

/**
 * 
 * @author David de Miguel Otero
 * 
 * This pattern is used when we try to decouple two objects one acts as invoker an other as receiver
 * of a request.
 * There will have a Command class which represents the request between both.
 * 
 * In our example we are going to suppose that exists a Coach class which have to order a diverse group of other classes.
 * Instead of create a concrete method to all of this classes, we are going to create a invoke method and use it
 * to communicate with the other classes that will be DefensePlayer, ForwardPlayer, KitMan, Doctor and Physiotherapist. 
 * 
 * All of this orders are going to do through a Command interface which will be implements by concrete classes will do
 * receiver's classes jobs.
 *
 */

public class CommandPatternExample {

	public static void main(String[] args) {
		System.out.println("EXAMPLE WHEN COACH ORDER TO DEFENSE PLAYER...");
		DefensePlayer defensePlayer = new DefensePlayer();
		DefenseTackle tackleCommand = new DefenseTackle(defensePlayer);
		Coach coach = new Coach(tackleCommand);
		coach.order();
		System.out.println();
		System.out.println("EXAMPLE WHEN COACH ORDER TO DOCTOR...");
		Doctor doctor = new Doctor(null);
		DoctorAttend doctorAttend = new DoctorAttend(doctor);
		coach.setOrder(doctorAttend);
		coach.order();
		System.out.println();
		System.out.println("NOW THERE IS A DEFENSE PLAYER TO BE ATTENDED...");
		doctor.setAttendedPlayer(defensePlayer);
		coach.order();
	}
	
}

interface Command {
	public void execute();
}

class Coach {
	Command order;
	
	public Coach(Command order){
		this.order=order;
	}
	
	public void setOrder(Command order) {
		this.order=order;
	}
	
	public void order() {
		order.execute();
	}
}

class DefensePlayer{
	public void tackle() {
		System.out.println("DEFENSE SAYS: I have to tackle my opponent");
	}
}

class DefenseTackle implements Command{

	private DefensePlayer defensePlayer;
	
	public DefenseTackle(DefensePlayer defensePlayer) {
		this.defensePlayer=defensePlayer;
	}
	
	@Override
	public void execute() {
		defensePlayer.tackle();
	}
	
}

class Doctor {
	
	DefensePlayer player; 
	
	public Doctor(DefensePlayer player) {
		this.player=player;
	}
	
	public void setAttendedPlayer(DefensePlayer player) {
		this.player=player;
	}
	
	public boolean tendPlayer() {
		if (null == player) {
			System.out.println("DOCTOR SAYS: I have no one to attend");
			return false;
		}
		System.out.println("DOCTOR SAYS: Done");
		return true;
	}
	
}

class DoctorAttend implements Command{
	
	private Doctor doctor;
	
	public DoctorAttend(Doctor doctor){
		this.doctor = doctor;
	}
	
	private void setDoctor(Doctor doctor){
		this.doctor = doctor;
	}

	@Override
	public void execute() {
		doctor.tendPlayer();
	}
	
}

