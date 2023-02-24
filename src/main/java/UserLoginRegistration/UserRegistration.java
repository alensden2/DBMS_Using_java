package UserLoginRegistration;

import FileManagement.ReadFileAuth;
import FileManagement.WriteFileAuth;
import LandingPage.LandingPage;

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
   *
   * @param username
   * @param password
   * @param securityQuestion
   * @param securityAnswer
   */
  public void createNewUser(
    String username,
    String password,
    String securityQuestion,
    String securityAnswer
  ) {
    // reference - https://www.javatpoint.com/how-to-encrypt-password-in-java

    // if file exists write or else make a new file
    readFileAuth.checkFileIfExist();
    PasswordHashing passwordHashing = new PasswordHashing();
    String hashedPassword = passwordHashing.hashString(password);
    // storing the hash password and username in the txt file
    writeUserDetailsToFile(
      username,
      hashedPassword,
      securityQuestion,
      securityAnswer
    );
  }

  /**
   *
   * @param username
   * @param hashedPassword
   * @param securityQuestion
   * @param securityAnswer
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
