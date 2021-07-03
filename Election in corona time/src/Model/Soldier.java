package Model;

import java.util.Scanner;

public class Soldier extends Citizan {
	protected boolean isArmed = false;
	private boolean isSoldier = true;

	public Soldier(String name, int id, int birthYear, Boolean isArmed, boolean isSoldier, boolean isSick)
			throws CitizenIDOutOfRnage, CitizenAgeIsLessThen18, CitizenIsNotASoldier {
		super(name, id, birthYear, isSoldier, isSick);
		this.isArmed = isArmed;
		isSoldier();
	}

	public boolean isSoldier() throws CitizenIsNotASoldier { // checks if soldier is between 18 - 21
		if (age >= 18 && age <= 21)
			return true;
		else
			throw new CitizenIsNotASoldier("This soldier is not aged 18-21, so he is not really a soldier");
	}

	public boolean isAllowedToVote() {
		return super.isAllowedToVote();
	}

	public boolean isIDBelongsToOtherCitizen(int ID) {
		return super.isIDBelongsToOtherCitizen(ID);
	}

	public boolean doYouWantToVote() {
		return super.doYouWantToVote();
	}

	public String toString() { // need to change the part of kalpi
		if (isBelongToKalpi) {
			if (isArmed)
				return "Soldier's name: " + name + ", ID: " + ID + ", birth Year: " + birthYear + ", age: " + age
						+ " is armed and is voting in " + ballotBox.toString();
			else
				return "Soldier's name: " + name + ", ID: " + ID + ", birth Year: " + birthYear + ", age: " + age
						+ " is not armed and is voting in " + ballotBox.toString();
		}
		else if (isArmed)
			return "Soldier's name: " + name + ", ID: " + ID + ", birth Year: " + birthYear + ", age: " + age
					+ " is arme";
		else
			return "Soldier's name: " + name + ", ID: " + ID + ", birth Year: " + birthYear + ", age: " + age
					+ " is not armed";
	}

	public String getName() {
		return super.getName();
	}

	public boolean addKalpiToCitizen(BallotBox ballotBox2) {
		return super.addKalpiToCitizen(ballotBox2);
	}

	public boolean getIsSoldier() {
		return isSoldier;
	}

}
