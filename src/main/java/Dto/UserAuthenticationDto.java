package Dto;

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

/**
 * Dto class to capture the user auth stored in the auth file
 */
public class UserAuthenticationDto {
  String name;
  String hashedPassword;
  String securityQuestion;
  String answer;

  public String getName() {
    return name;
  }

  public String getHashedPassword() {
    return hashedPassword;
  }

  public String getSecurityQuestion() {
    return securityQuestion;
  }

  public String getAnswer() {
    return answer;
  }

  public UserAuthenticationDto(
    String name,
    String hashedPassword,
    String securityQuestion,
    String answer
  ) {
    this.name = name;
    this.hashedPassword = hashedPassword;
    this.securityQuestion = securityQuestion;
    this.answer = answer;
  }

  public UserAuthenticationDto() {
    this.name = "";
    this.hashedPassword = "";
    this.securityQuestion = "";
    this.answer = "";
  }
}
