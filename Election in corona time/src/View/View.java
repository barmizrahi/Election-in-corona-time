package View;

import javafx.stage.Stage;
import javafx.scene.control.CheckBox;

import java.util.ArrayList;
import java.util.List;

import Model.Elections;
import Model.Party;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.chart.PieChart;
import javafx.scene.Scene;

public class View {
	private int choose, MemberDaysIso;
	private boolean isMemberSick, isWantToWote, wearingProtectSuit, yesOrNo;

	private Group root;
	private BorderPane bp;
	private ToggleGroup tgr;
	private VBox vbLeft, vbRight, vbCit, vbSick, vbSol, vbMem, vbsickSol, vbParty, vbKalpi, vbShowCit, vbShowParties,
			vbShowElection, vbElectionMaker, vbShowKalpi, vbstartTheElection;
	private HBox hbName, hbID, hbYear, hbKlapi, hbMemberParty, hbPartyName, hbMemberRank, hbDayIso, hbCarry,
			hbPartyMonth, hbPartyYear, hbYesOrNo, hbWearingSuit, hbWantToWote, hbelecMon, hbElecYear;
	private Button btElec, btCitizen, btParty, btKalpi, btMember, btSoldier, btSick, btSickSoldier, btShowCit,
			btShowKalpi, btShowParty, btShowElec, ok, yes, no;
	private RadioButton rbNormal, rbSick, rbSoldier, rbSickSoldier, rbLeft, rbRight, rbCenter, rbYes, rbNo;
	private Text name, id, year, daysInISo, carryWeapon, partyName, rankMember, partyMember, kalpiAdress, partyIdeology,
			partyCreationMonth, partyCreationYear, wantToWote, WearingSuit, elecYear, ElecMon;
	private TextField Tname, Tid, Tyear, TdaysInIso, TcarryWeapon, TpartyName, TrankMember, TpartyMember, TKalpiAdress,
			TpartyCreationMonth, TpartyCreationYear, TelecYear, TElecMon, TWearingSuit;
	private CheckBox cbCarryWepon, cbWantTovote, cbMemberSick;
	private List<Button> choseParty;
	private PieChart pieResults;

