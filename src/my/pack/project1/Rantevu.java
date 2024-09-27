package my.pack.project1;

import java.time.LocalDate;
import java.time.LocalTime;

public class Rantevu {
	private int anc; //anc = appointment number code = kar
	private InsuredPerson person;
	private VaccCenter center;
	private Doctor doctor;
	private LocalDate date;
	private LocalTime time;
	
	//constructor
	public Rantevu(InsuredPerson person, VaccCenter center, Doctor doctor, LocalDate date, LocalTime time, int anc) {
		this.person = person;
		this.center = center;
		this.doctor = doctor;
		this.date = date;
		this.time = time;
		this.anc = anc;
	}

	//print info for rantevu
	public void printRantevu() {
		System.out.println("On " + date.toString() + ", at " + time.toString() + ", ANC: " + getAnc() + ", Doctor: " + doctor.getDocFName() + " " + doctor.getDocLName() + ".\n");
	}
	//getters and setters
	public int getAnc() {
		return anc;
	}

	public void setAnc(int anc) {
		this.anc = anc;
	}


	public InsuredPerson getPerson() {
		return person;
	}

	public void setPerson(InsuredPerson person) {
		this.person = person;
	}

	public VaccCenter getCenter() {
		return center;
	}

	public void setCenter(VaccCenter center) {
		this.center = center;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}
	
}
