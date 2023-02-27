package DatabaseAdmin;

import java.io.File;
import java.nio.file.Path;
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

public class AdminConsole {
  String username;

  /**
   * Constructor for the Admin Console class
   * Takes and sets the current logged in user
   * @param username The current logged user
   */
  public AdminConsole(String username) {
    this.username = username;
  }

  /**
   * this function is the console where the user enters sql queries to be executed
   */
  public void AdminTEMP() {
    System.out.println("^^^^^^^^^^^^^^ CONSOLE LOGS ^^^^^^^^^^^^^^");
    System.out.println("........... ALEN SQL Shell running ..........");
    System.out.println("this shell is whitespace sensitive ...");
    System.out.println(
      "To create a new Database -> Eg : \'create database NAME_OF_DATABASE;\'"
    );
    System.out.println(
      "To use a existing Database -> Eg : \'use database NAME_OF_DATABASE;\'"
    );
    System.out.println(
      "To create a new table -> Eg : \'create table orders ( item_id int, orderDate varchar, nameOfOrder varchar, orderValue int );\' NOTE - All the columns are dynamic. Eg : don't use varchar(23)"
    );
    System.out.println(
      "To read the table please use -> Eg : \'select * from orders;\'"
    );
    System.out.println(
      "To insert into a table -> Eg : \'insert into orders values ( 1234, 23/oct/2023, Alen-order, $34000 );\' NOTE - All the columns are dynamic. Eg : don't use \" \" for varchar "
    );
    System.out.println(
      "To update table -> Eg : \'update table orders ( 1234, 23/oct/2023, Alen-order, $34000 );\'"
    );
    System.out.println("To delete table -> Eg : \'delete table orders;\'");
    System.out.println(
      "........... CHECK EXECUTION LOGS LOGGED ABOVE ..........."
    );
    System.out.println("........... Please execute query below ..........");
    Scanner s = new Scanner(System.in);
    String currentSqlCommand = s.nextLine();
    // this finds the sql query key word (eg - select, insert, delete)
    findSqlCodeKeyWord(currentSqlCommand);
    s.close();
  }

  /**
   * this finds the sql key word to execute the query
   * @param sqlCommand this is the entire sql command
   */
  public void findSqlCodeKeyWord(String sqlCommand) {
    SqlExecuter sqlExecuter = new SqlExecuter();
    if (sqlExecuter.sqlQueryDecomposer(sqlCommand)) {
      // executes if statement closed with ";"
      String sqlStatementKeyword = sqlExecuter.identifySqlCommand(sqlCommand);
      // checks if the keyword is valid
      // if keyword valid executes the statement
      if (checkIfValidKeyword(sqlStatementKeyword)) {
        System.out.println("Executing query ...");
        //query execution logic
        executeSqlStatement(sqlStatementKeyword, sqlCommand, username);
      } else {
        // if keyword not valid, prompts wrong syntax message
        System.out.println("Wrong syntax, please try again ...");
        AdminTEMP();
      }
      //System.out.println(sqlStatementKeyword);
    } else {
      System.out.println("Please include \";\" in your command");
      AdminTEMP();
    }
  }

  /**
   * This function checks if the keyword is valid
   * @param sqlKeyword the extracted keyword
   * @return True if the sqlKeyword is valid
   */
  public boolean checkIfValidKeyword(String sqlKeyword) {
    SqlExecuter sqlExecuter = new SqlExecuter();
    return sqlExecuter.checkIfValidKeyword(sqlKeyword);
  }

  /**
   * This function executes the sql query
   * @param sqlStatementKeyword sql keyword
   * @param sqlCommand the sql query
   * @param username the user username
   */
  public void executeSqlStatement(
    String sqlStatementKeyword,
    String sqlCommand,
    String username
  ) {
    SqlExecuter sqlExecuter = new SqlExecuter();
    sqlExecuter.executeSqlQuery(sqlStatementKeyword, sqlCommand, username);
  }
}
