/**
 * Responsible for staring the application.
 */
public class Program {
  /**
   * Application starting point.

   * @param args command line arguments.
   */
  public static void main(String[] args) {
    controller.BoatClubController b = new controller.BoatClubController();
    
    b.manageBoatClub();
  }
}
