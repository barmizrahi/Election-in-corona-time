package Model;

import java.util.ArrayList;
import java.util.List;

public class Party {
	private String name;
	private enum agendas {left, center, right};
	private agendas eAgendas;
	private MyDate creationDate;
	private List<PartyMember> members;
	//private int candidateCounter = 0;
	private int numOfVotesPerKalpi = 0;

	public Party(String name, String ideology, int creationDateMonth, int creationDateYear) {
		this.name = name;
		setIdeology(ideology);
		this.creationDate = new MyDate(creationDateMonth, creationDateYear);
		members = new ArrayList<>();
	}

	public boolean setIdeology(String ideology) {
		if (isAgendaLegal(ideology)) {
			eAgendas = agendas.valueOf(ideology);
			return true;
		}
		return false;
	}

	public boolean isAgendaLegal(String ideology) {
		agendas[] ideologies = agendas.values();
		for (int i = 0; i < ideologies.length; i++) {
			if (ideologies[i].name().equals(ideology)) 
				return true;
		}
		return false;
	}

	public String getName() {
		return name;
	}

	public boolean addPartyMember(PartyMember candidate) {
		if (!isPartyMemberAlreadyAMemberOfOtherParty(candidate)) {
			members.add(candidate);
			return true;
		}
		return false;
	}

	public boolean isPartyMemberAlreadyAMemberOfOtherParty(PartyMember other1) {
		return contains(other1.getID());
	}

	public boolean contains(int ID) {
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getID() == ID)
				return true;
		}
		return false;
	}

	public void voteCounter() {
		numOfVotesPerKalpi++;
	}

	public int getVoteCounter() {
		return numOfVotesPerKalpi;
	}

	public boolean isRankValid(int rank) {
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getRank() == rank)
				return false;
		}
		return true;
	}

	public String toString() {
		return "Party name-" + name + ", agendas=" + eAgendas + ", creation date:" + creationDate.toString();
	}

	public void showPartyMembers() {
		System.out.println(members);
	}

	public boolean isThereAlreadyAPartyWithTheSameName(Party other) {
		if (other.name.equals(this.name)) 
			return true;
		return false;
	}

	public void reset() {
		numOfVotesPerKalpi = 0;
	}
}
