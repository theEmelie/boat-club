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
public class BoatClubViewEnglish implements BoatClubView {
  private String userInput = "";
  
  /**
  * Printing the main menu with all options what a user can do.
  */
  public void mainMenu() {
    System.out.println("BOATCLUB OPERATIONS");
    System.out.println("---------------------");
    System.out.println("1. Add Member");
    System.out.println("2. Delete Member");
    System.out.println("3. Display Member");
    System.out.println("4. Display all Members (Verbose)");
    System.out.println("5. Display all Members (Compact)");
    System.out.println("6. Edit Member");
    System.out.println("7. Register a Boat");
    System.out.println("8. Delete Boat");
    System.out.println("9. Edit Boat");
    System.out.println("0. Add default data");
    System.out.println("99. Exit");
    System.out.println("---------------------");
  }

  /**
  * Displaying all members that has been added.
  *
  * @param members a list of all members
  */
  public void displayAllMembers(ArrayList<Member> members) {

    // Sort by member id
    Collections.sort(members, (m1, m2) -> {
      return m1.getMemberId().compareTo(m2.getMemberId());
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
    System.out.println("\nINFORMATION ABOUT MEMBER");
    System.out.println("---------------------");
    System.out.println("Personal Number: " + member.getPersonalNumber());
    System.out.println("Member Id: " + member.getMemberId());
    System.out.println("Firstname: " + member.getFirstName());
    System.out.println("Lastname: " + member.getLastName());
    
    for (model.Boat b : member.getRegisteredBoats()) {
      System.out.println("Boat: " + b.getBoatName() + ", " + b.getBoatType() + ", " + b.getSize() + "m");
    }

    System.out.println("---------------------");
  }

  /**
  * Displaying all members that has been added.
  *
  * @param members a list of all members
  */
  public void displayAllMembersCompact(ArrayList<Member> members) {
    // Sort by member id
    Collections.sort(members, (m1, m2) -> {
      return m1.getMemberId().compareTo(m2.getMemberId());
    });

    System.out.println("\nINFORMATION ABOUT MEMBERS");
    for (int i = 0; i < members.size(); i++) {
      System.out.println("---------------------");
      System.out.println("Member Id: " + members.get(i).getMemberId());
      System.out.println("Name: " + members.get(i).getFirstName() + " " + members.get(i).getLastName());
      System.out.println("Number of boats: " + members.get(i).getRegisteredBoats().size());

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
    System.out.println("\nEnter Personal Number: ");
    return getInput();
  }

  public String getMemberId() {
    System.out.println("\nEnter Member Id: ");
    return getInput();
  }

  public String getFirstName() {
    System.out.println("\nEnter firstname: ");
    return getInput();
  }

  public String getLastName() {
    System.out.println("\nEnter lastname: ");
    return getInput();
  }

  public String getNewBoatName() {
    System.out.println("\nEnter new boat name: ");
    return getInput();
  }

  public String getBoatName() {
    System.out.println("\nEnter boat name: ");
    return getInput();
  }

  public String getOldBoatName() {
    System.out.println("\nEnter current boat name: ");
    return getInput();
  }

  public String getBoatSize() {
    System.out.println("\nEnter size of boat in meters: ");
    return getInput();
  }

  public String getBoatType() {
    System.out.println("\nEnter type of boat (Sailboat, Motorboat, Kayak, Canoe or Other): ");
    return getInput();
  }

  public void displayTextDeletedMember(String memberId) {
    System.out.println("\nDeleted Member with Member Id: " + memberId + "\n");
  }

  public void displayTextAddedMember(String firstName, String lastName) {
    System.out.println("\nAdded: " + firstName + " " + lastName + "\n");
  }

  public void displayTextChangingMember(String firstName, String lastName) {
    System.out.println("\nChanging Member: " + firstName + " " + lastName + "\n");
  }

  public void displayTextChangedMember(String firstName, String lastName, int personalNumber) {
    System.out.println("\nChanged Members information to: " 
        + firstName + " " + lastName + ", " + personalNumber + "\n");
  }

  public void displayTextRegisteredBoat(String boatName, BoatType boatType, int boatSize) {
    System.out.println("\nRegistered Boat: " + boatName + " " + boatType + ", " + boatSize + "\n");
  }

  public void displayTextDeletedBoat(String boatName, String memberId) {
    System.out.println("\nDeleted boat: " + boatName + " for Member: " + memberId + "\n");
  }

  public void displayTextUpdatedBoat(String boatName, BoatType boatType, int boatSize) {
    System.out.println("\nUpdated Boat: " + boatName + " " + boatType + ", " + boatSize + "\n");
  }

  /*
  public void displayText(String text) {
    System.out.println("\n" + text + "\n");
  }
  */

  public void errorMessageInvalidInput() {
    System.out.println("\nInvalid Input");
    System.out.println("---------------------");
  }

  public void errorMessageNoUniqueIds() {
    System.out.println("\nNo unique id's available. Too many Members.");
    System.out.println("---------------------");
  }

  public void errorMessageInvalidBoatType(String boatTypeInput) {
    System.out.println("\n" + boatTypeInput + " is an invalid Boat Type.");
    System.out.println("---------------------");
  }

  public void errorMessage(String message) {
    System.out.println("\n" + message);
    System.out.println("---------------------");
  }

  public void duplicateBoatName(String boatName) {
    System.out.println("\n" + boatName + " already exists.");
    System.out.println("---------------------");
  }

  /**
  * Type in 1 to Add Member.
  *
  * @return the user input
  */
  public boolean wantsToAddMember() {
    boolean returnValue = userInput.equals("1");
    return returnValue;
  }

  /**
  * Type in 2 to Delete Member.
  *
  * @return the user input
  */
  public boolean wantsToDeleteMember() {
    boolean returnValue = userInput.equals("2");
    return returnValue;
  }
  
  /**
  * Type in 3 to Display Member.
  *
  * @return the user input
  */
  public boolean wantsToDisplayMember() {
    boolean returnValue = userInput.equals("3");
    return returnValue;
  }

  /**
  * Type in 4 to Display All Members in a full list.
  *
  * @return the user input
  */
  public boolean wantsToDisplayAllMembers() {
    boolean returnValue = userInput.equals("4");
    return returnValue;
  }

  /**
  * Type in 5 to Display All Members in a compact list.
  *
  * @return the user input
  */
  public boolean wantsToDisplayAllMembersCompact() {
    boolean returnValue = userInput.equals("5");
    return returnValue;
  }
  
  /**
  * Type in 6 to Edit Member.
  *
  * @return the user input
  */
  public boolean wantsToEditMember() {
    boolean returnValue = userInput.equals("6");
    return returnValue;
  }

  /**
  * Type in 7 to Register Boat.
  *
  * @return the user input
  */
  public boolean wantsToRegisterBoat() {
    boolean returnValue = userInput.equals("7");
    return returnValue;
  }
  
  /**
  * Type in 8 to Delete Boat.
  *
  * @return the user input
  */
  public boolean wantsToDeleteBoat() {
    boolean returnValue = userInput.equals("8");
    return returnValue;
  }
  
  /**
  * Type in 9 to Edit Boat.
  *
  * @return the user input
  */
  public boolean wantsToEditBoat() {
    boolean returnValue = userInput.equals("9");
    return returnValue;
  }
    
  /**
  * Type in 0 to Add Default Data.
  *
  * @return the user input
  */
  public boolean wantsToAddDefaultData() {
    boolean returnValue = userInput.equals("0");
    return returnValue;
  }

  /**
  * Type in 99 to Quit.
  *
  * @return the user input
  */
  public boolean wantsToQuit() {
    boolean returnValue = userInput.equals("99");
    return returnValue;
  }
}
