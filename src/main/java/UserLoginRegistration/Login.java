package UserLoginRegistration;

import DatabaseAdmin.AdminConsole;
import Dto.UserAuthenticationDto;
import LandingPage.LandingPage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
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

public class Login {
  String username;
  String password;
  List<UserAuthenticationDto> userAuthenticationDetails = new ArrayList<>();
  File file = new File("src/main/java/Assets/userAuth.txt");
  Path filePath = Path.of("src/main/java/Assets/userAuth.txt");

  public UserAuthenticationDto getCurrentUserDetails() {
    return currentUserDetails;
  }

  UserAuthenticationDto currentUserDetails = new UserAuthenticationDto();
  String fileContent;

  /**
   * File content stores all the content in the file to be read
   */
  {
    try {
      fileContent = Files.readString(filePath);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Constructor class for the Login class
   *
   * @param username sets the username
   * @param password sets the password
   */
  public Login(String username, String password) {
    this.password = password;
    this.username = username;
  }

  /**
   * This function parses the string that stores the entire file content and finds the username,
   * password, hint question and hint answer in the userAuthDTO
   * The data stored in the DTO list can be used to verify the login credentials of the current user
   * trying to login
   *
   */
  public void storeUserDetails() {
    String currentUserDetailField = "";
    List<String> userRecord = new ArrayList<>();
    for (int i = 0; i < fileContent.length(); i++) {
      if ((fileContent.charAt(i) != '-') && (fileContent.charAt(i) != ';')) {
        currentUserDetailField += fileContent.charAt(i);
      } else if (fileContent.charAt(i) == '-') {
        userRecord.add(currentUserDetailField);
        currentUserDetailField = "";
      } else if (fileContent.charAt(i) == ';') {
        userRecord.add(currentUserDetailField);
        currentUserDetailField = "";
        UserAuthenticationDto userAuthenticationDto = new UserAuthenticationDto(
          userRecord.get(0),
          userRecord.get(1),
          userRecord.get(2),
          userRecord.get(3)
        );
        userAuthenticationDetails.add(userAuthenticationDto);
        userRecord.clear();
      }
    }
  }

  /**
   * This function fetches the current user details fron the DTO userAuthDetails
   * This details is used to compare the stored hashed password with the current entered
   * hashed password
   */
  public void captureCurrentUserDetails() {
    for (int i = 0; i < userAuthenticationDetails.size(); i++) {
      if (userAuthenticationDetails.get(i).getName().equals(username)) {
        currentUserDetails =
          new UserAuthenticationDto(
            userAuthenticationDetails.get(i).getName(),
            userAuthenticationDetails.get(i).getHashedPassword(),
            userAuthenticationDetails.get(i).getSecurityQuestion(),
            userAuthenticationDetails.get(i).getAnswer()
          );
      }
    }
  }

  /**
   * This function compares the current entered hashed password with the stored hashed password
   * @return returns true if the passwords match ! login success!
   */
  public boolean checkUserAuthStatus() {
    PasswordHashing passwordHashing = new PasswordHashing();
    String hashedPassword = passwordHashing.hashString(password);
    if (currentUserDetails == null) {
      return false;
    } else if (
      (currentUserDetails.getName().equals(username)) &&
      (currentUserDetails.getHashedPassword().equals(hashedPassword))
    ) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * This function is used to verify the second factor authentication
   * The hint question is fetched from the DTO only if the first auth flow is true
   * If the login is successful the user is redirected to the admin console.
   *
   * @param firstAuthFlow this is the params that is the return value from checkUserAuthStatus method
   * @return returns true if the second auth is successful, redirects to adminConsole
   */
  public boolean secondFactorAuthentication(boolean firstAuthFlow) {
    if (firstAuthFlow) {
      System.out.println(
        "Hi " + username + " please answer your security question ..."
      );
      System.out.println();
      System.out.println(currentUserDetails.getSecurityQuestion());
      String answer;
      Scanner s = new Scanner(System.in);
      String questionAnswer = s.nextLine();
      answer = currentUserDetails.getAnswer();
      if (verifySecondFactorAuth(answer, questionAnswer)) {
        System.out.println("Login Success!");
        // redirect to admin console
        AdminConsole adminConsole = new AdminConsole(username);
        createDirectoryForUser(username);
        System.out.println("Hi " + username + " welcome to AlenSQL...");
        adminConsole.AdminTEMP();
        return true;
      } else {
        System.out.println(
          "Multi-factor authentication failed please try again ..."
        );
        LandingPage landingPage = new LandingPage();
        landingPage.landingPage();
        return false;
      }
    } else {
      System.out.println(
        "Multi-factor authentication failed please try again ..."
      );
      LandingPage landingPage = new LandingPage();
      landingPage.landingPage();
      return false;
    }
  }

  /**
   * This function compares the hint answer stored and the entered hint answer
   * If they match returns true
   * @param userAnswer current entered hint answer
   * @param realAnswer the stored real answer
   * @return returns true if the two params are equal
   */
  public boolean verifySecondFactorAuth(String userAnswer, String realAnswer) {
    if (userAnswer.equals(realAnswer)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * This function creates a new directory for the new user if it dosent exists
   * @param username the current logged in user
   */
  public void createDirectoryForUser(String username) {
    String directory = "src/main/java/Assets/" + username;
    File userDirectory = new File(directory);
    if (userDirectory.isDirectory()) {
      // directory already exists.
    } else {
      // create new user specific directory.
      new File(directory).mkdirs();
    }
  }
}