	public View(Stage stage) { // here we create all the start view the buttons
		choseParty = new ArrayList<>();
		root = new Group();
		bp = new BorderPane();
		ok = new Button("Ok"); // after pressing this all the details will save
		vbLeft = new VBox();
		vbRight = new VBox();
		vbShowCit = new VBox();
		vbShowParties = new VBox();
		vbShowKalpi = new VBox();
		vbElectionMaker = new VBox();
		vbShowElection = new VBox();
		vbstartTheElection = new VBox();
		hbName = new HBox();
		hbID = new HBox();
		hbYear = new HBox();
		hbPartyName = new HBox();
		hbPartyMonth = new HBox();
		hbPartyYear = new HBox();
		hbDayIso = new HBox();
		hbKlapi = new HBox();
		hbMemberParty = new HBox();
		hbCarry = new HBox();
		hbMemberRank = new HBox();
		hbYesOrNo = new HBox();
		hbWantToWote = new HBox();
		hbWearingSuit = new HBox();
		hbElecYear = new HBox();
		hbelecMon = new HBox();
		tgr = new ToggleGroup();
		cbWantTovote = new CheckBox("want to vote?"); // add set for the name voting

		
		btCitizen = new Button("create citizen");
		btCitizen.setPrefWidth(150);
		btKalpi = new Button("create ballot box");
		btKalpi.setPrefWidth(150);
		btMember = new Button("create candidate ");
		btMember.setPrefWidth(150);
		btParty = new Button("create party");
		btParty.setPrefWidth(150);
		btSick = new Button("create sick citizen");
		btSick.setPrefWidth(150);
		btSoldier = new Button("create soldier");
		btSoldier.setPrefWidth(150);
		btSickSoldier = new Button("create sick soldier");
		btSickSoldier.setPrefWidth(150);
		vbLeft.getChildren().addAll(btCitizen, btSick, btSoldier, btSickSoldier, btMember, btKalpi, btParty);
		vbLeft.setAlignment(Pos.TOP_LEFT);
		
		btElec = new Button("start the election!");
		btElec.setPrefWidth(150);
		btShowCit = new Button("show all citizens");
		btShowCit.setPrefWidth(150);
		btShowElec = new Button("show the election");
		btShowElec.setPrefWidth(150);
		btShowKalpi = new Button("show the kalpies");
		btShowKalpi.setPrefWidth(150);
		btShowParty = new Button("show the parties");
		btShowParty.setPrefWidth(150);
		vbRight.getChildren().addAll(btShowCit, btShowKalpi, btShowParty,btElec);
		vbRight.setAlignment(Pos.TOP_RIGHT);

		name = new Text("Name:");
		id = new Text("ID:");
		year = new Text("Year:");
		daysInISo = new Text("Days isolated:");
		carryWeapon = new Text("Are you carrying weapon?");
		rankMember = new Text("Rank:");
		kalpiAdress = new Text("ballot box adress:");
		partyName = new Text("Party name:");
		partyCreationMonth = new Text("Party creation month:");
		partyCreationYear = new Text("Party creation year:");
		partyMember = new Text("party name:");
		rankMember = new Text("rank:");
		WearingSuit = new Text("are you wearing a protected suit?"); // נועה חופרת
		wantToWote = new Text("do you want to vote?");
		elecYear = new Text("year:");
		ElecMon = new Text("month");

		Tname = new TextField();
		Tid = new TextField();
		Tyear = new TextField();
		TcarryWeapon = new TextField();
		TdaysInIso = new TextField();
		TrankMember = new TextField();
		TKalpiAdress = new TextField();
		TpartyName = new TextField();
		TpartyCreationMonth = new TextField();
		TpartyCreationYear = new TextField();
		TpartyMember = new TextField();
		TrankMember = new TextField();
		TelecYear = new TextField();
		TElecMon = new TextField();
		TWearingSuit = new TextField();

		hbName.getChildren().addAll(name, Tname);
		hbName.setAlignment(Pos.CENTER);
		hbYear.getChildren().addAll(year, Tyear);
		hbYear.setAlignment(Pos.CENTER);
		hbID.getChildren().addAll(id, Tid);
		hbID.setAlignment(Pos.CENTER);
		vbCit = new VBox();

		hbDayIso.getChildren().addAll(daysInISo, TdaysInIso);
		hbDayIso.setAlignment(Pos.CENTER);
		vbSick = new VBox();

		cbCarryWepon = new CheckBox("do you carry weapon?"); // add set for the soldier name
		cbCarryWepon.setAlignment(Pos.CENTER);
		vbSol = new VBox();

		vbsickSol = new VBox();

		rbNormal = new RadioButton("normal ballot box");
		rbNormal.setToggleGroup(tgr);
		rbSick = new RadioButton("Corona ballot box");
		rbSick.setToggleGroup(tgr);
		rbSoldier = new RadioButton("soldier ballot box");
		rbSoldier.setToggleGroup(tgr);
		rbSickSoldier = new RadioButton("sick Soldier ballot box");
		rbSickSoldier.setToggleGroup(tgr);
		hbKlapi.getChildren().addAll(kalpiAdress, TKalpiAdress);
		hbKlapi.setAlignment(Pos.CENTER);
		vbKalpi = new VBox();

		hbPartyName.getChildren().addAll(partyName, TpartyName);
		hbPartyName.setAlignment(Pos.CENTER);

		hbPartyMonth.getChildren().addAll(partyCreationMonth, TpartyCreationMonth);
		hbPartyMonth.setAlignment(Pos.CENTER);

		hbPartyYear.getChildren().addAll(partyCreationYear, TpartyCreationYear);
		hbPartyYear.setAlignment(Pos.CENTER);

		rbCenter = new RadioButton("Center");
		rbCenter.setToggleGroup(tgr);
		rbRight = new RadioButton("Right");
		rbRight.setToggleGroup(tgr);
		rbLeft = new RadioButton("Left");
		rbLeft.setToggleGroup(tgr);
		partyIdeology = new Text("Party ideology:");

		vbParty = new VBox();
		
		pieResults = new PieChart();

		cbMemberSick = new CheckBox("is the member sick?");
		hbMemberParty.getChildren().addAll(partyMember, TpartyMember); // party name
		hbMemberRank.setAlignment(Pos.CENTER);

		hbMemberRank.getChildren().addAll(rankMember, TrankMember); // rank
		hbMemberParty.setAlignment(Pos.CENTER);
		// create yes no option for w/e we like to do with it
		rbYes = new RadioButton("yes");
		rbYes.setToggleGroup(tgr);
		rbNo = new RadioButton("no");
		rbNo.setToggleGroup(tgr);
		hbYesOrNo.getChildren().addAll(rbYes, rbNo);

		yes = new Button("ok");
		no = new Button("no");
		// hbYesOrNo.getChildren().addAll(yes, no);

		hbElecYear.getChildren().addAll(elecYear, TelecYear);
		hbelecMon.getChildren().addAll(ElecMon, TElecMon);
		vbstartTheElection.getChildren().addAll(hbElecYear, hbelecMon);
		hbWantToWote.getChildren().addAll(wantToWote);
		hbWantToWote.setAlignment(Pos.CENTER);

		hbWearingSuit.getChildren().addAll(WearingSuit , TWearingSuit);
		hbWearingSuit.setAlignment(Pos.CENTER);

		vbMem = new VBox();

		bp.setLeft(vbLeft);
		bp.setRight(vbRight);

		Scene scene = new Scene(bp, 800, 600);

		stage.setScene(scene);
		stage.show();
	}

