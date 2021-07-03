package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class BallotBox <T extends Citizan> { // need to be generic <T>
	protected static int kalpiID = 1;
	protected int ID;
	protected String adress;
	protected List<T> voters;
	protected int currentCitizans;
	public List<Party> parties;
	protected int results;
	protected int numOfVoters = 0;
	protected double votePercentage;
	protected int partyNum;
	protected List<Integer> votes;
	protected boolean isStart = false;

	public BallotBox(String adress, List<Party> temp1, List<T> voters) {
		this.adress = adress;
		parties = temp1;
		this.voters = voters;
		ID = kalpiID++;
		votes = new ArrayList<Integer>();
	}

	public BallotBox(String adress, List<Party> temp1) {
		this(adress, temp1, new ArrayList<>());
	}
	public void addVoter(T person) {
		voters.add(person);
		currentCitizans++;
	}


	public void VotersPrecent() {
		double precent;
		precent = (numOfVoters / voters.size()) * 100;
		votePercentage = precent;
	}

	public void countNumOfVoters() {
		numOfVoters++;
	}

	public double getVotePercentage() {
		return votePercentage;
	}

	public int getNumVotesPerKalpi(int i) {
		return votes.get(i);
	}

	public void showParties() {
		System.out.println("These are your voting options");
		for (int i = 0; i < parties.size(); i++) {
			System.out.println(i + 1 + ") " + parties.get(i).getName() + "\n");
		}
	}

	public void startList() {
		for (int i = 0; i < parties.size(); i++) {
			votes.add(0);
		}
		isStart = true;
	}

	public void addVote() {
		if (!isStart)
			startList();
		for (int i = 0; i < votes.size(); i++) {
			if (i == partyNum) {
				int j = votes.get(i).intValue() + 1;
				votes.set(i, j);
			}
		}
	}

	public boolean whoDoYouWantToVoteFor(Scanner s, int j) {
		System.out.println("-----------------------------------------------------------");
		if (voters.get(j).doYouWantToVote()) { // if enter he wants to vote 
			countNumOfVoters();
			String vote;
			showParties();
			System.out.println(voters.get(j).getName() + " Which party do you want to vote for?");
			vote = s.next();
			if (isVoteLegal(vote))
				return true;
		}
		return false;
	}

	public boolean whoDoYouWantToVoteForFx(int j) {
		startList();
		System.out.println("-----------------------------------------------------------");
		if (voters.get(j).doYouWantToVote()) { // if enter he wants to vote 
			countNumOfVoters();
			partyNum = voters.get(j).voting(parties.size());// number of party
			int k = votes.get(partyNum) + 1; // add +1 to the party we chose
			votes.set(partyNum, k); // update the votes of the party to become the value in K
			return true;
		}
		return false;
	}
	

	public int getCurrentCitizans() {
		return currentCitizans;
	}

	public boolean isVoteLegal(String vote) {
		for (int i = 0; i < parties.size(); i++) {
			if (isSameString(vote, parties.get(i).getName())) { 
				partyNum = i;
				return true;
			}
		}
		return false;
	}

	private boolean isSameString(String str, String str1) {
		String str2 = str.replaceAll(" ", "");
		String str3 = str1.replaceAll(" ", "");
		if (str2.equals(str3))
			return true;
		return false;
	}

	public void result() {
		Collections.sort(parties, compareByVotes);
	}

	Comparator<Party> compareByVotes = new Comparator<Party>() {
		public int compare(Party o1, Party o2) {
			return Integer.compare(o1.getVoteCounter(), o2.getVoteCounter());
		}
		// sorts by descending order
	};

	public void showResultsPerKalpi(int i) {
		if (!votes.isEmpty())
			System.out.println("The number of votes in this kalpi ID " + getID() + " for " + parties.get(i).getName()
					+ " is: " + votes.get(i));
		else
			System.out.println(
					"The number of votes in this kalpi ID " + getID() + " for " + parties.get(i).getName() + " is: 0");
	}

	public int getID() {
		return ID;
	}

	public int howManyVotesPerParty(String partyName) {
		int resultOfPartyPerKalpi = 0;
		for (int i = 0; i < parties.size(); i++) {
			if (parties.get(i).getName().equals(partyName))
				resultOfPartyPerKalpi = parties.get(i).getVoteCounter();
		}
		return resultOfPartyPerKalpi;
	}

	public int getPartyNum() {
		return partyNum;
	}

	public String toString() {
		return "Ballotbox number: " + ID + " is located at: " + adress;
	}
}
