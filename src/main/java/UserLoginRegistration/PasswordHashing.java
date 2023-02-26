package UserLoginRegistration;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
public class PasswordHashing {

  /**
   * This function takes a string and hashes it to make it more secure
   * This is used by the new user registration and login modules
   * @param password This is the unhashed password
   * @return This returns the hashed password.
   */
  public String hashString(String password) {
    String hashedPassword = null;

    MessageDigest m = null;
    try {
      m = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
    m.update(password.getBytes());
    byte[] bytes = m.digest();
    StringBuilder s = new StringBuilder();
    for (int i = 0; i < bytes.length; i++) {
      s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
    }
    // hashed password ->
    hashedPassword = s.toString();
    return hashedPassword;
  }
}
