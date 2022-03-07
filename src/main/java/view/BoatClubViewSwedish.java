package view;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import model.BoatType;
import model.Member;

/**
* This class is the main view for the application.
*/
public class BoatClubViewSwedish implements BoatClubView {
  private String userInput = "";
  
  /**
  * Printing the main menu with all options what a user can do.
  */
  public void mainMenu() {
    System.out.println("BÅTKLUBB ALTERNATIV");
    System.out.println("---------------------");
    System.out.println("a. Lägg till en Medlem");
    System.out.println("b. Ta bort en Medlem");
    System.out.println("c. Visa en Medlem");
    System.out.println("d. Visa alla Medlemmar (Fullständig)");
    System.out.println("e. Visa alla Medlemmar (Kompakt)");
    System.out.println("f. Redigera en Medlem");
    System.out.println("g. Registrera en Båt");
    System.out.println("h. Ta bort en Båt");
    System.out.println("i. Redigera en Båt");
    System.out.println("z. Lägg till standard data");
    System.out.println("q. Avsluta");
    System.out.println("---------------------");
  }

  /**
  * Displaying all members that has been added.
  *
  * @param members a list of all members
  */
  public void displayAllMembers(ArrayList<Member> members) {

    // Sort by firstname, lastname
    Collections.sort(members, (m1, m2) -> {
      return (m1.getFirstName() + m1.getLastName()).compareTo((m2.getFirstName() + m2.getLastName()));
    });

    for (int i = 0; i < members.size(); i++) {
      displayMember(members.get(i));
    }
  }

  /**
  * Displaying one member, which member is depending on which personal number that has been entered.
  *
  * @param member displaying the picked member
  */
  public void displayMember(Member member) {
    System.out.println("\nINFORMATION OM MEDLEM");
    System.out.println("---------------------");
    System.out.println("Personnummer: " + member.getPersonalNumber());
    System.out.println("Medlems Id: " + member.getMemberId());
    System.out.println("Förnamn: " + member.getFirstName());
    System.out.println("Efternamn: " + member.getLastName());
    
    for (model.Boat b : member.getRegisteredBoats()) {
      System.out.println("Båt: " + b.getBoatName() + ", " + b.getBoatType() + ", " + b.getSize() + "m");
    }

    System.out.println("---------------------");
  }

  /**
  * Displaying all members that has been added.
  *
  * @param members a list of all members
  */
  public void displayAllMembersCompact(ArrayList<Member> members) {
    // Sort by firstname, lastname
    Collections.sort(members, (m1, m2) -> {
      return (m1.getFirstName() + m1.getLastName()).compareTo((m2.getFirstName() + m2.getLastName()));
    });

    System.out.println("\nINFORMATION OM MEDLEMMAR");
    for (int i = 0; i < members.size(); i++) {
      System.out.println("---------------------");
      System.out.println("Medlems Id: " + members.get(i).getMemberId());
      System.out.println("Namn: " + members.get(i).getFirstName() + " " + members.get(i).getLastName());
      System.out.println("Antal Båtar: " + members.get(i).getRegisteredBoats().size());

    }
    System.out.println("---------------------");
  }

