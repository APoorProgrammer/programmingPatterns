package structural;

public class DecoratorPatternMain {

	/**
	 * @author David de Miguel Otero
	 * 
	 * Decorator pattern allow us to add an extra behavior an object implements some interface.
	 * This way gets by creating a new class, The Decorator, which implements the same interface and 
	 * has this as a object's dependency.
	 * So all new classes extends the decorator will be able to implement the default behavior implemented and
	 * add an extra behavior.
	 * 
	 * In our example we are going to create a interface called GreatPlayer.
	 * This interface is going to have some methods which will be implemented by a class which name will be Maradona
	 * 
	 * But few years later, appears an other great player that will have the same characteristics than Maradona but 
	 * with better performance...this will be Messi our Decorator.
	 * 
	 * See what's happens...
	 * 
	 */
	
	public static void main(String[] args) {

	}
	
}

