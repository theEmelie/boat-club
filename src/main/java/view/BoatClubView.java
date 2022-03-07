package view;

import java.util.ArrayList;
import model.BoatType;
import model.Member;

/**
* This class is the main view for the application.
*/
public interface BoatClubView {
  
  /**
  * Printing the main menu with all options what a user can do.
  */
  void mainMenu();

  /**
  * Displaying all members that has been added.
  *
  * @param members a list of all members
  */
  void displayAllMembers(ArrayList<Member> members);

  /**
  * Displaying one member, which member is depending on which personal number that has been entered.
  *
  * @param member displaying the picked member
  */
  void displayMember(Member member);

  /**
  * Displaying all members that has been added.
  *
  * @param members a list of all members
  */
  void displayAllMembersCompact(ArrayList<Member> members);

  /**
  * Collect user input.
  *
  * @return the user input
  */
  String getInput();

  String getPersonalNumber();

  String getMemberId();

  String getFirstName();

  String getLastName();

  String getNewBoatName();

  String getBoatName();

  String getOldBoatName();

  String getBoatSize();

  String getBoatType();

  void displayTextDeletedMember(String memberId);

  void displayTextAddedMember(String firstName, String lastName);

  void displayTextChangingMember(String firstName, String lastName);

  void displayTextChangedMember(String firstName, String lastName, int personalNumber);

  void displayTextRegisteredBoat(String boatName, BoatType boatType, int boatSize);

  void displayTextDeletedBoat(String boatName, String memberId);

  void displayTextUpdatedBoat(String boatName, BoatType boatType, int boatSize);

  void errorMessageInvalidInput();

  void errorMessageNoUniqueIds();

  void errorMessageInvalidBoatType(String boatTypeInput);

  void errorMessage(String message);

  void duplicateBoatName(String boatName);

  /**
  * Type in 1 to Add Member.
  *
  * @return the user input
  */
  boolean wantsToAddMember();

  /**
  * Type in 2 to Delete Member.
  *
  * @return the user input
  */
  boolean wantsToDeleteMember();
  
  /**
  * Type in 3 to Display Member.
  *
  * @return the user input
  */
  boolean wantsToDisplayMember();

  /**
  * Type in 4 to Display All Members in a full list.
  *
  * @return the user input
  */
  boolean wantsToDisplayAllMembers();

  /**
  * Type in 5 to Display All Members in a compact list.
  *
  * @return the user input
  */
  boolean wantsToDisplayAllMembersCompact();
  
  /**
  * Type in 6 to Edit Member.
  *
  * @return the user input
  */
  boolean wantsToEditMember();

  /**
  * Type in 7 to Register Boat.
  *
  * @return the user input
  */
  boolean wantsToRegisterBoat();
  
  /**
  * Type in 8 to Delete Boat.
  *
  * @return the user input
  */
  boolean wantsToDeleteBoat();
  
  /**
  * Type in 9 to Edit Boat.
  *
  * @return the user input
  */
  boolean wantsToEditBoat();
    
  /**
  * Type in 0 to Add Default Data.
  *
  * @return the user input
  */
  boolean wantsToAddDefaultData();

  /**
  * Type in 99 to Quit.
  *
  * @return the user input
  */
  boolean wantsToQuit();
}
