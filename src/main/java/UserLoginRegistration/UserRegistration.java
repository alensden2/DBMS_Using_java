package UserLoginRegistration;

import FileManagement.ReadFileAuth;
import FileManagement.WriteFileAuth;
import LandingPage.LandingPage;

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

public class UserRegistration {
  /**
   * Creates new user
   */
  ReadFileAuth readFileAuth;
  WriteFileAuth writeFileAuth;

  public UserRegistration() {
    readFileAuth = new ReadFileAuth();
    writeFileAuth = new WriteFileAuth();
  }

  /**
   * This method takes the required params for the creation of a new user and stores then in the auth file
   * The method checks if the auth file is already present (in the case of first time running of the app)
   * a new auth file is created at - src/main/java/Assets/userAuth.txt
   * The details are written to this file to log the new user for future log ins.
   *
   * @param username This is the username of the new user.
   * @param password This is the password of the new user.
   * @param securityQuestion This is the security question set by the user.
   * @param securityAnswer This is the security question answer set by the user.
   */
  public void createNewUser(
    String username,
    String password,
    String securityQuestion,
    String securityAnswer
  ) {
    // reference - https://www.javatpoint.com/how-to-encrypt-password-in-java

    // if user authentication file exists write or else make a new file
    readFileAuth.checkFileIfExist();

    // This class helps to securely store the password by hashing it.
    PasswordHashing passwordHashing = new PasswordHashing();
    // This returns the hashed password
    String hashedPassword = passwordHashing.hashString(password);
    // storing the hash password and username in the txt file

    // writes the user details
    writeUserDetailsToFile(
      username,
      hashedPassword,
      securityQuestion,
      securityAnswer
    );
  }

  /**
   * This method writes the user details to the auth file for the user authentication
   *
   * @param username This is the username of the new user.
   * @param hashedPassword This is the password of the new user.
   * @param securityQuestion This is the security question set by the user.
   * @param securityAnswer This is the security question answer set by the user.
   */
  public void writeUserDetailsToFile(
    String username,
    String hashedPassword,
    String securityQuestion,
    String securityAnswer
  ) {
    //if user already exists then dont add the user
    if (readFileAuth.checkIfUserExists(username) != true) {
      writeFileAuth.writeUserAuthData(
        username,
        hashedPassword,
        securityQuestion,
        securityAnswer
      );
      LandingPage landingPage = new LandingPage();
      landingPage.landingPage();
    } else {
      System.out.println("User exists! Please try again.");
      System.out.println("");
      LandingPage landingPage = new LandingPage();
      landingPage.landingPage();
    }
    // else write to auth file
  }
}