	public VBox getVbstartTheElection() {
		return vbstartTheElection;
	}

	public HBox getHbelecMon() {
		return hbelecMon;
	}

	public HBox getHbElecYear() {
		return hbElecYear;
	}

	public Text getElecYear() {
		return elecYear;
	}

	public Text getElecMon() {
		return ElecMon;
	}

	public TextField getTelecYear() {
		return TelecYear;
	}

	public TextField getTElecMon() {
		return TElecMon;
	}

	public HBox getHbMemberParty() {
		return hbMemberParty;
	}

	public HBox getHbMemberRank() {
		return hbMemberRank;
	}

	public Text getPartyMember() {
		return partyMember;
	}

	public TextField getTpartyMember() {
		return TpartyMember;
	}

	public RadioButton getRbNormal() {
		return rbNormal;
	}

	public RadioButton getRbSick() {
		return rbSick;
	}

	public RadioButton getRbSoldier() {
		return rbSoldier;
	}

	public RadioButton getRbSickSoldier() {
		return rbSickSoldier;
	}

	public void addCitizenDetails(EventHandler<ActionEvent> event) {
		btCitizen.setOnAction(event);
	}

	public void okButtonEvent(EventHandler<ActionEvent> event) {
		ok.setOnAction(event);
	}

	public void addSickDetails(EventHandler<ActionEvent> eventSick) {
		btSick.setOnAction(eventSick);
	}

	public void addSoldierDetails(EventHandler<ActionEvent> event) {
		btSoldier.setOnAction(event);
	}

	public void addSickSoldierDetails(EventHandler<ActionEvent> event) {
		btSickSoldier.setOnAction(event);
	}

	public void addRbYesEvent(EventHandler<ActionEvent> event) {
		rbYes.setOnAction(event);
	}

	public void addRbNoEvent(EventHandler<ActionEvent> event) {
		rbNo.setOnAction(event);
	}

	public void addKalpi(EventHandler<ActionEvent> event) {
		btKalpi.setOnAction(event);
	}

	public void addParty(EventHandler<ActionEvent> event) {
		btParty.setOnAction(event);
	}

	public void addMember(EventHandler<ActionEvent> event) {
		btMember.setOnAction(event);
	}

	public void showCitizenInfo(EventHandler<ActionEvent> event) {
		btShowCit.setOnAction(event);
	}

	public void showpartiesInfo(EventHandler<ActionEvent> event) {
		btShowParty.setOnAction(event);
	}

