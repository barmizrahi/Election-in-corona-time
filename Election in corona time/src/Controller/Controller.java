package Controller;

import javafx.beans.value.ObservableValue;
import javafx.scene.text.Text;

import javafx.scene.layout.HBox;
import java.util.ArrayList;
import java.util.List;
import Model.Citizan;
import Model.CitizenIsNotASoldier;
import Model.Elections;
import Model.Party;
import Model.PartyMember;
import Model.Sick;
import Model.SickSoldier;
import Model.Soldier;
import View.MyMessage;
import Model.CitizenAgeIsLessThen18;
import Model.CitizenIDOutOfRnage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class Controller {
	private Model.Model theModel;
	private View.View theView;

	public Controller(View.View view, Model.Model model) throws CitizenIDOutOfRnage, CitizenAgeIsLessThen18, Exception {
		theModel = model;
		theView = view;
		theModel.getElection().hardCoded();
		// chose 1- citizen , chose 2-sick , chose 3- soldier ,chose4-sicksol , chose5 -
		// kalpi , chose6-party ,chose7-partymember
		// chose8-showcit , chose9-showparties , chose10-showballots, chose11-elections
		// , shose12-showelec
		theView.addCitizenDetails(new EventHandler<ActionEvent>() { // for normal person

			@Override
			public void handle(ActionEvent event) {
				theView.setBP(theView.getVbCit());
				theView.getVbCit().getChildren().addAll(theView.getHbName(), theView.getHbYear(), theView.getHbID(),
						theView.getOk());
				theView.setChoose(1); // need to change every time
			}
		});

		theView.addSickDetails(new EventHandler<ActionEvent>() { // for sick citizen

			@Override
			public void handle(ActionEvent event) {
				theView.setBP(theView.getVbSick());
				theView.getVbSick().getChildren().addAll(theView.getHbName(), theView.getHbYear(), theView.getHbID(),
						theView.getHbDayIso(), theView.getOk());
				theView.setChoose(2);
			}
		});

		theView.addSoldierDetails(new EventHandler<ActionEvent>() { // for soldier

			@Override
			public void handle(ActionEvent event) {
				theView.setBP(theView.getVbSol());
				theView.getVbSol().getChildren().addAll(theView.getHbName(), theView.getHbYear(), theView.getHbID(),
						theView.getCbCarryWepon(), theView.getOk());
				theView.setChoose(3);
			}

		});

		theView.addSickSoldierDetails(new EventHandler<ActionEvent>() { // for sick soldier

			@Override
			public void handle(ActionEvent event) {
				theView.setBP(theView.getVbsickSol());
				theView.getVbsickSol().getChildren().addAll(theView.getHbName(), theView.getHbYear(), theView.getHbID(),
						theView.getHbDayIso(), theView.getCbCarryWepon(), theView.getOk());
				theView.setChoose(4);
			}
		});

		theView.addKalpi(new EventHandler<ActionEvent>() { // create kalpi

			@Override
			public void handle(ActionEvent event) {
				theView.setBP(theView.getVbKalpi());
				theView.getVbKalpi().getChildren().addAll(theView.getHbKlapi(), theView.getRbNormal(),
						theView.getRbSick(), theView.getRbSoldier(), theView.getRbSickSoldier(), theView.getOk());
				theView.setChoose(5);
			}
		});

		theView.addParty(new EventHandler<ActionEvent>() { // create party

			@Override
			public void handle(ActionEvent event) {
				theView.setBP(theView.getVbParty());
				theView.getVbParty().getChildren().addAll(theView.getHbPartyName(), theView.getHbPartyMonth(),
						theView.getHbPartyYear(), theView.getPartyIdeology(), theView.getRbLeft(),
						theView.getRbCenter(), theView.getRbRight(), theView.getOk());
				theView.setChoose(6);
			}
		});

		theView.addMember(new EventHandler<ActionEvent>() { // create party member

			@Override
			public void handle(ActionEvent event) {
				theView.setBP(theView.getVbMem());
				theView.getVbMem().getChildren().addAll(theView.getHbName(), theView.getHbYear(), theView.getHbID(),
						theView.getHbMemberParty(), theView.getHbMemberRank(), theView.getCbMemberSick(),
						theView.getOk());
				theView.setChoose(7);
			}
		});

		theView.showCitizenInfo(new EventHandler<ActionEvent>() { // show citizens

			@Override
			public void handle(ActionEvent event) {
				theView.getVbShowCit().getChildren().clear();
				theView.setBP(theView.getVbShowCit());
				List<Text> info = new ArrayList<>();
				theModel.getElection().showCitizenInfoFx(info);
				for (int i = 0; i < info.size(); i++) {
					theView.getVbShowCit().getChildren().add(info.get(i));
				}
			}
		});

		theView.showpartiesInfo(new EventHandler<ActionEvent>() { // show party

			@Override
			public void handle(ActionEvent event) {
				theView.getVbShowParties().getChildren().clear();
				theView.setBP(theView.getVbShowParties());
				List<Text> info = new ArrayList<>();
				theModel.getElection().showPartiesInfoFx(info);
				for (int i = 0; i < info.size(); i++) {
					theView.getVbShowParties().getChildren().add(info.get(i));
				}
			}
		});

		theView.showKalpiInfo(new EventHandler<ActionEvent>() { // show kalpi

			@Override
			public void handle(ActionEvent event) {
				theView.getVbShowkalpi().getChildren().clear();
				theView.setBP(theView.getVbShowkalpi());
				List<Text> info = new ArrayList<>();
				theModel.getElection().showKalpiesInfoFx(info);
				for (int i = 0; i < info.size(); i++) {
					theView.getVbShowkalpi().getChildren().add(info.get(i));
				}
			}
		});
		theView.startTheElection(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				theView.setBP(theView.getVbElectionMaker());
				theView.getVbElectionMaker().getChildren().addAll(theView.getVbstartTheElection(), theView.getOk());
				theView.setChoose(8);
				// int year = Integer.parseInt(theView.getTelecYear().getText());
				// theModel.getElection().electionsMakerFx(Integer.parseInt(theView.getTelecYear().getText()),
				// Integer.parseInt(theView.getTElecMon().getText()));
				// try {
				// theModel.getElection().addCitizensToKalpiFx(theView);
				// } catch (Exception e) {
				// MyMessage msg = new MyMessage(new Stage());
				// msg.addMessage("Object Is Already On The List");
				// }
				// theView.getVbElectionMaker().getChildren().addAll(theView.getHbWantToWote(),
				// theView.getYes(),theView.getNo());
			}
		});
		
		theView.showElections(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				theView.setBP(theView.getVbShowElection());
				theView.getPieResults().getData().clear();
				theModel.getElection().electionsResultsFx(theView.getPieResults());
				theView.getVbShowElection().getChildren().addAll(theView.getPieResults());
				
			}
			
		});

		theView.addRbYesEvent(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				theView.setYesOrNo(true);

				// theView.getVbElectionMaker().getChildren().clear();

			}
		});

		theView.addRbNoEvent(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				theView.setYesOrNo(false);
				theView.getVbElectionMaker().getChildren().clear();
			}
		});

		theView.okButtonEvent(new EventHandler<ActionEvent>() { // ok button

			@Override
			public void handle(ActionEvent event) {
				int choose = theView.getChoose();
				MyMessage msg = new MyMessage(new Stage());
				Elections elec = theModel.getElection();
				try {

					if (choose == 1) { // create normal
						Citizan c = new Citizan(theView.getTname().getText(),
								Integer.parseInt(theView.getTid().getText()),
								Integer.parseInt(theView.getTyear().getText()), false, false);
						if (elec.addFullCitizen(c, null, null, null, 1))
							;
						msg.addMessage("citizen was added successfully!");
						theView.getVbCit().getChildren().clear();
					}
					if (choose == 2) {// create sick
						Sick s = new Sick(theView.getTname().getText(), Integer.parseInt(theView.getTid().getText()),
								Integer.parseInt(theView.getTyear().getText()),
								Integer.parseInt(theView.getTdaysInIso().getText()), true, false);
						if (elec.addFullCitizen(null, s, null, null, choose))
							;
						msg.addMessage("citizen was added successfully!");
						theView.getVbSick().getChildren().clear();
					}
					if (choose == 3) { // create soldier
						Soldier sol = new Soldier(theView.getTname().getText(),
								Integer.parseInt(theView.getTid().getText()),
								Integer.parseInt(theView.getTyear().getText()), theView.isCarryWepon(), true, false);
						if (elec.addFullCitizen(null, null, sol, null, choose))
							;
						msg.addMessage("citizen was added successfully!");
						theView.getCbCarryWepon().setSelected(false);
						theView.getVbSol().getChildren().clear();
					}
					if (choose == 4) { // create sick soldier
						SickSoldier Sl = new SickSoldier(theView.getTname().getText(),
								Integer.parseInt(theView.getTid().getText()),
								Integer.parseInt(theView.getTyear().getText()), theView.isCarryWepon(),
								Integer.parseInt(theView.getTdaysInIso().getText()), true, true);
						if (elec.addFullCitizen(null, null, null, Sl, choose))
							;
						msg.addMessage("citizen was added successfully!");
						theView.getCbCarryWepon().setSelected(false);
						theView.getVbsickSol().getChildren().clear();
					}

				} catch (CitizenIDOutOfRnage e) {
					msg.addMessage(e.getMessage());
				} catch (CitizenAgeIsLessThen18 e) {
					msg.addMessage(e.getMessage());
				} catch (CitizenIsNotASoldier e) {
					msg.addMessage(e.getMessage());
				} catch (Exception e) {
					msg.addMessage(e.getMessage());
				}

				if (choose == 5) { // this function is just like in election
					int type = theView.getKalpiType();
					String adress = theView.getTKalpiAdress().getText();
					msg.addMessage("ballot box was added successfully!");
					switch (type) {
					case 1:
						elec.addNormalKalpi(adress); // normal
						theView.getRbNormal().setSelected(false);
						theView.clearTextFiled();
						break;
					case 2:
						elec.addIDFKalpi(adress); // soldier
						theView.getRbSoldier().setSelected(false);
						theView.clearTextFiled();
						break;
					case 3:
						elec.addCoronaKalpi(adress); // sick
						theView.getRbSick().setSelected(false);
						theView.clearTextFiled();
						break;
					case 4:
						elec.addIDFCoronaKalpi(adress); // sick soldier
						theView.getRbSickSoldier().setSelected(false);
						theView.clearTextFiled();
						break;
					}
					theView.getVbKalpi().getChildren().clear();
					elec.showKalpiesInfo();
				}
				if (choose == 6) { // here we create a party
					if (elec.addParty(theView.getTpartyName().getText(), theView.getPartyIdeologyFromUser(),
							Integer.parseInt(theView.getTpartyCreationMonth().getText()),
							Integer.parseInt(theView.getTpartyCreationYear().getText()))) {
						msg.addMessage("party was added successfully!");
					}
					theView.getVbParty().getChildren().clear();
				}

				if (choose == 7) { // create partyMember
					if (elec.addPartyMember(theView.getTname().getText(), Integer.parseInt(theView.getTid().getText()),
							Integer.parseInt(theView.getTyear().getText()), theView.getMemberSick(),
							theView.getMemberDaysIso(), theView.getTpartyMember().getText(),
							Integer.parseInt(theView.getTrankMember().getText())))
						msg.addMessage("party member was Added successfully");

					else
						msg.addMessage("can not added this party member");

					theView.getVbMem().getChildren().clear();
				}
				if (choose == 8) { // start the election
					theModel.getElection().electionsMakerFx(Integer.parseInt(theView.getTelecYear().getText()),
							Integer.parseInt(theView.getTElecMon().getText())); // get the info from the user about the
																				// election delatis
					theView.getVbElectionMaker().getChildren().clear();
					try {
						theModel.getElection().electionsMakerFx(theView, msg);
					} catch (Exception e) {
						msg.addMessage(e.getMessage());
					}
					theView.getVbRight().getChildren().add(theView.getBtShowElec());
				}
				elec.electionsResults();
				theView.clearTextFiled();
			}
		});

	}

}
