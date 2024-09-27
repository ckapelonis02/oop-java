package my.pack.project1;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

public class DataSystem {
	
	//final fields
	final private String PROGRAM_NAME = "National Vaccination Program"; 
	final private String URL = "https://www.moh.gov.gr/articles/health/dieythynsh-dhmosias-ygieinhs/emboliasmoi";
	final private int M = 100; //max insured person number
	final private int N = 10; //max vaccination center number
	final private int vaccinationTimePeriod = 7; //in days

	
	//insured persons
	private InsuredPerson[] insPerCatalogue = new InsuredPerson[M]; //catalogue where we store insured persons
	private int insPerNum; //max M
		
	//vaccination centers
	private VaccCenter[] vaccCentCatalogue = new VaccCenter[N]; //catalogue where we store vaccination centers
	private int vaccCentNum; //max N
		
	//reference point for appointment number code
	private int anc;
	
	//user's input for case 4
	Scanner userInput = new Scanner(System.in);
	
	//constructor
	public DataSystem() {
		this.insPerNum = 0;
		this.vaccCentNum = 0;
		this.anc = 40;
	}


	//getters
	public int getVaccinationTimePeriod() {
		return vaccinationTimePeriod;
	}	

	public int getVaccCentNum() {
		return vaccCentNum;
	}

	public int getInsPerNum() {
		return insPerNum;
	}
	
	public InsuredPerson[] getInsPerCatalogue() {
		return insPerCatalogue;
	}

	public VaccCenter[] getVaccCentCatalogue() {
		return vaccCentCatalogue;
	}
	
	
	
	
												/*****CASE 1 - add insured person*****/

	public void addInsuredPerson(String fName, String lName, String city, long ssn) {
		//checking if catalogue is full of M insured persons
		if (insPerNum == M) {
			System.out.println("Cannot add another person. Data system is full.");
			return;
		}

		//add person to DataSystem catalogue and increase insured persons number by one
		insPerCatalogue[insPerNum] = new InsuredPerson(fName, lName, city, ssn);
		insPerNum++;
	}
	
	
	
		
												/*****CASE 2 - add vaccination center*****/
	
	public void addVaccinationCenter(String vaccCenterName, String vaccCenterCity, int vcc) {
		//check if catalogue is full of N vaccination centers
		if (vaccCentNum == N) {
			System.out.println("Cannot add another Vaccination Center. Data system is full.");
			return;
		}

		//add vaccination center in catalogue and increase vaccination center number by one
		vaccCentCatalogue[vaccCentNum] = new VaccCenter(vaccCenterName, vaccCenterCity, vcc);
		vaccCentNum++;	
	}
				
	
	
								/*****CASE 3 - add a doctor to a specific vaccination center of this catalogue*****/
			
	public void addDoctor(String docFName, String docLName, int regNum, int vcc) {
		VaccCenter referenceVC = null;
		
		//finding vaccination center where doctor belongs using a method given the vaccination center code
		referenceVC = findVaccCenter(vcc);
		
		//check if vaccination center exists
		if (referenceVC == null) {
			System.out.println("There is no Vaccination Center with this Code.\n");
			return;
		}
		
		//adding the doctor to the vaccination center catalogue using a method
		referenceVC.addDoctor(docFName, docLName, regNum);
	}

		
	

											


	//print vaccination centers
	public void printVaccCenters() {
		for (int i = 0; i < vaccCentNum; i ++) {
			if (vaccCentCatalogue[i] != null) {
				System.out.println("\t" + vaccCentCatalogue[i].getVaccCenterName() + ", " + vaccCentCatalogue[i].getVaccCenterCity() + ", " + vaccCentCatalogue[i].getVcc());
			}
		}
	}
	
	
	//arranging a rantevu
	public Rantevu arrangeRantevu(VaccCenter vaccCenterReference, Doctor doctorReference, InsuredPerson personReference, int day, int slot) {
		//creating a LocalDate object to help us with dates
		LocalDate myDate = LocalDate.now();
		LocalTime myTime = LocalTime.of(9, 0);
		
		//creating a new rantevu object and adding the rantevu to the corresponding doctor, vaccination center and person
		Rantevu rantevuReference = new Rantevu(personReference, vaccCenterReference, doctorReference, myDate.plusDays(day), myTime.plusMinutes((slot-1)*30), anc);
		doctorReference.addRantevuReference(rantevuReference);
		vaccCenterReference.addRantevu(rantevuReference, doctorReference, day - 1, slot - 1);
		personReference.addRantevuReference(rantevuReference);
		anc++;
		
		return rantevuReference;
	}
	
