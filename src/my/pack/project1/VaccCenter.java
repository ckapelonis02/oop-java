package my.pack.project1;


public class VaccCenter {
	final private int D = 5;
	private int vcc; //vcc = vaccination center code = kek
	private String vaccCenterName, vaccCenterCity;
	private Doctor[] docCatalogue = new Doctor[D];
	private int docNum;
	private Rantevu[][][] catalogue = new Rantevu[7][4][D];
	private int rantevusNum = 0;
	
	//constructor
	public VaccCenter(String vaccCenterName, String vaccCenterCity, int vcc) {
		this.vcc = vcc;
		this.vaccCenterName = vaccCenterName;
		this.vaccCenterCity = vaccCenterCity;
		this.docNum = 0;
	}
	
	//adding a doctor
	public void addDoctor(String docFName, String docLName, int regNum) {
		if (docNum == D) {
			System.out.println("You cannot add any other doctors in this Vaccination Center because it is full (" + D + "doctors).");
			System.exit(0);
		}
		docCatalogue[docNum] = new Doctor(docFName, docLName, regNum);
		docNum++;
	}
	
	//adding a rantevu
	public void addRantevu(Rantevu rantevuReference, Doctor doctorReference, int day, int slot) {
		int index = 0;
		for (int i = 0; i < docNum; i++) {
			if (doctorReference.equals(docCatalogue[i])) {
				index = i;
			}
		}
		this.catalogue[day][slot][index] = rantevuReference;
		rantevusNum++;
	}
	
	//printing all rantevus for a vaccination center
	public void printRantevusForVc() {
		if (rantevusNum != 0) {
			for (int i = 0; i < 7; i++) {
				for (int j = 0; j < 4; j++) {
					for (int k = 0; k < D; k++) {
						if (catalogue[i][j][k] != null) {
							catalogue[i][j][k].printRantevu();
						}
					}	
				}
			}
		}
		else {
			System.out.println("There are no Rantevus for this Vaccination Center.");
		}
	}
	
	//printing all doctors for a vaccination center
	public void printDoctors() {
		for (int i = 0; i < docNum; i++) {
			System.out.println("" + docCatalogue[i].getDocFName() + " " + docCatalogue[i].getDocLName() + ", " + docCatalogue[i].getRegNumber() +  ", " + vaccCenterName);
		}
	}
	
	
	//getters and setters
	public int getVcc() {
		return vcc;
	}

	public void setVcc(int vcc) {
		this.vcc = vcc;
	}

	public String getVaccCenterName() {
		return vaccCenterName;
	}

	public void setVaccCenterName(String vaccCenterName) {
		this.vaccCenterName = vaccCenterName;
	}

	public String getVaccCenterCity() {
		return vaccCenterCity;
	}

	public void setVaccCenterCity(String vaccCenterCity) {
		this.vaccCenterCity = vaccCenterCity;
	}

	public Rantevu[][][] getCatalogue() {
		return catalogue;
	}

	public void setCatalogue(Rantevu[][][] catalogue) {
		this.catalogue = catalogue;
	}
	
	public Doctor[] getDocCatalogue() {
		return docCatalogue;
	}

	public void setDocCatalogue(Doctor[] docCatalogue) {
		this.docCatalogue = docCatalogue;
	}

	public int getDocNum() {
		return docNum;
	}

	public void setDocNum(int docNum) {
		this.docNum = docNum;
	}

	
	
}