	public void showKalpiInfo(EventHandler<ActionEvent> event) {
		btShowKalpi.setOnAction(event);
	}
	
	public void showElections(EventHandler<ActionEvent> event) {
		btShowElec.setOnAction(event);
	}
	
	public void yes(EventHandler<ActionEvent> event) {
		yes.setOnAction(event);
	}

	public void no(EventHandler<ActionEvent> event) {
		no.setOnAction(event);
	}

	public void startTheElection(EventHandler<ActionEvent> event) {
		btElec.setOnAction(event);
	}

	public boolean isMemberSick() {
		return isMemberSick;
	}

	public VBox getVbShowParties() {
		return vbShowParties;
	}

	public void addChangeListenerToSickMember(ChangeListener<Boolean> cl) {
		cbMemberSick.selectedProperty().addListener(cl);
		isMemberSick = true;
	}

	public void clearTextFiled() {
		Tname.clear();
		Tid.clear();
		Tyear.clear();
		TcarryWeapon.clear();
		TdaysInIso.clear();
		TpartyName.clear();
		TrankMember.clear();
		TKalpiAdress.clear();
		TpartyCreationMonth.clear();
		TpartyCreationYear.clear();

	}

	public void setMemberSick(boolean isMemberSick) {
		this.isMemberSick = isMemberSick;
	}

	public boolean getMemberSick() {
		return isMemberSick;
	}

	public boolean isCarryWepon() {
		if (cbCarryWepon.isSelected())
			return true;
		else
			return false;
	}

	public int getKalpiType() {
		if (rbNormal.isSelected())
			return 1;
		else if (rbSoldier.isSelected())
			return 2;
		else if (rbSick.isSelected())
			return 3;
		else if (rbSickSoldier.isSelected())
			return 4;
		return 0;
	}

	public String getPartyIdeologyFromUser() {
		if (rbCenter.isSelected())
			return "center";
		if (rbRight.isSelected())
			return "right";
		if (rbLeft.isSelected())
			return "left";
		return "";
	}

	public void addPartyToListParty(Party party) { // add new button to the party List
		choseParty.add(new Button(party.getName()));
	}
	
	

	public CheckBox getCbMemberSick() {
		return cbMemberSick;
	}

	public Group getRoot() {
		return root;
	}

	public BorderPane getBp() {
		return bp;
	}

	public HBox getHbKlapi() {
		return hbKlapi;
	}

	public Text getKalpiAdress() {
		return kalpiAdress;
	}

	public TextField getTKalpiAdress() {
		return TKalpiAdress;
	}

	public CheckBox getCbWantTovote() {
		return cbWantTovote;
	}

	public ToggleGroup getTgr() {
		return tgr;
	}

	public VBox getVbLeft() {
		return vbLeft;
	}

	public VBox getVbRight() {
		return vbRight;
	}

	public boolean isWantToWote() {
		return isWantToWote;
	}

	public boolean isWearingProtectSuit() {
		return wearingProtectSuit;
	}

	public VBox getVbShowElection() {
		return vbShowElection;
	}

	public VBox getVbElectionMaker() {
		return vbElectionMaker;
	}

	public VBox getVbShowKalpi() {
		return vbShowKalpi;
	}

	public HBox getHbYesOrNo() {
		return hbYesOrNo;
	}

	public Button getYes() {
		return yes;
	}

	public Button getNo() {
		return no;
	}

	public Text getWantToWote() {
		return wantToWote;
	}

	public List<Button> getChoseParty() {
		return choseParty;
	}

	public VBox getVbCit() {
		return vbCit;
	}

	public VBox getVbSick() {
		return vbSick;
	}

	public VBox getVbSol() {
		return vbSol;
	}

	public VBox getVbMem() {
		return vbMem;
	}

	public VBox getVbsickSol() {
		return vbsickSol;
	}

	public Text getPartyCreationMonth() {
		return partyCreationMonth;
	}

	public Text getPartyCreationYear() {
		return partyCreationYear;
	}

	public boolean isYesOrNo() {
		return yesOrNo;
	}