	//arranging a rantevu with given appointment number code
	public Rantevu arrangeRantevu(VaccCenter vaccCenterReference, Doctor doctorReference, InsuredPerson personReference, int day, int slot, int anc) {
		//creating a LocalDate object to help us with dates
		LocalDate myDate = LocalDate.now();
		LocalTime myTime = LocalTime.of(9, 0);
		
		//creating a new rantevu object and adding the rantevu to the corresponding doctor, vaccination center and person
		Rantevu rantevuReference = new Rantevu(personReference, vaccCenterReference, doctorReference, myDate.plusDays(day), myTime.plusMinutes((slot-1)*30), anc);
		doctorReference.addRantevuReference(rantevuReference);
		vaccCenterReference.addRantevu(rantevuReference, doctorReference, day - 1, slot - 1);
		personReference.addRantevuReference(rantevuReference);
		
		return rantevuReference;
	}
	
		
	//find Vaccination Center with vaccination center code
	public VaccCenter findVaccCenter(int vcc) {
		for (int i = 0; i < vaccCentNum; i++) {
			if (vcc == vaccCentCatalogue[i].getVcc()) {
				return vaccCentCatalogue[i];
			}
		}
		return null;
	}
		
	//find insured person with social security number
	public InsuredPerson findInsuredPerson(long ssn) {
		for (int i = 0; i < insPerNum; i ++) {
			if (ssn == insPerCatalogue[i].getSsn()) {
				return insPerCatalogue[i];
			}
		}
		return null;
	}
		
	//find vaccination center with town name
	public VaccCenter findVaccCenter(String city) {
		for (int i = 0; i < vaccCentNum; i++) {
			if (city.equals(vaccCentCatalogue[i].getVaccCenterCity())) {
				return vaccCentCatalogue[i];
			}
		}
		return null;
	}
		
	//print a rantevu
	public String printRantevu(int i, int j) {
		LocalTime myTime = LocalTime.of(9, 0);
		String result = "\t" + (j+1) + ". ";
		result += myTime.plusMinutes(30*j);
		return result;
	}
		
	//finding a rantevu with insured person's ssn
	public void findRantevuSsn(long ssn) {
		while (true) {
			for (int i = 0; i < insPerNum; i++) {
				if (insPerCatalogue[i].getSsn() == ssn) {
					insPerCatalogue[i].getRantevuReference().printRantevu();
					return;
				}
			}
		}
	}
	
	//finding a rantevu with vaccination center code
	public void findRantevuVcc(int vcc) {
		while (true) {
			for (int i = 0; i < vaccCentNum; i ++) {
				if (vcc == vaccCentCatalogue[i].getVcc()) {
					vaccCentCatalogue[i].printRantevusForVc();
					return;
				}
			}
		}
	}
		
	//finding a rantevu with doctor's registration number
	public void findRantevuDoctorRegNum(int regNum) {
		for (int i = 0; i < vaccCentNum; i++) {
			for (int j = 0; j < vaccCentCatalogue[i].getDocNum(); j++) {
				if (vaccCentCatalogue[i].getDocCatalogue()[j].getRegNumber() == regNum) {
					vaccCentCatalogue[i].getDocCatalogue()[j].printRantevus();
					return;
				}
			}
		}
	}
	
	//finding the doctor depending on less rantevus in general
	public Doctor findDoctor(VaccCenter vaccCenterReference, int day, int slot) {
		Doctor doctorReference = null;
		int minRantevus = 30;
		for (int i = 0; i < vaccCenterReference.getDocNum(); i++) {
			if ((vaccCenterReference.getDocCatalogue()[i].checkDoctor(day-1, slot-1)) && (vaccCenterReference.getDocCatalogue()[i].getRantevusNum() < minRantevus)) {
				doctorReference = vaccCenterReference.getDocCatalogue()[i];
				minRantevus = vaccCenterReference.getDocCatalogue()[i].getRantevusNum();
			}
		}
		return doctorReference;
	}
	
	//printing all insured persons
	public void printInsuredPersons() {
		for (int i = 0; i < insPerNum; i++) {
			System.out.println("" + insPerCatalogue[i].getfName() + " " + insPerCatalogue[i].getlName() + ", " + insPerCatalogue[i].getCity() + ", " + insPerCatalogue[i].getSsn());
		}
	}
}
