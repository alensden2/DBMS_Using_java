package FileManagement;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

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
public class ReadFileAuth {
  File file = new File("src/main/java/Assets/userAuth.txt");
  Path filePath = Path.of("src/main/java/Assets/userAuth.txt");
  String fileContent;

  {
    try {
      fileContent = Files.readString(filePath);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * This is a helper method and this checks if the auth file is already present to store the new
   * user registrations. Incase the file is already present this returns true. In the case the
   * file is not present this returns false and creates a new file to store all the user auth details.
   *
   * @return This returns True or False if the auth file is present or not present in the directory.
   */
  public boolean checkFileIfExist() {
    // reference - https://www.w3schools.com/java/java_files_create.asp

    File myObj = new File("src/main/java/Assets/userAuth.txt");
    try {
      if (myObj.createNewFile()) {
        return false;
      } else {
        return true;
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Checks if the username is present in the file.
   *
   * @param username The username entered by the user
   * @return returns true if the user is already present in the auth file
   */
  public boolean checkIfUserExists(String username) {
    return fileContent.contains(username);
  }
}
