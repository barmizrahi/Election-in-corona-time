package Model;

import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

public class Citizan {
	protected String name;
	protected int ID;
	protected MyDate birthYear;
	protected int age;
	protected BallotBox ballotBox;
	//protected KalpiCorona KalpiCorona;
	//protected IDFKalpi IDFKalpi;
	protected boolean isIsolated = false;
	protected boolean isSoldier = false;
	protected boolean isBelongToKalpi = false;
	
	
	

	public Citizan() {
		super();
	}

	public Citizan(String name, int id, int birthYear, boolean isSoldier, boolean isIsolated)
			throws CitizenIDOutOfRnage, CitizenAgeIsLessThen18 {
		this.name = name;
		this.isIsolated = isIsolated;
		this.isSoldier = isSoldier;
		setID(id);
		setAge(birthYear);
		setBirthYear(birthYear);

	}

	private boolean setBirthYear(int birthYear) throws CitizenAgeIsLessThen18 {
		// setAge(birthYear);
		if (!ageLess18()) {
			throw new CitizenAgeIsLessThen18("The age of " + this.name + " is less then 18''therefore he can't vote");
		}
		this.birthYear = new MyDate(birthYear);
		return true;
	}
	

	private boolean ageLess18() {
		if (age >= 18)
			return true;
		return false;
	}

	private boolean setID(int id) throws CitizenIDOutOfRnage {
		if (!checkIDDigit(id)) { // if number iilagel
			throw new CitizenIDOutOfRnage("The number of digits in the ID of " + this.name + " is not legal"); // add massg
		}
		this.ID = id;
		return true;
	}

	private boolean checkIDDigit(int ID) {
		int counter = 0;
		while (ID != 0) {
			ID = ID / 10;
			counter++;
		}
		if (counter == 9)
			return true;
		return false;
	}

	public boolean setAge(int birthYear) {
		int age, currentYear;
		currentYear = LocalDate.now().getYear();
		age = currentYear - birthYear;
		this.age = age;
		return true;
	}

	public int getAge() {
		return age;
	}
	
	public void setBelongToKalpi(boolean isBelongToKalpi) {
		this.isBelongToKalpi = isBelongToKalpi;
	}

	public boolean isBelongToKalpi() {
		return isBelongToKalpi;
	}

	public boolean isAllowedToVote() {
		if (age >= 18)
			return true;
		return false;
	}

	public int getID() {
		return ID;
	}

	public boolean isIDBelongsToOtherCitizen(int ID) {
		if (this.getID() == ID) {
			return false;
		}
		return true;
	}

	public boolean doYouWantToVote() {
		if (isAllowedToVote()) {
			Random shahar = new Random();
			return shahar.nextBoolean();
		}
		return false;
	}

	public int howManyDaysIsTheSickPersonInIsolation(Scanner s) {
		System.out.println("How many days the citizen is isolated?");
		return s.nextInt();
	}

	public String toString() {
		if(isBelongToKalpi)
			return "Citizan's name: " + name + ", ID: " + ID + ", birth Year: " + birthYear + ", age: " + age + ballotBox.toString();
		else
			return "Citizan's name: " + name + ", ID: " + ID + ", birth Year: " + birthYear + ", age: " + age;
	}

	public String getName() {
		return name;
	}

	public boolean addKalpiToCitizen(BallotBox ballotBox2) {
		ballotBox = ballotBox2;
		isBelongToKalpi = true;
		return true;
	}

	public boolean yesOrNo(Scanner s) {
		char answer;
		do {
			answer = s.next().charAt(0);
			if (answer == 'Y' || answer == 'y')
				return true;
			else if (answer == 'N' || answer == 'n')
				return false;
			System.out.println("You have enterd a wrong answer, try again");
		} while (answer != 'N' || answer != 'n' || answer != 'Y' || answer != 'y');
		return false;
	}

	public int voting(int partyNum) {
		Random shaherBkolMakom = new Random();
		return shaherBkolMakom.nextInt(partyNum);
	}
}
