package Controller;

import java.util.Scanner;

import Model.CitizenAgeIsLessThen18;
import Model.CitizenIDOutOfRnage;

public interface electioneble {
	void hardCoded() throws CitizenIDOutOfRnage, CitizenAgeIsLessThen18, Exception;

	boolean makeAKalpi(Scanner s);

	void makeACitizenOrAPartyMember(Scanner s, int answer, int choose)
			throws CitizenIDOutOfRnage, CitizenAgeIsLessThen18, Exception;

	void makeAParty(Scanner s);

	void showKalpiesInfo();

	String showCitizenInfo();

	String showPartiesInfo();

	void electionsMaker(Scanner s) throws Exception;

	

}
