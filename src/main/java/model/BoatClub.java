package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

/**
* This class holds the methods for 
* adding, deleting and display members and boats.
*/
public class BoatClub {
  private ArrayList<Member> members;
  private AtomicInteger atomicInteger = new AtomicInteger(0);
  private HashSet<String> existingMemberIdSet = new HashSet<String>();
  private int latestMemberNumber;

  public BoatClub() {
    members = new ArrayList<Member>();
  }

  /**
  * Adding a member.
  *
  * @param firstName members firstname
  * @param lastName members lastname
  * @param personalNumber personal number after edit is made
  * @return true when added member
  */
  public boolean addMember(String firstName, String lastName, int personalNumber) {
    String memberId;
    boolean invalidMemberId = true;
    int startingAtomicInteger = atomicInteger.get();
    // Member Id is created by having the first letter in the 
    // member's lastname and then a number for the last registered member, add up to 4 leading 0's.
    // i.e. lastname Ajax as the first registered user will create A00001
    while (invalidMemberId) {
      latestMemberNumber = atomicInteger.incrementAndGet();
      memberId = lastName.substring(0, 1) + String.format("%05d", latestMemberNumber % 10000);
     
      if (existingMemberIdSet.contains(memberId) == false) {
        // Member id is unique.
        Member m = new Member(firstName, lastName, personalNumber, memberId);
        members.add(m);
        existingMemberIdSet.add(memberId);
        invalidMemberId = false;
      } else {
        if (startingAtomicInteger % 10000 == atomicInteger.get() % 10000) {
          // If there is no more unique id's left, throw exception.
          // A boatclub should not require more than 10000 members, 
          // possible to write different algorithms if needed here instead.
          invalidMemberId = false;
          throw new UnsupportedOperationException("Too many members: Cannot generate unique member id");
        }
      }
    }
    return true;
  }

  /**
  * Deleting a member with the help of the personal number.
  *
  * @param memberId id of member
  */
  public void deleteMember(String memberId) throws IllegalArgumentException {
    for (int i = 0; i < members.size(); i++) {
      if (members.get(i).getMemberId().equals(memberId)) {
        existingMemberIdSet.remove(members.get(i).getMemberId());
        members.remove(i);
        return;
      }
    }
    throw new IllegalArgumentException("Member not found");
  }

  /**
  * Returning a copy of the ArrayList of all members.
  *
  * @return a copied list of all members
  */
  public ArrayList<Member> getAllMembers() {
    return new ArrayList<Member>(members);
  }

  /**
  * Get information of one member.
  *
  * @param memberId id of member
  * @return the picked member
  */
  public Member getMember(String memberId) throws IllegalArgumentException {
    for (int i = 0; i < members.size(); i++) {
      if (members.get(i).getMemberId().equals(memberId)) {
        return members.get(i);
      }
    }
    throw new IllegalArgumentException("Member not found");
  }

  /**
  * Edit a members information.
  *
  * @param firstName members firstname
  * @param lastName members lastname
  * @param newPersonalNumber personal number after edit is made
  * @param memberId id of member
  */
  public void editMember(String firstName, String lastName, int newPersonalNumber, String memberId) 
      throws IllegalArgumentException {
    for (int i = 0; i < members.size(); i++) {
      Member member = members.get(i);
      if (member.getMemberId().equals(memberId)) {
        member.setFirstName(firstName);
        member.setLastName(lastName);
        member.setPersonalNumber(newPersonalNumber);

        return;
      }
    }
    throw new IllegalArgumentException("Member not found");
  }

  /**
  * Register a boat to member.
  *
  * @param type the type of boat
  * @param size the boat size
  * @param boatName name of boat
  * @param memberId id of member
  */
  public void addBoat(BoatType type, int size, String boatName, String memberId) throws IllegalArgumentException {
    Member member;
    
    if (boatNameExists(boatName) == true) {
      throw new IllegalArgumentException("Duplicate boat name");
    }

    for (int i = 0; i < members.size(); i++) {
      member = members.get(i);
      
      if (member.getMemberId().equals(memberId)) {
        Boat b = new Boat(type, size, boatName);
        member.addBoat(b);

        return;
      }
    }
    
    throw new IllegalArgumentException("Member not found");
  }

  /**
   * Check if boat name exists.
   *
   * @param boatName name of boat
   * @return true or false if boat name exists
   */
  public boolean boatNameExists(String boatName) {
    Member member;
    boolean foundBoatName = false;

    for (int i = 0; i < members.size(); i++) {
      member = members.get(i);
      
      if (member.boatNameExists(boatName)) {
        foundBoatName = true;

        break;
      }
    }
    return foundBoatName;
  }

  /**
  * Delete a boat.
  *
  * @param boatName name of boat
  * @param memberId id of member
  */
  public void deleteBoat(String boatName, String memberId) throws IllegalArgumentException {
    Member member;
    
    for (int i = 0; i < members.size(); i++) {
      member = members.get(i);
      // If correct member Id has been entered, look for member's registered boats, 
      // if correct name entered, delete boat, otherwise throw exception
      if (member.getMemberId().equals(memberId)) {
        for (Boat b : member.getRegisteredBoats()) {
          if (b.getBoatName().equals(boatName)) {
            member.deleteBoat(b);
            return;
          }
        }
        throw new IllegalArgumentException("Boat not found");
      }
    }
    throw new IllegalArgumentException("Member not found");
  }

  /**
  * Edit a boat.
  *
  * @param boatType the type of boat
  * @param size the boat size
  * @param boatName name of boat
  * @param memberId id of member
  * @param oldBoatName the old boat name before edit
  */
  public void editBoat(BoatType boatType, int size, String boatName, String memberId, String oldBoatName) 
      throws IllegalArgumentException {
    Member member;

    if (boatNameExists(boatName) == true) {
      throw new IllegalArgumentException("Duplicate boat name");
    }
    
    for (int i = 0; i < members.size(); i++) {
      member = members.get(i);

      if (member.getMemberId().equals(memberId)) {
        for (Boat b : member.getRegisteredBoats()) {
          if (b.getBoatName().equals(oldBoatName)) {
            b.setBoatName(boatName);
            b.setBoatType(boatType);
            b.setSize(size);
            return;
          }
        }
        throw new IllegalArgumentException("Boat not found");
      }
    }
    throw new IllegalArgumentException("Member not found");
  }
 
  /**
  * Add default data to the application by calling addMember and addBoat.
  */
  public void addDefaultData() {
    addMember("Chirsley", "Ajax", 1111);
    addBoat(BoatType.Sailboat, 10, "Stormy Waters", "A00001");
    addBoat(BoatType.Other, 15, "Riff Raff", "A00001");
    
    addMember("Botkin", "Berrycloth", 2222);
    addBoat(BoatType.Kayak, 10, "Wildflower", "B00002");

    addMember("Akira", "Alinsky", 3333);
    addBoat(BoatType.Sailboat, 13, "Hercules' Wave", "A00003");
    addBoat(BoatType.Kayak, 4, "Spartacus", "A00003");

    addMember("Averill", "Dempsey", 4444);
    addBoat(BoatType.Motorboat, 6, "Moor Often Than Knot", "D00004");
    addBoat(BoatType.Canoe, 2, "Wet Poodle", "D00004");
    addBoat(BoatType.Other, 269, "Titanic", "D00004");
  }

}
