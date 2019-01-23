package structural;

import static constants.Constants.HIGH_SALARY_CLASS;
import static constants.Constants.MID_SALARY_CLASS;
import static constants.Constants.TOP_SALARY_CLASS;

import java.util.ArrayList;
import java.util.List;

public class CompositePatternMain {

	/**
	 * 
	 * @author David de Miguel Otero
	 * 
	 * Composite Pattern is used when we want create a tree structure where certain components are individuals 
	 * and others are a composition of them and we want to treat all of them as the same form.
	 * 
	 * Composite pattern will be formed by a Interface knows as Component which will be implemented by Leaf, the individual
	 * components and by Composite elements which could have other Composite elements and/or other Leaf components.
	 * 
	 * In our example, imagine we want to print the name and salary of a set of Real Madrid players.
	 * We are going to have some levels which use Component Pattern
	 * Component Interface will be represent by RealMadridPlayer and will have certain methods that allows us
	 * to manage the name and the salary and print them.
	 * On the other hand we will have classes represents the individual players(Leafs) and the Composite classes which
	 * will be known as TopSalaryClass and HighSalaryClass. It will have a hierarchy between this salary classes.  
	 * 
	 */
	
	public static void main(String[] args) {
		//Create Leaf classes
		SalaryClass cR7 = new PassBallPlayer("CR7", 12.33);
		
		SalaryClass sergioRamos = new PassBallPlayer("Sergio Ramos", 6.66);
		SalaryClass bale = new PassBallPlayer("Bale", 8.66);
		SalaryClass kroos = new PassBallPlayer("Kroos", 7.66);
		
		SalaryClass asensio = new PassBallPlayer("Asensio", 3.51);
		SalaryClass lucasVazquez = new PassBallPlayer("Lucas Vázquez", 3.99);
		SalaryClass isco = new PassBallPlayer("Isco", 5.99);
		
		//Composite classes
		SalaryClass topSalaryClass = new SalaryChainLink(TOP_SALARY_CLASS, cR7.getSalary());
		SalaryClass highSalaryClass = new SalaryChainLink(HIGH_SALARY_CLASS, bale.getSalary());
		SalaryClass midSalaryClass = new SalaryChainLink(MID_SALARY_CLASS, isco.getSalary());
		
		//At this point, we have the great problem with this pattern.
		//If we want to mantain the I in SOLID and we don't want Leaf implements methods that shouldn't, we must 
		//take this method to the Composite classes instead of in the component interface. I refer to methods 
		// to manage children's Composite classes.
		
		//Composite Mid class salary
		((SalaryChainLink)midSalaryClass).addChild(isco);
		((SalaryChainLink)midSalaryClass).addChild(lucasVazquez);
		((SalaryChainLink)midSalaryClass).addChild(asensio);
		//Composite High class salary
		((SalaryChainLink)highSalaryClass).addChild(sergioRamos);
		((SalaryChainLink)highSalaryClass).addChild(bale);
		((SalaryChainLink)highSalaryClass).addChild(kroos);
		((SalaryChainLink)highSalaryClass).addChild(midSalaryClass);
		//Composite Top class salary
		((SalaryChainLink)topSalaryClass).addChild(cR7);
		((SalaryChainLink)topSalaryClass).addChild(highSalaryClass);

		topSalaryClass.paint();
		
		//Result
		//		Salary class TOP SALARY CLASS has a limit: 12.33M €
		//		CR7: 12.33M€
		//
		//		Salary class HIGH SALARY CLASS has a limit: 8.66M €
		//		SERGIO RAMOS: 6.66M€
		//		BALE: 8.66M€
		//		KROOS: 7.66M€
		//
		//		Salary class MID SALARY CLASS has a limit: 5.99M €
		//		ISCO: 5.99M€
		//		LUCAS VÁZQUEZ: 3.99M€
		//		ASENSIO: 3.51M€
	}
}

interface SalaryClass {
	String getName();
	Double getSalary();
	void paint();
}

//Leaf class
class Player implements SalaryClass{

	private String name;
	private Double salary;
	
	public Player(String name, double salary) {
		this.name = name;
		this.salary = salary;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Double getSalary() {
		return this.salary;
	}

	@Override
	public void paint() {
		System.out.println(getName().toUpperCase()+": "+getSalary()+"M€");
	}
	
}

//Composite classes

class SalaryChainLink implements SalaryClass{

	private List<SalaryClass> players;
	private String name;
	private Double salaryLimit;
	
	public SalaryChainLink(String name, double salaryLimit) {
		players = new ArrayList<>();
		this.name = name;
		this.salaryLimit = salaryLimit;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Double getSalary() {
		return this.salaryLimit;
	}

	@Override
	public void paint() {
		System.out.println();
		System.out.println("Salary class "+getName().toUpperCase()+" has a limit: "+getSalary()+"M €");
		for(SalaryClass child : players) {
			child.paint();
		}
	}
	
	public void addChild(SalaryClass child) {
		players.add(child);
	}
	
}