package DatabaseAdmin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

public class SqlExecuter {
  List<String> sqlKeywords = new ArrayList<>(
    Arrays.asList(
      "select",
      "insert",
      "update",
      "delete",
      "begin",
      "commit",
      "create",
      "use"
    )
  );
  Query query = new Query();

  // for this version of sql termex begin and commit is for transaction

  /**
   * This function removes the delimiter for further processing.
   * @param sqlCommand this is the sql query
   * @return returns true if the statement was closed with ;
   */
  public boolean sqlQueryDecomposer(String sqlCommand) {
    if (sqlCommand.contains(";")) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * The function breaks query into words and selcts the first word the first word can be ( eg- Create, Insert, Update, Delete)
   * All the input taken from user is converted to lowercase case
   * @param sqlCommand this is the sql query
   * @return returns the key word
   */
  public String identifySqlCommand(String sqlCommand) {
    String sqlKeyword = "";
    for (int i = 0; i < sqlCommand.length(); i++) {
      if (sqlCommand.charAt(i) == ' ') {
        break;
      }
      sqlKeyword += sqlCommand.charAt(i);
      sqlKeyword = sqlKeyword.toLowerCase();
    }
    return sqlKeyword;
  }

  /**
   * This function checks if the captured keyword is valid by check if it is available
   * in the sqlkeyword list
   * @param sqlKeyword this is the sql keyword
   * @return returns true if contained in the array
   */
  public boolean checkIfValidKeyword(String sqlKeyword) {
    return sqlKeywords.contains(sqlKeyword);
  }

  /**
   * This function executes the sql query depending on the keyword captured
   * @param sqlStatementKeyword this is the sql keyword
   * @param sqlCommand this is the sql command
   * @param username this is the username
   */
  public void executeSqlQuery(
    String sqlStatementKeyword,
    String sqlCommand,
    String username
  ) {
    switch (sqlStatementKeyword) {
      case "create":
        query.createQuery(sqlCommand, username);
        break;
      case "use":
        query.useQuery(sqlCommand, username);
        break;
      case "insert":
        query.insertIntoTable(sqlCommand, username);
        break;
      case "select":
        query.SelectTable(sqlCommand, username);
        break;
      case "update":
        query.updateTable(sqlCommand, username);
        break;
      case "delete":
        query.deleteTable(sqlCommand, username);
        break;
      default:
        System.out.println("Please Enter a valid command");
    }
  }
}
