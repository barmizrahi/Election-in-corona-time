package Model;

import java.util.Scanner;

public class Sick extends Citizan implements Sickable{
	private int howManyDaysInIsolation;
	private boolean isIsolated = true;

	
	public Sick(String name, int id, int birthYear, int days, boolean isSoldier, boolean isSick)
			throws CitizenIDOutOfRnage, CitizenAgeIsLessThen18 {
		super(name, id, birthYear, isSoldier, isSick);
		setIsIsolated(isSick);
		setHowManyDaysInIsolation (days);
	}
	

	public boolean setHowManyDaysInIsolation(int days) {
		if(days>=0) {
			this.howManyDaysInIsolation = days;
			return true;
		}
		else 
			days = -1;
		return false;
	}


	public boolean isAllowedToVote() {
		return super.isAllowedToVote();
	}

	public boolean isIDBelongsToOtherCitizen(int ID) {
		return super.isIDBelongsToOtherCitizen(ID);
	}

	public boolean doYouWantToVote(Scanner s) {
		return super.doYouWantToVote();
	}

	public String toString() { // need to change the part of kalpi
		if (isBelongToKalpi)
			return "Sick person's name is: " + name + ", ID: " + ID + ", birth Year: " + birthYear + ", age: " + age
					+ " has been sick for " + howManyDaysInIsolation + " and is voting in: " + ballotBox.toString();
		else
			return "Sick person's name is: " + name + ", ID: " + ID + ", birth Year: " + birthYear + ", age: " + age
					+ " has been sick for " + howManyDaysInIsolation;
	}

	public String getName() {
		return super.getName();
	}

	public boolean addKalpiToCitizen(BallotBox ballotBox2) {
		return super.addKalpiToCitizen(ballotBox2);
	}


	public int getHowManyDaysInIsolation() {
		return howManyDaysInIsolation;
	}

	public boolean getIsIsolated() {
		return isIsolated;
	}


	public void setIsIsolated(boolean isIsolated2) {
		this.isIsolated = isIsolated2;
	}
}
