package my.pack.project1;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		//user's Input
		Scanner userInput = new Scanner(System.in);
		int choice = 0;
		
		DataSystem myDataSystem = new DataSystem();
		
														/***** SOME STARTING DATA *****/
		
		myDataSystem.addInsuredPerson("Asfalismenos", "1", "Chania", 11111111);
		myDataSystem.addInsuredPerson("Asfalismenos", "2", "Chania", 22222222);
		myDataSystem.addInsuredPerson("Asfalismenos", "3", "Chania", 33333333);
		myDataSystem.addInsuredPerson("Asfalismenos", "4", "Chania", 44444444);
		myDataSystem.addInsuredPerson("Asfalismenos", "5", "Chania", 55555555);
		myDataSystem.addInsuredPerson("Asfalismenos", "6", "Rethymno", 66666666);
		myDataSystem.addInsuredPerson("Asfalismenos", "7", "Rethymno", 77777777);
		myDataSystem.addInsuredPerson("Asfalismenos", "8", "Rethymno", 88888888);
		myDataSystem.addInsuredPerson("Asfalismenos", "9", "Rethymno", 99999999);
		
		myDataSystem.addVaccinationCenter("CH-22", "Chania", 22222);
		myDataSystem.addVaccinationCenter("RTH-33", "Rethymno", 33333);
		
		myDataSystem.addDoctor("Doctor", "1", 111111, 22222);
		myDataSystem.addDoctor("Doctor", "2", 222222, 22222);
		myDataSystem.addDoctor("Doctor", "3", 333333, 22222);
		myDataSystem.addDoctor("Doctor", "4", 444444, 33333);
		myDataSystem.addDoctor("Doctor", "5", 555555, 33333);
		
		myDataSystem.arrangeRantevu(myDataSystem.findVaccCenter(22222), myDataSystem.findVaccCenter(22222).getDocCatalogue()[0], myDataSystem.findInsuredPerson(11111111), 1, 1, 20);
		myDataSystem.arrangeRantevu(myDataSystem.findVaccCenter(22222), myDataSystem.findVaccCenter(22222).getDocCatalogue()[0], myDataSystem.findInsuredPerson(22222222), 1, 4, 21);
		myDataSystem.arrangeRantevu(myDataSystem.findVaccCenter(22222), myDataSystem.findVaccCenter(22222).getDocCatalogue()[1], myDataSystem.findInsuredPerson(33333333), 1, 4, 22);
		myDataSystem.arrangeRantevu(myDataSystem.findVaccCenter(22222), myDataSystem.findVaccCenter(22222).getDocCatalogue()[2], myDataSystem.findInsuredPerson(44444444), 1, 4, 23);
		myDataSystem.arrangeRantevu(myDataSystem.findVaccCenter(22222), myDataSystem.findVaccCenter(22222).getDocCatalogue()[0], myDataSystem.findInsuredPerson(55555555), 2, 2, 24);
		
		myDataSystem.arrangeRantevu(myDataSystem.findVaccCenter(33333), myDataSystem.findVaccCenter(33333).getDocCatalogue()[0], myDataSystem.findInsuredPerson(66666666), 2, 2, 30);
		myDataSystem.arrangeRantevu(myDataSystem.findVaccCenter(33333), myDataSystem.findVaccCenter(33333).getDocCatalogue()[1], myDataSystem.findInsuredPerson(77777777), 2, 2, 31);
		myDataSystem.arrangeRantevu(myDataSystem.findVaccCenter(33333), myDataSystem.findVaccCenter(33333).getDocCatalogue()[1], myDataSystem.findInsuredPerson(88888888), 2, 3, 32);
		myDataSystem.arrangeRantevu(myDataSystem.findVaccCenter(33333), myDataSystem.findVaccCenter(33333).getDocCatalogue()[0], myDataSystem.findInsuredPerson(99999999), 3, 2, 33);
		
		
		
															/***** MENU *****/
		
		while(choice != 9) {
			printMenu();
			choice = userInput.nextInt();
			

			switch(choice) {
			case 0:
				continue;
			case 1:
				//ask user for person's data
				System.out.print("First name: ");
				String fName = userInput.next();
				
				System.out.print("Last name: ");
				String lName = userInput.next();
				
				System.out.print("City: ");
				String city = userInput.next();
				
				System.out.print("Social Security Number: ");
				Long ssn = userInput.nextLong();
				
				//calling a method
				myDataSystem.addInsuredPerson(fName, lName, city, ssn);
				
				break;
			case 2:
				//ask user for vaccination center's data
				System.out.print("Vaccination Center Name: ");
				String vaccCenterName = userInput.next();
				
				System.out.print("Vaccination Center City: ");
				String vaccCenterCity = userInput.next();
				
				System.out.print("Vaccination Center Code: ");
				int vcc = userInput.nextInt();
				
				//calling a method
				myDataSystem.addVaccinationCenter(vaccCenterName, vaccCenterCity, vcc);
				
				break;
			case 3:
				//ask user for doctor's data
				System.out.print("Doctor's first name: ");
				String docFName = userInput.next();
				
				System.out.print("Doctor's last name: ");
				String docLName = userInput.next();
				
				System.out.print("Doctor's registration number: ");
				int regNum = userInput.nextInt();
				
				System.out.println("Available Vaccination Centers:");
				myDataSystem.printVaccCenters();
				System.out.print("\nVaccination Center Code: ");
				vcc = userInput.nextInt();
				
				//calling a method
				myDataSystem.addDoctor(docFName, docLName, regNum, vcc);
				
				break;
			case 4:
				LocalDate today = LocalDate.now();
				
				//ask user for person's social security number
				System.out.print("Person's Social Security Number: ");
				ssn = userInput.nextLong();
				
				//person and vaccination center references
				InsuredPerson personReference = myDataSystem.findInsuredPerson(ssn);
				VaccCenter vaccCenterReference = myDataSystem.findVaccCenter(personReference.getCity());
				
				//print available rantevus
				System.out.println("\nAvailable Rantevus:\n");
				for (int i = 0; i < myDataSystem.getVaccinationTimePeriod(); i++) {
					System.out.println("Day " + (i+1) + " (" + today.plusDays(i+1) + ")" + ":");
					for (int j = 0; j < 4; j++) {
						System.out.print(myDataSystem.printRantevu(i, j));
						for (int k = 0; k < vaccCenterReference.getDocNum(); k++) {
							if (vaccCenterReference.getCatalogue()[i][j][k] == null) {
								break;
							}
							if (k == (vaccCenterReference.getDocNum() - 1)) {
								System.out.print(" *NOT AVAILABLE*");
							}
						}
						System.out.println();
					}
					System.out.println();
				}

				
				
				//letting user select their rantevu
				int day = 0;
				int slot = 0;
				boolean choiceCheck = true;
				while(choiceCheck) {
					System.out.print("Select day (1 - 7): ");
					day = userInput.nextInt();
					System.out.print("Select slot (1 - 4): ");
					slot = userInput.nextInt();
					
					for (int i = 0; i < vaccCenterReference.getDocNum(); i++) {
						if (vaccCenterReference.getCatalogue()[day-1][slot-1][i] == null) {	
							choiceCheck = false;
							break;
						}
					}
					if (choiceCheck) {
						System.out.println("\nNo Rantevu available then. Try again.\n");
					}
				}
				
				//calling a method to find the right doctor
				Doctor doctorReference = myDataSystem.findDoctor(vaccCenterReference, day, slot);
				
				//calling a method to arrange the rantevu
				Rantevu rantevuReference = myDataSystem.arrangeRantevu(vaccCenterReference, doctorReference, personReference, day, slot);
				
				//printing information for the rantevu using a rantevu class method
				System.out.println("\nInformation for your Rantevu:");
				rantevuReference.printRantevu();
				
				break;
			case 5:
				//print a menu
				int option = 0;
				while (option != 4) {
					printMenuCaseFive();
					option = userInput.nextInt();
					
					switch(option) {
					case 0:
						continue;
					case 1:
						System.out.print("Enter the Social Security Number: ");
						ssn = userInput.nextLong();
						myDataSystem.findRantevuSsn(ssn);
						break;
					case 2:
						System.out.print("Enter Vaccination Center Code: ");
						vcc = userInput.nextInt();
						myDataSystem.findRantevuVcc(vcc);
						break;
					case 3:
						System.out.print("Doctor's registration number: ");
						regNum = userInput.nextInt();
						myDataSystem.findRantevuDoctorRegNum(regNum);
						break;
					case 4:
						break;
					default:
						System.out.println("Select a number 1-4.");
						continue;		
					}
				}
				
				break;
			case 6:
				myDataSystem.printInsuredPersons();
				
				break;
			case 7:
				myDataSystem.printVaccCenters();
				
				break;
			case 8:
				for (int i = 0; i < myDataSystem.getVaccCentNum(); i++) {
					myDataSystem.getVaccCentCatalogue()[i].printDoctors();
				}
				break;
			case 9:
				System.out.println("Goodbye!");
				return;
			default:
				System.out.println("Select a number 1-9.");
				continue;
			}
		}
		userInput.close();
	}

	
	public static void printMenu() {
		System.out.println("Choose an option 1-9 by entering the corresponding number:");
		System.out.println("\n\t1. Add an insured person.");
		System.out.println("\n\t2. Add a Vaccination Center.");
		System.out.println("\n\t3. Add a Doctor.");
		System.out.println("\n\t4. Arrange new Rantevu.");
		System.out.println("\n\t5. Find and print a specific Rantevu.");
		System.out.println("\n\t6. Print every insured person.");
		System.out.println("\n\t7. Print every Vaccination Center.");
		System.out.println("\n\t8. Print every Doctor.");
		System.out.println("\n\t9. Exit.");
		System.out.print("\nOption: ");
	}
	
	public static void printMenuCaseFive() {
		System.out.println("How do you want to search the Rantevu?");
		System.out.println("\t1. With person's Social Security Number.");
		System.out.println("\t2. With Vaccination Center Code.");
		System.out.println("\t3. With Doctor's registration number.");
		System.out.println("\t4. Exit this option.");
		System.out.print("Option: ");
	}
}
