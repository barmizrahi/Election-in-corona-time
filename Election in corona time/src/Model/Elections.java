package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import View.MyMessage;
import View.View;
import javafx.scene.chart.PieChart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Elections implements electioneble {
	private final int ALLKALPIES = 4;
	private MyDate electionsDate;
	private BallotBox<Citizan> ballotCitizens;
	private BallotBox<Sick> ballotSickCitizens;
	private BallotBox<Soldier> ballotSoldiers;
	private BallotBox<SickSoldier> ballotSickSoldier;
	private List<BallotBox<Citizan>> allBallotCitizens = new ArrayList<BallotBox<Citizan>>(); // list of kalpis for
																								// citizen
	private List<BallotBox<Sick>> allBallotSickCitizens = new ArrayList<BallotBox<Sick>>(); // list of kalpis for sick
	private List<BallotBox<Soldier>> allBallotSoldiers = new ArrayList<BallotBox<Soldier>>(); // list of kalpis for
																								// soildier
	private List<BallotBox<SickSoldier>> allBallotSickSoldier = new ArrayList<BallotBox<SickSoldier>>(); // list of sick
																											// soildier
	private List<ArrayList<BallotBox>> totalBallotBox = new ArrayList<ArrayList<BallotBox>>();
	private List<Party> parties1;
	private List<Integer> totalVotes;
	private MyList<Citizan> citizan;
	private MyList<PartyMember> members;
	private int counter, M;
	private boolean isCitizenAdded = false;

	public Elections(int month, int year, List<BallotBox> kalpies, List<Party> parties) {
		setDate(month, year);
		this.parties1 = parties;
		totalVotes = new ArrayList<Integer>();
		citizan = new MyList();
		members = new MyList();
	}

	public Elections() {
		this(1, 2020);
	}

	public Elections(int month, int year) {
		this(month, year, new ArrayList<>(), new ArrayList<>());

	}

	public boolean setDate(int month, int year) {
		if (year > 1948) {
			electionsDate = new MyDate(month, year);
			return true;
		}
		electionsDate = new MyDate(month, 1948);
		return true;

	}

	private void startList() {
		for (int i = 0; i < parties1.size(); i++) {
			totalVotes.add(0);
		}
	}

	public void electionsMakerFx(int year, int month) {
		setDate(month, year);
	}

	public void electionsMaker(Scanner s) throws Exception {
		System.out.println("Lets start the elections!");
		System.out.println("Please provide me the year of the election");
		int year = s.nextInt();
		System.out.println("Please provide me the month of the election");
		int month = s.nextInt();
		setDate(month, year);
		if (!isCitizenAdded) {
			addCitizensToKalpi(s); // each citizen go to his kalpi
			isCitizenAdded = true;
		}

		for (int j = 0; j < allBallotCitizens.size(); j++) // run all the kalpi for normal citizen
			electionStartForKalpi(allBallotCitizens.get(j), s);

		for (int j = 0; j < allBallotSickCitizens.size(); j++) // run all the kalpi for sick citizen
			electionStartForKalpi(allBallotSickCitizens.get(j), s);

		for (int j = 0; j < allBallotSoldiers.size(); j++) // run all the kalpi for soldier citizen
			electionStartForKalpi(allBallotSoldiers.get(j), s);

		for (int j = 0; j < allBallotSickSoldier.size(); j++) // run all the kalpi for sicksoldier citizen
			electionStartForKalpi(allBallotSickSoldier.get(j), s);

	}

	public void electionsMakerFx(View view, MyMessage msg) throws Exception {
		if (!isCitizenAdded) {
			addCitizensToKalpiFx(view, msg); // each citizen go to his kalpi
			isCitizenAdded = true;
		}

		for (int j = 0; j < allBallotCitizens.size(); j++) // run all the kalpi for normal citizen
			electionStartForKalpiFx(allBallotCitizens.get(j));

		for (int j = 0; j < allBallotSickCitizens.size(); j++) // run all the kalpi for sick citizen
			electionStartForKalpiFx(allBallotSickCitizens.get(j));

		for (int j = 0; j < allBallotSoldiers.size(); j++) // run all the kalpi for soldier citizen
			electionStartForKalpiFx(allBallotSoldiers.get(j));

		for (int j = 0; j < allBallotSickSoldier.size(); j++) // run all the kalpi for sicksoldier citizen
			electionStartForKalpiFx(allBallotSickSoldier.get(j));

	}

	public void electionStartForKalpi(BallotBox ballotBox, Scanner s) {
		for (int j = 0; j < ballotBox.getCurrentCitizans(); j++) { // run all the citizen
			if (ballotBox.whoDoYouWantToVoteFor(s, j)) // return true
				ballotBox.addVote();
			else
				ballotBox.startList();
		}
	}

	public void electionStartForKalpiFx(BallotBox ballotBox) {
		for (int j = 0; j < ballotBox.getCurrentCitizans(); j++) { // run all the citizen
			if (ballotBox.whoDoYouWantToVoteForFx(j)) // return true
				ballotBox.addVote();
			else
				ballotBox.startList();
		}
	}

	public void electionsResults() { // go for each party and collect all the votes for that party from all the
										// kalpies
		startList();
		for (int k = 0; k < parties1.size(); k++) {
			counter = 0;
			for (int q = 0; q < allBallotCitizens.size(); q++)
				counter = electionHelp(allBallotCitizens.get(q), k, counter);
			for (int q = 0; q < allBallotSickCitizens.size(); q++)
				counter = electionHelp(allBallotSickCitizens.get(q), k, counter);
			for (int q = 0; q < allBallotSoldiers.size(); q++)
				counter = electionHelp(allBallotSoldiers.get(q), k, counter);
			for (int q = 0; q < allBallotSickSoldier.size(); q++)
				counter = electionHelp(allBallotSickSoldier.get(q), k, counter);
			totalVotes.set(k, counter);
			System.out.println("The results for the elections on the: " + electionsDate.toString() + " for the party: "
					+ parties1.get(k).getName() + " is: " + totalVotes.get(k)); // shows the total results for all the
		}
	}

	public void electionsResultsFx(PieChart pieResults) { // go for each party and collect all the votes for that party from all the
		// kalpies
		startList();
		for (int k = 0; k < parties1.size(); k++) {
			counter = 0;
			for (int q = 0; q < allBallotCitizens.size(); q++)
				counter = electionHelp(allBallotCitizens.get(q), k, counter);
			for (int q = 0; q < allBallotSickCitizens.size(); q++)
				counter = electionHelp(allBallotSickCitizens.get(q), k, counter);
			for (int q = 0; q < allBallotSoldiers.size(); q++)
				counter = electionHelp(allBallotSoldiers.get(q), k, counter);
			for (int q = 0; q < allBallotSickSoldier.size(); q++)
				counter = electionHelp(allBallotSickSoldier.get(q), k, counter);
			totalVotes.set(k, counter);
			PieChart.Data results = new PieChart.Data(
					parties1.get(k).getName(),
					totalVotes.get(k));
			pieResults.getData().add(results);  // shows the total results for all the in a pie chart
		}
	}

	private int electionHelp(BallotBox ballotBox, int k, int counter) {
		if (!ballotBox.votes.isEmpty()) {
			if (ballotBox != null) {
				ballotBox.showResultsPerKalpi(k); // shows the result per kalpi for each party
				counter = counter + ballotBox.getNumVotesPerKalpi(k); // count the votes for the party K.
			}
		} else
			System.out.println(
					"In kalpi number " + (ballotBox.ID) + " no one can/is allowed to vote! See you next time :)");
		return counter;
	}

	public boolean addFullCitizen(Citizan c, Sick s, Soldier sol, SickSoldier Sl, int choose)
			throws CitizenIDOutOfRnage, CitizenAgeIsLessThen18, Exception, CitizenIsNotASoldier {
		if (choose == 1) {
			citizan.add(c);
			return true;
		}
		if (choose == 2) {
			citizan.add(s);
			return true;
		}
		if (choose == 3) {
			citizan.add(sol);
			return true;
		}
		if (choose == 4) {
			citizan.add(Sl);
			return true;
		}
		return false;

	}

	public boolean addCitizen(String name, int ID, int birthYear, int days, Boolean isArmed, int choose)
			throws CitizenIDOutOfRnage, CitizenAgeIsLessThen18, Exception, CitizenIsNotASoldier {
		try {
			if (choose == 1)
				citizan.add(new Citizan(name, ID, birthYear, false, false));
			if (choose == 2)
				citizan.add(new Sick(name, ID, birthYear, days, false, true));
			if (choose == 3)
				citizan.add(new Soldier(name, ID, birthYear, isArmed, true, false));
			if (choose == 4)
				citizan.add(new SickSoldier(name, ID, birthYear, isArmed, days, true, true));
		} catch (CitizenIDOutOfRnage e) {

			System.out.println(e.getMessage());
		} catch (CitizenAgeIsLessThen18 e) {
			System.out.println(e.getMessage());
		} catch (CitizenIsNotASoldier e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return true;
	}

	public boolean addPartyMember(String name, int ID, int birthYear, boolean isIsolated, int days, String partyName,
			int rank) { // throws CitizenIDOutOfRnage, CitizenAgeIsLessThen18, Exception {
		Party party = null;
		for (int i = 0; i < parties1.size(); i++) {
			if (parties1.get(i).getName().equals(partyName))
				party = parties1.get(i);
		}
		for (int r = 0; r < citizan.size(); r++) {
			if (citizan.get(r).getID() == ID)
				return false;
		}

		try {
			members.add(new PartyMember(name, ID, birthYear, isIsolated, days, party, rank));
		} catch (CitizenIDOutOfRnage e) {
			System.out.println(e.getMessage());
		} catch (CitizenAgeIsLessThen18 e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return true;
	}

	public boolean addParty(String name, String ideology, int creationDateMonth, int creationDateYear) {
		if (parties1.isEmpty()) {
			parties1.add(new Party(name, ideology, creationDateMonth, creationDateYear));
			return true;
		}
		for (int i = 0; i < parties1.size(); i++) {
			if (parties1.get(i).getName().equals(name)) {
				System.out.println("There is already a party with the same name, please try again");
				return false;
			}
		}
		parties1.add(new Party(name, ideology, creationDateMonth, creationDateYear));
		return true;
	}

	public boolean addNormalKalpi(String adress) { // need to chance
		ballotCitizens = new BallotBox<>(adress, parties1);
		allBallotCitizens.add(ballotCitizens);
		return true;
	}

	public boolean addCoronaKalpi(String adress) {// need to chance
		ballotSickCitizens = new BallotBox<>(adress, parties1);
		allBallotSickCitizens.add(ballotSickCitizens);
		return true;
	}

	public boolean addIDFKalpi(String adress) {// need to chance
		ballotSoldiers = new BallotBox<>(adress, parties1);
		allBallotSoldiers.add(ballotSoldiers);
		return true;
	}

	public boolean addIDFCoronaKalpi(String adress) {
		ballotSickSoldier = new BallotBox<>(adress, parties1);
		allBallotSickSoldier.add(ballotSickSoldier);
		return true;
	}

	private void addCitizensToKalpi(Scanner s) throws Exception {
		for (int r = 0; r < members.size(); r++) // add all the member
			citizan.add(members.get(r));
		for (int j = 0; j < citizan.size(); j++) {
			if (citizan.get(j).isIsolated) {
				if (isProtected(citizan.get(j), s) == true) {
					if (citizan.get(j).isSoldier) {
						ballotSickSoldier.addVoter((SickSoldier) citizan.get(j));
						citizan.get(j).setBelongToKalpi(true);
						continue;
					} else {
						ballotSickCitizens.addVoter((Sick) citizan.get(j));
						citizan.get(j).setBelongToKalpi(true);
						continue;
					}
				} else {
					System.out.println("your not protected therefore you can't vote");
					continue;
				}
			} else if (citizan.get(j).isSoldier) {
				ballotSoldiers.addVoter((Soldier) citizan.get(j));
				citizan.get(j).setBelongToKalpi(true);
				continue;
			}
			ballotCitizens.addVoter((Citizan) citizan.get(j));
			citizan.get(j).setBelongToKalpi(true);
		}
	}

	public void addCitizensToKalpiFx(View view, MyMessage msg) throws Exception {
		for (int r = 0; r < members.size(); r++) // add all the member
			citizan.add(members.get(r));
		for (int j = 0; j < citizan.size(); j++) {
			if (citizan.get(j).isIsolated) { // if citizen is sick
				Random randomProt = new Random();
				if (randomProt.nextBoolean()) { // if protaceted
					if (citizan.get(j).isSoldier) {
						ballotSickSoldier.addVoter((SickSoldier) citizan.get(j));
						citizan.get(j).setBelongToKalpi(true);
						continue;
					} else {
						ballotSickCitizens.addVoter((Sick) citizan.get(j));
						citizan.get(j).setBelongToKalpi(true);
						continue;
					}
				} else {
					// msg.addMessage("You are not protect, therefore you can't vote - Go suit
					// up!");
					continue;
				}
			} else if (citizan.get(j).isSoldier) {
				ballotSoldiers.addVoter((Soldier) citizan.get(j));
				citizan.get(j).setBelongToKalpi(true);
				continue;
			}
			ballotCitizens.addVoter((Citizan) citizan.get(j));
			citizan.get(j).setBelongToKalpi(true);
		}
	}

	/*
	 * public void addCitizensToKalpiFx(View.View theView) throws Exception { for
	 * (int r = 0; r < members.size(); r++) { // add all the member
	 * citizan.add(members.get(r)); } for (int j = 0; j < citizan.size(); j++) { //
	 * sort citizen to kalpi M = j; if (citizan.get(j).isIsolated) {//
	 * theView.getVbElectionMaker().getChildren().addAll(new
	 * Text(citizan.get(M).getName()) , theView.getHbWearingSuit(),
	 * theView.getYes()); theView.yes(new EventHandler<ActionEvent>() {
	 * 
	 * @Override public void handle(ActionEvent event) { boolean bfx =
	 * yesOrNoFx(theView.getTWearingSuit().getText()); if (bfx) { // if protected if
	 * (citizan.get(M).isSoldier) { // cheack if sick soldier
	 * ballotSickSoldier.addVoter((SickSoldier) citizan.get(M));
	 * citizan.get(M).setBelongToKalpi(true); //continue; } else { // just sick
	 * ballotSickCitizens.addVoter((Sick) citizan.get(M));
	 * citizan.get(M).setBelongToKalpi(true); //continue; //break; }
	 * 
	 * } theView.getVbElectionMaker().getChildren().clear(); } }); } if
	 * (citizan.get(j).isSoldier) { ballotSoldiers.addVoter((Soldier)
	 * citizan.get(j)); citizan.get(j).setBelongToKalpi(true);
	 * 
	 * } else { ballotCitizens.addVoter((Citizan) citizan.get(j));
	 * citizan.get(j).setBelongToKalpi(true); }
	 * 
	 * } }
	 */
	public boolean isProtected(Citizan c, Scanner s) {
		System.out.println(c.getName() + " Are you wearing Protective suit?");
		return yesOrNo(s);
	}

	public boolean isProtectedFx(Citizan c, View theView) { //
		theView.getVbElectionMaker().getChildren().addAll(theView.getHbWearingSuit());
		return yesOrNoFx(theView.getTWearingSuit().getText());
	}

	public String showPartiesInfo() {
		StringBuffer sb = new StringBuffer();
		sb.append("The parties are :\n");
		for (int i = 0; i < parties1.size(); i++)
			sb.append(i + 1 + ")" + parties1.get(i).toString() + "\n");
		return sb.toString();
	}

	public void showPartiesInfoFx(List<Text> info) {
		info.add(new Text("The parties are :\n"));
		for (int i = 0; i < parties1.size(); i++) {
			info.add(new Text(i + 1 + ")" + parties1.get(i).toString() + "\n"));
		}
	}

	public String showCitizenInfo() { // *****
		StringBuffer sb = new StringBuffer();
		sb.append("The citizens are :\n");
		for (int i = 0; i < citizan.size(); i++) {
			sb.append(i + 1 + ")" + citizan.get(i).toString() + "\n");
		}
		return sb.toString();
	}

	public void showCitizenInfoFx(List<Text> info) {
		info.add(new Text("The citizens are :\n"));
		for (int i = 0; i < citizan.size(); i++) {
			info.add(new Text(i + 1 + ")" + citizan.get(i).toString() + "\n"));
		}
	}

	public void showKalpiesInfo() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < allBallotCitizens.size(); i++) {
			sb.append("The Ballot boxes are :\n");
			sb.append(i + 1 + ") " + allBallotCitizens.get(i).toString() + "\n");
		}
		System.out.println(sb);
		sb = new StringBuffer();
		for (int i = 0; i < allBallotSickCitizens.size(); i++) {
			sb.append("The Corona Ballot boxes are :\n");
			sb.append(i + 1 + ") " + allBallotSickCitizens.get(i).toString() + "\n");
		}
		System.out.println(sb);
		sb = new StringBuffer();
		for (int i = 0; i < allBallotSoldiers.size(); i++) {
			sb.append("The IDF Ballot boxes are :\n");
			sb.append(i + 1 + ") " + allBallotSoldiers.get(i).toString() + "\n");
		}
		System.out.println(sb);
		sb = new StringBuffer();
		for (int i = 0; i < allBallotSickSoldier.size(); i++) {
			sb.append("The sick Soldier Ballot boxes are :\n");
			sb.append(i + 1 + ") " + allBallotSickSoldier.get(i).toString() + "\n");
		}
		System.out.println(sb);

	}

	public void showKalpiesInfoFx(List<Text> info) {

		for (int i = 0; i < allBallotCitizens.size(); i++) {
			info.add(new Text("The Ballot boxes are :\n"));
			info.add(new Text(i + 1 + ") " + allBallotCitizens.get(i).toString() + "\n"));
		}
		for (int i = 0; i < allBallotSickCitizens.size(); i++) {
			info.add(new Text("The Corona Ballot boxes are :\n"));
			info.add(new Text(i + 1 + ") " + allBallotSickCitizens.get(i).toString() + "\n"));
		}
		for (int i = 0; i < allBallotSoldiers.size(); i++) {
			info.add(new Text("The IDF Ballot boxes are :\n"));
			info.add(new Text(i + 1 + ") " + allBallotSoldiers.get(i).toString() + "\n"));
		}
		for (int i = 0; i < allBallotSickSoldier.size(); i++) {
			info.add(new Text("The sick Soldier Ballot boxes are :\n"));
			info.add(new Text(i + 1 + ") " + allBallotSickSoldier.get(i).toString() + "\n"));
		}
	}

	private void showMenu() {
		System.out.println("Press 1 to add a kalpi");
		System.out.println("Press 2 to add a citizen");
		System.out.println("Press 3 to add a party");
		System.out.println("Press 4 to add a party member");
		System.out.println("Press 5 to show all the kalpies");
		System.out.println("Press 6 to show all the citizens");
		System.out.println("Press 7 to show all the parties");
		System.out.println("Press 8 to start the elections");
		System.out.println("Press 9 to show the results of the elections");
		System.out.println("Press 10 to exit :(");
	}

	public void makeACitizenOrAPartyMember(Scanner s, int select, int choose)
			throws CitizenIDOutOfRnage, CitizenAgeIsLessThen18, Exception {
		System.out.println("Please enter the citizen's name");
		String name = s.next();
		System.out.println("Please enter " + name + "'s ID");
		int ID = s.nextInt();
		System.out.println("please enter " + name + "'s birth year");
		int year = s.nextInt();
		if (select == 2) {
			if (choose == 1) // normal
				addCitizen(name, ID, year, 0, false, choose);
			if (choose == 2) // sick
				addCitizen(name, ID, year, daysIso(s), false, choose);// need to ad to sick list
			if (choose == 3) // soldier
				addCitizen(name, ID, year, 0, isArmed(s), choose);
			if (choose == 4) // sick soldier
				addCitizen(name, ID, year, daysIso(s), isArmed(s), choose);
		}

		else if (select == 4) { // if we make a party member
			int iso = 0;
			System.out.println("does " + name + "'s sick? pless enter y for yes and n for no");
			boolean sick = yesOrNo(s);
			if (sick)
				iso = daysIso(s);
			System.out.println("Please enter " + name + "'s party name");
			String partyName = s.next();
			System.out.println("Please enter " + name + "'s rank in the party");
			int rank = s.nextInt();
			addPartyMember(name, ID, year, sick, iso, partyName, rank);
		}
	}

	private Boolean isArmed(Scanner s) {
		System.out.println("does the soldier armed? please enter y for yes and n for no");
		return yesOrNo(s);
	}

	private int daysIso(Scanner s) {
		System.out.println("how many days the citizen is isolated?");
		int iso = -1;
		do {
			iso = s.nextInt();
			if (iso >= 0)
				return iso;
			else
				System.out.println("please enter days positive or zero");
		} while (iso < 0);
		return iso;
	}
	/*
	 * public int setAge(int birthYear) { int age, currentYear; currentYear =
	 * LocalDate.now().getYear(); age = currentYear - birthYear; return age; }
	 */

	public boolean makeAKalpi(Scanner s) {
		System.out.println("Which kalpi you want to create?");
		System.out.println(
				"Press 1 for normal kalpi, 2 for soldiers kalpi , 3 for corona kalpi or 4 for IDF Corona kalpi");
		int a = s.nextInt();
		System.out.println("Don't forget the adress of the kalpi!");
		String adress = s.next();
		switch (a) {
		case 1:
			addNormalKalpi(adress);
			return true;
		case 2:
			addIDFKalpi(adress);
			return true;
		case 3:
			addCoronaKalpi(adress);
			return true;
		case 4:
			addIDFCoronaKalpi(adress);
			return true;
		}
		return false;
	}

	public void makeAParty(Scanner s) {
		System.out.println("Please enter the party's name, without space");
		String name = s.next();
		s.nextLine();
		System.out.println("Please enter the party's ideology (right, center or left)");
		String ideology = s.next();
		System.out.println("Please enter the party's creation month");
		int month = s.nextInt();
		System.out.println("Please enter the part's creation year");
		int year = s.nextInt();
		addParty(name, ideology, month, year);
	}

	private static boolean yesOrNo(Scanner s) {
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

	private static boolean yesOrNoFx(String s) {
		char answer = s.charAt(0);
		do {
			if (answer == 'Y' || answer == 'y')
				return true;
			else if (answer == 'N' || answer == 'n')
				return false;
			System.out.println("You have enterd a wrong answer, try again");
		} while (answer != 'N' || answer != 'n' || answer != 'Y' || answer != 'y');
		return false;
	}

	public void switchCase(int answer, Scanner s) throws CitizenIDOutOfRnage, CitizenAgeIsLessThen18, Exception {
		switch (answer) {
		case 1:
			makeAKalpi(s);
			break;
		case 2:
			int choose = chooseCitien(s);
			makeACitizenOrAPartyMember(s, answer, choose);
			break;
		case 3:
			makeAParty(s);
			break;
		case 4:
			makeACitizenOrAPartyMember(s, answer, 5);
			break;
		case 5:
			showKalpiesInfo();
			break;
		case 6:
			System.out.println(showCitizenInfo());
			break;
		case 7:
			System.out.println(showPartiesInfo());
			break;
		case 8:
			electionsMaker(s);
			break;
		case 9:
			electionsResults();
			break;
		}
	}

	private int chooseCitien(Scanner s) {
		System.out.println("which citizen would you like to make? for normal press 1 , for normal and sick press 2 , "
				+ "for soldier press 3 and for sick soldier press 4");
		int choose;
		do {
			choose = s.nextInt();
			if (choose == 1 || choose == 2 || choose == 3 || choose == 4)
				return choose;
			System.out.println("You have enterd a wrong answer, try again");
		} while (choose != 1 || choose != 2 || choose != 3 || choose != 4);
		return choose;
	}

	public void start(Scanner s) throws CitizenIDOutOfRnage, CitizenAgeIsLessThen18, Exception {
		hardCoded();
		int answer;
		do {
			System.out.println("Please select the wanted option");
			showMenu();
			answer = s.nextInt();
			switchCase(answer, s);
			s.nextLine();
		} while (answer != 10);
		System.out.println("Thank you, goodbye.");
	}

	public void hardCoded() throws CitizenIDOutOfRnage, CitizenAgeIsLessThen18, Exception {
		addParty("halicud", "right", 4, 1973);
		addParty("kahol-lavan", "center", 5, 2019);
		addParty("hahavoda", "left", 6, 1955);
		// 1 for normal 2 for sick 3 for soldier 4 for sick soldier
		addCitizen("Yonatan", 316554260, 1996, 0, false, 1);
		addCitizen("Bar", 316080365, 1996, 0, false, 1);
		addCitizen("anit", 116080365, 2002, 0, false, 3);
		addCitizen("shai", 216080365, 1995, 20, false, 2);
		addCitizen("or", 22, 1980, 10, false, 2);
		addCitizen("ron", 516080365, 2002, 8, true, 4);
		addNormalKalpi("or yehuda");
		addIDFKalpi("tzahal");
		addCoronaKalpi("haredim");
		addIDFCoronaKalpi("sickIDF");
		// true for sick false for not sick
		addPartyMember("bibi", 616080365, 1950, false, 0, "halicud", 1);
		addPartyMember("edelstein", 716080365, 1900, true, 0, "halicud", 2);
		addPartyMember("gantz", 816080365, 1960, false, 0, "kahol-lavan", 1);
		addPartyMember("ashcenazi", 916080365, 1965, true, 2, "kahol-lavan", 2);
		addPartyMember("peretz", 316080360, 1955, false, 0, "hahavoda", 1);
		addPartyMember("shmuli", 316080361, 1975, true, 5, "hahavoda", 2);
	}
}
