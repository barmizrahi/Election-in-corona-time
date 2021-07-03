package Model;

import java.util.Scanner;

public class SickSoldier extends Soldier implements Sickable {
	private int howManyDaysIsolated;
	private boolean isSickSoldier = true;

	public SickSoldier(String name, int id, int birthYear, Boolean isArmed, int howManyDaysIsolated, boolean isSoldier,
			boolean isSick) throws CitizenIDOutOfRnage, CitizenAgeIsLessThen18, CitizenIsNotASoldier {
		super(name, id, birthYear, isArmed, isSoldier, isSick);
		setIsIsolated(isSick);
		setHowManyDaysInIsolation(howManyDaysIsolated);
	}

	public int getHowManyDaysIsolated() {
		return howManyDaysIsolated;
	}

	public boolean isSickSoldier() {
		return isSickSoldier;
	}

	public String toString() {
		if (isBelongToKalpi) {
			if (isArmed)
				return "Sick soldier's name: " + name + ", ID: " + ID + ", birth Year: " + birthYear + ", age: " + age
						+ " is isolated for " + howManyDaysIsolated + " is armed and is voting in "
						+ ballotBox.toString();
			else
				return "Sick soldier's name: " + name + ", ID: " + ID + ", birth Year: " + birthYear + ", age: " + age
						+ " is isolated for " + howManyDaysIsolated + " is not armed and is voting in "
						+ ballotBox.toString();
		}
		else if (isArmed)
			return "Sick soldier's name: " + name + ", ID: " + ID + ", birth Year: " + birthYear + ", age: " + age
					+ " is isolated for " + howManyDaysIsolated + " is armed";
		else
			return "Sick soldier's name: " + name + ", ID: " + ID + ", birth Year: " + birthYear + ", age: " + age
					+ " is isolated for " + howManyDaysIsolated + " is not armed";
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

	public boolean addKalpiToCitizen(BallotBox ballotBox2) {
		return super.addKalpiToCitizen(ballotBox2);
	}

	public int getHowManyDaysInIsolation() {
		return howManyDaysIsolated;
	}

	

	@Override
	public boolean setHowManyDaysInIsolation(int days) {
		if (days >= 0) {
			this.howManyDaysIsolated = days;
			return true;
		} else
			days = -1;
		return false;
	}

	@Override
	public void setIsIsolated(boolean isSick) {
		isSickSoldier = isSick;
		
	}

}
