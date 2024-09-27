package my.pack.project1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Doctor {
	private int regNumber; //regNumber = registration number = am
	private String docFName, docLName;
	private int rantevusNum = 0;
	private Rantevu[] rantevuReference = new Rantevu[30];
	
	//constructor
	public Doctor(String docFName, String docLName, int regNumber) {
		this.docFName = docFName;
		this.docLName = docLName;
		this.regNumber = regNumber;
	}
	
	//add doctor's rantevu reference
	public void addRantevuReference(Rantevu rantevuReference) {
		this.rantevuReference[rantevusNum] = rantevuReference;
		rantevusNum++;
	}
	
	//check if the doctor is available given day and slot
	public boolean checkDoctor(int day, int slot) {
		LocalDate myDate = LocalDate.now();
		LocalTime myTime = LocalTime.of(9, 0);
		for (int i = 0; i < rantevusNum; i++) {
			if ((rantevuReference[i].getDate().equals(myDate.plusDays(day+1))) && ((myTime.until(rantevuReference[i].getTime(), ChronoUnit.MINUTES)/30) == slot)) {
				return false;
			}
		}
		return true;
	}
	
	//print doctor's rantevus
	public void printRantevus() {
		if (rantevusNum != 0) {
			for (int i = 0; i < rantevusNum; i++) {
				rantevuReference[i].printRantevu();
			}
		}
		else {
			System.out.println("This Doctor has no Rantevus.");
		}
	}
	
	//getters and setters
	public int getRegNumber() {
		return regNumber;
	}
	public void setRegNumber(int regNumber) {
		this.regNumber = regNumber;
	}
	public String getDocFName() {
		return docFName;
	}
	public void setDocFName(String docFName) {
		this.docFName = docFName;
	}
	public String getDocLName() {
		return docLName;
	}
	public void setDocLName(String docLName) {
		this.docLName = docLName;
	}
	public int getRantevusNum() {
		return rantevusNum;
	}

	public void setRantevusNum(int rantevusNum) {
		this.rantevusNum = rantevusNum;
	}

	public Rantevu[] getRantevuReference() {
		return rantevuReference;
	}

	public void setRantevuReference(Rantevu[] rantevuReference) {
		this.rantevuReference = rantevuReference;
	}

	
}
