package FileManagement;

import java.io.FileWriter;
import java.io.IOException;

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

public class WriteFileAuth {

  /**
   * This method writes the user authentication data to the auth file
   * the username, password, hint, and answer are seperated by the delimiter '-'
   * the different records are seperated by the delimiter ';'
   *
   * @param username This is the username of the new user.
   * @param password This is the password of the new user.
   * @param securityQuestion This is the security question set by the user.
   * @param securityAnswer This is the security question answer set by the user.
   */
  public void writeUserAuthData(
    String username,
    String password,
    String securityQuestion,
    String securityAnswer
  ) {
    try {
      FileWriter myWriter = null;
      try {
        myWriter = new FileWriter("src/main/java/Assets/userAuth.txt", true);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      try {
        myWriter.write(username);
        myWriter.write("-");
        myWriter.write(password);
        myWriter.write("-");
        myWriter.write(securityQuestion);
        myWriter.write("-");
        myWriter.write(securityAnswer);
        myWriter.write(";");
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      try {
        myWriter.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      System.out.println("Successfully wrote to the file.");
      System.out.println("");
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
