package my.pack.project1;

public class InsuredPerson {
	private long ssn; //ssn = social security number = amka
	private String fName, lName;
	private String city;
	private Rantevu rantevuReference;
	
	//constructor
	public InsuredPerson(String fName, String lName, String city, long ssn) {
		this.fName = fName;
		this.lName = lName;
		this.city = city;
		this.ssn = ssn;
	}
	
	//add person's rantevu reference
	public void addRantevuReference(Rantevu rantevuReference) {
		this.rantevuReference = rantevuReference;
	}

	//getters and setters
	public long getSsn() {
		return ssn;
	}
	public void setSsn(long ssn) {
		this.ssn = ssn;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Rantevu getRantevuReference() {
		return rantevuReference;
	}

	public void setRantevuReference(Rantevu rantevuReference) {
		this.rantevuReference = rantevuReference;
	}
	
	
}
