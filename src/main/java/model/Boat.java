package model;

/** 
 * This class gets and sets a Boat with a type, size and name.
*/
public class Boat {
  private BoatType boatType;
  private int size;
  private String boatName;
  
  /** Constructor. */
  public Boat(BoatType bt, int s, String bn) {
    boatType = bt;
    size = s;
    boatName = bn;
  }

  public int getSize() {
    return size;
  }

  public String getBoatName() {
    return boatName;
  }

  public BoatType getBoatType() {
    return boatType;
  }

  public void setSize(int s) {
    size = s;
  }

  public void setBoatName(String bn) {
    boatName = bn;
  }

  public void setBoatType(BoatType bt) {
    boatType = bt;
  }
}