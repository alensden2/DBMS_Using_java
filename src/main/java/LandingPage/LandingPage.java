package LandingPage;

import UserLoginRegistration.Login;
import UserLoginRegistration.UserRegistration;
import java.util.Scanner;

/**
 * This is a sample light weight database application
 * That can have multiple users and can have multiple tables and database for each user
 * The scope of the login exists beyond the execution of the program as the authentication
 * Information is stored in the log file present at - src/main/java/Assets/userAuth.txt
 * landing page is where the entire application is bootstrapped at.
 *
 * @author Alen John
 * @version 1.0
 * @since Feb 26, 2023
 *
 * @see Java Docs reference - "https://www.tutorialspoint.com/java/java_documentation.htm"
 */
public class LandingPage {

  /**
   * This function renders the initial login page where the user can be prompted with three options
   * 1. New user creation
   * 2. Existing login
   * 3. Error message
   *
   * All the methods before login renders the landing page again after execution
   * Eg - user registers successfully
   *      is returned to this page where they can login
   *      Vice - versa for errors during login
   * @return - void function returns nothing
   * */
  public void landingPage() {
    int userSelection;
    System.out.println("Database Management system");
    System.out.println();
    System.out.println();
    System.out.println("[1] -> For existing user....");
    System.out.println("[2] -> For new user....");

    // taking the input from the user -
    Scanner s = new Scanner(System.in);
    userSelection = s.nextInt();
    if (userSelection > 3 && userSelection < 0) {
      incorectInput();
    }
    if (userSelection == 1) {
      getLoginComponent();
    }
    if (userSelection == 2) {
      getUserRegistrationComponent();
    }
    s.close();
  }

  /**
   * Gets the login module if the user exists
   */
  public void getLoginComponent() {
    System.out.println("Redirecting to Login Module ...");
    System.out.println("Please enter the credentials : ");
    System.out.println();
    Scanner s = new Scanner(System.in);
    System.out.println("Username : ");
    String username = s.nextLine();
    System.out.println("Password : ");
    String password = s.nextLine();
    Login login = new Login(username, password);

    // stores the user details in the list obj after fetching from the txt file
    login.storeUserDetails();

    // capture current user details for user Auth -
    login.captureCurrentUserDetails();

    // check for auth status -
    // 1. if the current user dto empty - no user exits with that name
    // 2. if data there but hashed password wont match - incorrect password
    boolean firstAuth = login.checkUserAuthStatus();

    // for second factor authentication

    login.secondFactorAuthentication(firstAuth);
    s.close();
  }

  /**
   * Gets the new registration module if the user does not exist.
   * This will help the user to creating a new registration information
   * eg - the username, password, hint question and the hint answer
   *
   * @return - void function returns nothing
   *
   */
  public void getUserRegistrationComponent() {
    System.out.println("Redirecting to User Registration Module ...");
    System.out.println("Please enter the credentials : ");
    System.out.println();
    UserRegistration userRegistration = new UserRegistration();
    Scanner s = new Scanner(System.in);
    System.out.println("Username : ");
    String username = s.nextLine();
    System.out.println("Password : ");
    String password = s.nextLine();
    System.out.println("Hint Question : ");
    String question = s.nextLine();
    System.out.println("Answer : ");
    String answer = s.nextLine();
    userRegistration.createNewUser(username, password, question, answer);
    s.close();
  }

  public int incorectInput() {
    return 0;
  }
}
