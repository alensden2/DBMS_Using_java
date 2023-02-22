package FileManagement;

import java.io.FileWriter;
import java.io.IOException;

public class WriteFileAuth {

  /**
   *
   * @param username
   * @param password
   * @param securityQuestion
   * @param securityAnswer
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
