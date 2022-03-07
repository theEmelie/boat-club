package controller;

import java.util.ArrayList;
import model.BoatClub;
import model.BoatType;
import model.Member;
import view.BoatClubView;
import view.BoatClubViewEnglish;
import view.BoatClubViewSwedish;

/**
* The main controller for the application, the middle man,
* listens to the views events and execute them by calling the methods in the model.
*/
public class BoatClubController {
  private BoatClub boatClub;
  private BoatClubView view;

  /** Constructor. */
  public BoatClubController() {
    boatClub = new BoatClub();
    //view = new BoatClubViewEnglish(); 
    view = new BoatClubViewSwedish();
  }
  
  /**
  * This is a method that decides which method to call 
  * depending on the event that has been triggered from the view.
  */
  public void manageBoatClub() {
    while (view.wantsToQuit() == false) {
      view.mainMenu();
      view.getInput();
      
      if (view.wantsToAddMember()) {
        addMember();
      } else if (view.wantsToDeleteMember()) {
        deleteMember();
      } else if (view.wantsToDisplayAllMembers()) {
        ArrayList<Member> members = boatClub.getAllMembers();
        view.displayAllMembers(members);
      } else if (view.wantsToDisplayAllMembersCompact()) {
        ArrayList<Member> members = boatClub.getAllMembers();
        view.displayAllMembersCompact(members);
      } else if (view.wantsToDisplayMember()) {
        displayMember(); 
      } else if (view.wantsToEditMember()) {
        editMember();
      } else if (view.wantsToRegisterBoat()) {
        registerBoat();
      } else if (view.wantsToDeleteBoat()) {
        deleteBoat();
      } else if (view.wantsToEditBoat()) {
        editBoat();
      } else if (view.wantsToAddDefaultData()) {
        try {
          boatClub.addDefaultData();
        } catch (Exception e) {
          view.errorMessage(e.getMessage());
        }
      }
    }
  }

  /** Method to delete a member, by calling the BoatClub model and BoatClub view. */
  public void deleteMember() {
    String memberId;

    memberId = view.getMemberId();
    
    try {
      boatClub.deleteMember(memberId);
      view.displayTextDeletedMember(memberId);
    } catch (Exception e) {
      view.errorMessage(e.getMessage());
    }
  }
  
  /** Method to add a member, by calling the BoatClub model and BoatClub view. */
  public void addMember() {
    String firstName;
    String lastName;
    int personalNumber;

    try {
      personalNumber = Integer.parseInt(view.getPersonalNumber());
      firstName = view.getFirstName();   
      lastName = view.getLastName();

      boatClub.addMember(firstName, lastName, personalNumber);
      view.displayTextAddedMember(firstName, lastName);
    } catch (UnsupportedOperationException e) {
      view.errorMessageNoUniqueIds();
    } catch (Exception e) {
      view.errorMessageInvalidInput();
    }
  }

  /** Method to display a member, by calling the BoatClub model and BoatClub view. */
  public void displayMember() {
    String memberId;
    Member memberInfo;

    memberId = view.getMemberId();
    
    try {
      memberInfo = boatClub.getMember(memberId);
      view.displayMember(memberInfo);
    } catch (Exception e) {
      view.errorMessage(e.getMessage());
    }
  }
  
  /** Method to edit a member, by calling the BoatClub model and BoatClub view. */
  public void editMember() {
    String firstName;
    String lastName;
    int personalNumber;
    String memberId;
    Member memberInfo;

    try {
      memberId = view.getMemberId();
      memberInfo = boatClub.getMember(memberId);
      view.displayTextChangingMember(memberInfo.getFirstName(), memberInfo.getLastName());
      
      personalNumber = Integer.parseInt(view.getPersonalNumber());
      firstName = view.getFirstName();
      lastName = view.getLastName();
      
      boatClub.editMember(firstName, lastName, personalNumber, memberId);
      view.displayTextChangedMember(firstName, lastName, personalNumber);
    } catch (Exception e) {
      view.errorMessage(e.getMessage());
    }
  }

  /** Method to register a boat to a specific member, by calling the BoatClub model and BoatClub view. */
  public void registerBoat() {
    String memberId;
    String boatName;
    int boatSize;
    BoatType boatType;
    String boatTypeInput = "";

    try {
      memberId = view.getMemberId();

      // Loop until non-duplicate boat name is entered
      while (true) {
        boatName = view.getBoatName();
        if (boatClub.boatNameExists(boatName) == false) {
          break;
        } else {
          view.duplicateBoatName(boatName);
        }
      }

      // Loop until valid boat type is entered
      while (true) {
        try {
          boatTypeInput = view.getBoatType();
          boatType = BoatType.valueOf(boatTypeInput);
          break;
        } catch (Exception e) {
          view.errorMessageInvalidBoatType(boatTypeInput);
        }
      }

      boatSize = Integer.parseInt(view.getBoatSize());
      
      boatClub.addBoat(boatType, boatSize, boatName, memberId);
      view.displayTextRegisteredBoat(boatName, boatType, boatSize);
    } catch (Exception e) {
      view.errorMessage(e.getMessage());
    }
  }

  /** Method to delete a boat from a specific member, by calling the BoatClub model and BoatClub view. */
  public void deleteBoat() {
    String memberId;
    String boatName;
    
    try {
      memberId = view.getMemberId();
      boatName = view.getBoatName();

      boatClub.deleteBoat(boatName, memberId);
      view.displayTextDeletedBoat(boatName, memberId);
    } catch (Exception e) {
      view.errorMessage(e.getMessage());
    }
  }

  /** Method to edit information about a boat, by calling the BoatClub model and BoatClub view. */
  public void editBoat() {
    String memberId;
    String boatName;
    String oldBoatName;
    int boatSize;
    BoatType boatType;
    String boatTypeInput = "";
    
    try {
      memberId = view.getMemberId();
      oldBoatName = view.getOldBoatName();

      // Loop until non-duplicate boat name is entered
      while (true) {
        boatName = view.getNewBoatName();
        if (boatClub.boatNameExists(boatName) == false) {
          break;
        } else {
          view.duplicateBoatName(boatName);
        }
      }

      // Loop until valid boat type is entered
      while (true) {
        try {
          boatTypeInput = view.getBoatType();
          boatType = BoatType.valueOf(boatTypeInput);
          break;
        } catch (Exception e) {
          view.errorMessageInvalidBoatType(boatTypeInput);
        }
      }

      boatSize = Integer.parseInt(view.getBoatSize());
      
      boatClub.editBoat(boatType, boatSize, boatName, memberId, oldBoatName);
      view.displayTextUpdatedBoat(boatName, boatType, boatSize);
    } catch (Exception e) {
      view.errorMessage(e.getMessage());
    }
  }

}