  /**
  * Collect user input.
  *
  * @return the user input
  */
  public String getInput() {
    // Scanner is not being closed as it crashes the application even when placed at various location in the code.
    Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);
    userInput = sc.nextLine();
    return userInput;
  }

  public String getPersonalNumber() {
    System.out.println("\nAnge Personnummer: ");
    return getInput();
  }

  public String getMemberId() {
    System.out.println("\nAnge Medlems Id: ");
    return getInput();
  }

  public String getFirstName() {
    System.out.println("\nAnge Förnamn: ");
    return getInput();
  }

  public String getLastName() {
    System.out.println("\nAnge Efternamn: ");
    return getInput();
  }

  public String getNewBoatName() {
    System.out.println("\nAnge nytt båt namn: ");
    return getInput();
  }

  public String getBoatName() {
    System.out.println("\nAnge båt namn: ");
    return getInput();
  }

  public String getOldBoatName() {
    System.out.println("\nAnge nuvarande båt namn: ");
    return getInput();
  }

  public String getBoatSize() {
    System.out.println("\nAnge storlek på båt i meter: ");
    return getInput();
  }

  /**
   * Print the boat types a user can choose from.
   */
  public String getBoatType() {
    System.out.println("\nAnge typ av båt, Sailboat (Segelbåt), Motorboat (Motorbåt)," 
        + " Kayak (Kajak), Canoe (Kanot) eller Other (Annat): ");
    return getInput();
  }

  public void displayTextDeletedMember(String memberId) {
    System.out.println("\nTog bort Medlem med Medlems Id: " + memberId + "\n");
  }

  public void displayTextAddedMember(String firstName, String lastName) {
    System.out.println("\nLade till: " + firstName + " " + lastName + "\n");
  }

  public void displayTextChangingMember(String firstName, String lastName) {
    System.out.println("\nÄndrar medlem: " + firstName + " " + lastName + "\n");
  }

  public void displayTextChangedMember(String firstName, String lastName, int personalNumber) {
    System.out.println("\nÄndrat medlems information till: " 
        + firstName + " " + lastName + ", " + personalNumber + "\n");
  }

  public void displayTextRegisteredBoat(String boatName, BoatType boatType, int boatSize) {
    System.out.println("\nRegistrerad Båt: " + boatName + " " + boatType + ", " + boatSize + "\n");
  }

  public void displayTextDeletedBoat(String boatName, String memberId) {
    System.out.println("\nBorttagen Båt: " + boatName + " for Member: " + memberId + "\n");
  }

  public void displayTextUpdatedBoat(String boatName, BoatType boatType, int boatSize) {
    System.out.println("\nUppdaterad Båt: " + boatName + " " + boatType + ", " + boatSize + "\n");
  }

  /*
  public void displayText(String text) {
    System.out.println("\n" + text + "\n");
  }
  */

  public void errorMessageInvalidInput() {
    System.out.println("\nOgiltig inmatning");
    System.out.println("---------------------");
  }

  public void errorMessageNoUniqueIds() {
    System.out.println("\nInga unika id:en tillgängliga. För många medlemmar.");
    System.out.println("---------------------");
  }

  public void errorMessageInvalidBoatType(String boatTypeInput) {
    System.out.println("\n" + boatTypeInput + " är en ogitlig båt typ.");
    System.out.println("---------------------");
  }

  public void errorMessage(String message) {
    System.out.println("\n" + message);
    System.out.println("---------------------");
  }

  public void duplicateBoatName(String boatName) {
    System.out.println("\n" + boatName + " finns redan.");
    System.out.println("---------------------");
  }

  /**
  * Type in 1 to Add Member.
  *
  * @return the user input
  */
  public boolean wantsToAddMember() {
    boolean returnValue = userInput.equals("a");
    return returnValue;
  }

  /**
  * Type in 2 to Delete Member.
  *
  * @return the user input
  */
  public boolean wantsToDeleteMember() {
    boolean returnValue = userInput.equals("b");
    return returnValue;
  }
  
  /**
  * Type in 3 to Display Member.
  *
  * @return the user input
  */
  public boolean wantsToDisplayMember() {
    boolean returnValue = userInput.equals("c");
    return returnValue;
  }

  /**
  * Type in 4 to Display All Members in a full list.
  *
  * @return the user input
  */
  public boolean wantsToDisplayAllMembers() {
    boolean returnValue = userInput.equals("d");
    return returnValue;
  }

  /**
  * Type in 5 to Display All Members in a compact list.
  *
  * @return the user input
  */
  public boolean wantsToDisplayAllMembersCompact() {
    boolean returnValue = userInput.equals("e");
    return returnValue;
  }
  
  /**
  * Type in 6 to Edit Member.
  *
  * @return the user input
  */
  public boolean wantsToEditMember() {
    boolean returnValue = userInput.equals("f");
    return returnValue;
  }

  /**
  * Type in 7 to Register Boat.
  *
  * @return the user input
  */
  public boolean wantsToRegisterBoat() {
    boolean returnValue = userInput.equals("g");
    return returnValue;
  }
  
  /**
  * Type in 8 to Delete Boat.
  *
  * @return the user input
  */
  public boolean wantsToDeleteBoat() {
    boolean returnValue = userInput.equals("h");
    return returnValue;
  }
  
  /**
  * Type in 9 to Edit Boat.
  *
  * @return the user input
  */
  public boolean wantsToEditBoat() {
    boolean returnValue = userInput.equals("i");
    return returnValue;
  }
    
  /**
  * Type in 0 to Add Default Data.
  *
  * @return the user input
  */
  public boolean wantsToAddDefaultData() {
    boolean returnValue = userInput.equals("z");
    return returnValue;
  }

  /**
  * Type in 99 to Quit.
  *
  * @return the user input
  */
  public boolean wantsToQuit() {
    boolean returnValue = userInput.equals("q");
    return returnValue;
  }
}
