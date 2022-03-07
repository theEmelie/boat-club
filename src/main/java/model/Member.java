package model;

import java.util.HashSet;

/** 
 * This class gets and sets a Member with a 
 * firstname, lastname, personal number and member id.
 * Also add/delete boat from member.
*/
public class Member {
  private String firstName;
  private String lastName;
  private int personalNumber; 
  private String memberId;
  private HashSet<Boat> registeredBoats;

  /** Constructor. */
  public Member(String fn, String ln, int pn, String id) throws UnsupportedOperationException {
    firstName = fn;
    lastName = ln;
    personalNumber = pn;
    memberId = id;
    registeredBoats = new HashSet<Boat>();
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public int getPersonalNumber() {
    return personalNumber;
  }

  public String getMemberId() {
    return memberId;
  }

  public HashSet<Boat> getRegisteredBoats() {
    return new HashSet<Boat>(registeredBoats);
  }

  public void setFirstName(String fn) {
    firstName = fn;
  }

  public void setLastName(String ln) {
    lastName = ln;
  }

  public void setPersonalNumber(int pn) {
    personalNumber = pn;
  }

  public void setMemberId(String id) {
    memberId = id;
  }

  /** 
   * Add boat to member. 
   *
   * @param b the boat
   */
  public void addBoat(Boat b) {
    registeredBoats.add(b);
  }

  /** 
   * Delete boat from member.
   *
   * @param b the boat
   */
  public void deleteBoat(Boat b) {
    registeredBoats.remove(b);
  }

  /**
   * Check if boat name exists.
   *
   * @param boatName name of boat
   * @return true or false if boat name exists
   */
  public boolean boatNameExists(String boatName) {
    boolean foundBoatName = false;

    for (Boat b : registeredBoats) {
      if (b.getBoatName().equalsIgnoreCase(boatName)) {
        foundBoatName = true;

        break;
      } 
    }
    return foundBoatName;
  }

}