	public void setYesOrNo(boolean yesOrNo) {
		this.yesOrNo = yesOrNo;
	}

	public TextField getTpartyCreationMonth() {
		return TpartyCreationMonth;
	}

	public TextField getTpartyCreationYear() {
		return TpartyCreationYear;
	}

	public Text getPartyIdeology() {
		return partyIdeology;
	}

	public HBox getHbPartyMonth() {
		return hbPartyMonth;
	}

	public HBox getHbPartyYear() {
		return hbPartyYear;
	}

	public RadioButton getRbLeft() {
		return rbLeft;
	}

	public RadioButton getRbRight() {
		return rbRight;
	}

	public RadioButton getRbCenter() {
		return rbCenter;
	}

	public RadioButton getRbYes() {
		return rbYes;
	}

	public RadioButton getRbNo() {
		return rbNo;
	}

	public VBox getVbParty() {
		return vbParty;
	}

	public VBox getVbKalpi() {
		return vbKalpi;
	}

	public int getMemberDaysIso() {
		return MemberDaysIso;
	}

	public void setMemberDaysIso(int memberDaysIso) {
		MemberDaysIso = memberDaysIso;
	}

	public HBox getHbName() {
		return hbName;
	}

	public CheckBox getCbCarryWepon() {
		return cbCarryWepon;
	}

	public HBox getHbID() {
		return hbID;
	}

	public HBox getHbYear() {
		return hbYear;
	}

	public HBox getHbKlapiAdress() {
		return hbKlapi;
	}

	public HBox getHbPartyName() {
		return hbPartyName;
	}

	public HBox getHbDayIso() {
		return hbDayIso;
	}

	public HBox getHbCarry() {
		return hbCarry;
	}

	public TextField getTWearingSuit() {
		return TWearingSuit;
	}

	public Button getBtElec() {
		return btElec;
	}

	public Button getBtCitizen() {
		return btCitizen;
	}

	public Button getBtParty() {
		return btParty;
	}

	public Button getBtKalpi() {
		return btKalpi;
	}

	public int getChoose() {
		return choose;
	}

	public void setChoose(int choose) {
		this.choose = choose;
	}

	public Button getBtMember() {
		return btMember;
	}

	public Button getBtSoldier() {
		return btSoldier;
	}

	public Button getBtSick() {
		return btSick;
	}

	public Button getBtSickSoldier() {
		return btSickSoldier;
	}

	public Button getBtShowCit() {
		return btShowCit;
	}

	public Button getBtShowKalpi() {
		return btShowKalpi;
	}

	public Button getBtShowParty() {
		return btShowParty;
	}

	public Button getBtShowElec() {
		return btShowElec;
	}

	public Button getOk() {
		return ok;
	}

	public Text getName() {
		return name;
	}

	public Text getId() {
		return id;
	}

	public Text getYear() {
		return year;
	}

	public Text getDaysInISo() {
		return daysInISo;
	}

	public Text getCarryWeapon() {
		return carryWeapon;
	}

	public Text getPartyName() {
		return partyName;
	}

	public Text getRankMember() {
		return rankMember;
	}

	public TextField getTname() {
		return Tname;
	}

	public TextField getTid() {
		return Tid;
	}

	public TextField getTyear() {
		return Tyear;
	}

	public TextField getTdaysInIso() {
		return TdaysInIso;
	}

	public TextField getTcarryWeapon() {
		return TcarryWeapon;
	}

	public TextField getTpartyName() {
		return TpartyName;
	}

	public TextField getTrankMember() {
		return TrankMember;
	}

	public void setBP(VBox vb) {
		bp.setCenter(vb);
	}

	public VBox getVbShowCit() {
		return vbShowCit;
	}

	public VBox getVbShowkalpi() {
		return vbShowKalpi;
	}

	public HBox getHbWearingSuit() {
		return hbWearingSuit;
	}

	public HBox getHbWantToWote() {
		return hbWantToWote;
	}

	public Text getWearingSuit() {
		return WearingSuit;
	}

	public PieChart getPieResults() {
		return pieResults;
	}
}
