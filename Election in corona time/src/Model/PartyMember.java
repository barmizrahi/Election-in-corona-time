package Model;

import java.util.Scanner;

public class PartyMember extends Sick {
	private Party party;
	private int rank;
	private boolean isAlreadyInParty = false;

	public PartyMember(String name, int id, int birthYear, boolean isIsolated,int days, Party party, int rank) 
			throws CitizenIDOutOfRnage, CitizenAgeIsLessThen18 {
		super(name,id,birthYear,days,false,isIsolated);
		constructorHelper(isIsolated, party, rank);
	}
	
	
	private void constructorHelper(boolean isIsolated, Party party, int rank) {
		setPartyMemberIsIsolated(isIsolated);
		if (!joinAParty(party))
			System.out.println("can't add this party member to this party becouse he already in another party");
		setRank(rank);
	}
	
	private void setPartyMemberIsIsolated(boolean isIsolated2) {
		super.setIsIsolated(isIsolated2);
		
	}
	public boolean getIsIsolated() {
		return super.getIsIsolated();
	}

	private boolean setRank(int rank2) {
		if (party.isRankValid(rank2)) {
			rank = rank2;
			return true;
		}
		rank = -1;
		return false;
	}

	public int getRank() {
		return rank;
	}

	public int getPartyMemberID() {
		return super.getID();
	}

	public void becomeAPartyMember() {
		party.addPartyMember(this);
	}

	public boolean joinAParty(Party party) {
		if (party == null)
			return false;
		if (party.addPartyMember(this) && isAlreadyInParty == false) {
			isAlreadyInParty = true;
			this.party = party;
			return true;
		}
		return false;
	}

	public boolean addKalpiToCitizen(BallotBox ballotBox2) {
		return super.addKalpiToCitizen(ballotBox2);
	}

	public String toString() {
		if(super.getIsIsolated())
			return super.toString()+" is in party: "+party.getName()+" in rank "
					+getRank();
		else
			return "The party member's name is: " + name + ", ID: " + ID + ", birth Year: " + birthYear
					+ ", age: " + age + " is voting in: "+ballotBox.toString()
					+ " is in party: "+party.getName()+" in rank " + +getRank();
	}
}
