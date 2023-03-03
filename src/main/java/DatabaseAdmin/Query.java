package DatabaseAdmin;

import java.io.File;
import java.util.ArrayList;
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

public class Query {
  // current used database
  static String currentUsedDatabase;

  /**
   * This function is for the create keyword
   * @param sqlCommand this is the sql query
   * @param username the username of the current user
   */
  public void createQuery(String sqlCommand, String username) {
    // this method checks if the query is for creation of database of creation of table
    List<String> sqlCommandDecomposed = new ArrayList<>();
    sqlCommandDecomposed = sqlCommandRefactor(sqlCommand);

    if (sqlCommandDecomposed.contains("database")) {
      // create database executed
      createDatabase(sqlCommand, username);
    } else if (sqlCommandDecomposed.contains("table")) {
      // create table executed
      createTable(sqlCommand, username);
    } else {
      System.out.println("Syntax error please re-login ...");
    }
  }

  /**
   * This function executes the create database query
   * This is achieved by making a new directory
   * @param sqlCommand this is the sql query
   * @param username the user name
   */
  public void createDatabase(String sqlCommand, String username) {
    // makes a new dir with the db name inside the username directory that was created when loggin in for the first time
    List<String> sqlCommandDecomposed = new ArrayList<>();
    sqlCommandDecomposed = sqlCommandRefactor(sqlCommand);

    String user_directory = "src/main/java/Assets/" + username;
    String user_database_directory =
      user_directory + "/" + sqlCommandDecomposed.get(2);
    File userDatabaseDirectory = new File(user_database_directory);
    if (userDatabaseDirectory.isDirectory()) {
      // directory already exists.
      System.out.println("Database already exists!");
      AdminConsole adminConsole = new AdminConsole(username);
      adminConsole.AdminTEMP();
    } else {
      // create new user specific directory.
      new File(user_database_directory).mkdirs();
      System.out.println("Database created!");
      /**
       * Logic to create the database schema that stores the time of creation and user name
       * */
      AdminConsole adminConsole = new AdminConsole(username);
      adminConsole.AdminTEMP();
    }
  }

  /**
   * This is the use query for specifying the SQL terminal to use a particular Database
   * This basically sets the read/write path for all the files
   * @param sqlCommand the sql command
   * @param username the user name
   */
  public void useQuery(String sqlCommand, String username) {
    List<String> sqlCommandDecomposed = new ArrayList<>();
    sqlCommandDecomposed = sqlCommandRefactor(sqlCommand);
    // eg - "use database <DATABASE_NAME>"

    String user_directory = "src/main/java/Assets/" + username;
    String user_database_directory =
      user_directory + "/" + sqlCommandDecomposed.get(2);
    File userDatabaseDirectory = new File(user_database_directory);
    if (userDatabaseDirectory.isDirectory()) {
      // directory already exists.
      System.out.println("Using Database ");
      this.currentUsedDatabase = user_database_directory;
      AdminConsole adminConsole = new AdminConsole(username);
      adminConsole.AdminTEMP();
    } else {
      // create new user specific directory.
      new File(user_database_directory).mkdirs();
      System.out.println("Database does not exists!");
      AdminConsole adminConsole = new AdminConsole(username);
      adminConsole.AdminTEMP();
    }
  }

  public void createTable(String sqlCommand, String username) {
    // creating a table
    /**
     * We create two text files in the username/user_database dir
     * the first file contains all the values for the table
     * The values are divides using '-' eg - alen-19-bike
     * The second file is the schema that stores the following -
     * the column definition
     * the user logs - 1. date, 2. user-name
     * file naming - table_name.txt & table_name_schema.txt
     *
     * Constraint -
     * eg of the create table statement - space to be given after each parameter ( commas can be ignored )
     * CREATE Table Orders ( item_id int, orderDate varchar )
     * The varchar in this SQL is dynamic and does not require "()" -> will fail compilation if used
     * */
    String tableName;
    List<String> columnName = new ArrayList<>();
    List<String> columnDefinition = new ArrayList<>();
    if (currentUsedDatabase != null) {
      QueryCleaner queryCleaner = new QueryCleaner();
      List<String> decomposedQuery = queryCleaner.queryDecomposer(
        sqlCommand,
        username
      );
      ReadWriteToDatabase readWriteToDatabase = new ReadWriteToDatabase();
      //

      //
      String nameOfTable = queryCleaner.getTableName();
      readWriteToDatabase.createTableFromQuery(
        nameOfTable,
        decomposedQuery,
        username,
        currentUsedDatabase
      );
      AdminConsole adminConsole = new AdminConsole(username);
      adminConsole.AdminTEMP();
    } else {
      System.out.println("Please Create/Use Database ...");
      AdminConsole adminConsole = new AdminConsole(username);
      adminConsole.AdminTEMP();
    }
  }

  /**
   * This function decomposes the sql statement into an arraylist
   * The arraylist contains various keywords that is used to process
   * @param sqlCommand This is the sql command
   * @return the list of all the decomposed strings
   */
  public List<String> sqlCommandRefactor(String sqlCommand) {
    sqlCommand = sqlCommand.toLowerCase();
    List<String> sqlCommandDecomposed = new ArrayList<>();
    sqlCommand = sqlCommand.replace(";", "");
    sqlCommandDecomposed = List.of(sqlCommand.split(" "));
    return sqlCommandDecomposed;
  }

  /**
   * This function is used to insert values into a created table
   * @param sqlCommand the sql command
   * @param username the user name
   */
  public void insertIntoTable(String sqlCommand, String username) {
    /**
     * the query is decomposed into an array list, using delimiters the column defination and the
     * column value is stored in to different array list
     * this is then added to the respective text file
     *
     * Constraint -
     * eg of the insert table statement - space to be given after each parameter ( commas can be ignored )
     * insert into orders values ( 1234, 23/oct/2023, Alen-order, $34000 );
     * The varchar in this SQL is dynamic and does not require "()" -> will fail compilation if used
     * */
    String tableName;
    List<String> columnName = new ArrayList<>();
    List<String> columnDefinition = new ArrayList<>();
    if (currentUsedDatabase != null) {
      QueryCleaner queryCleaner = new QueryCleaner();
      List<String> decomposedQuery = queryCleaner.queryDecomposer(
        sqlCommand,
        username
      );
      List<String> decomposedQueryValues = new ArrayList<>();

      /**
       * Cleaning the value set
       * */
      for (int i = 1; i < decomposedQuery.size(); i++) {
        decomposedQueryValues.add(decomposedQuery.get(i));
      }
      /**
       * All the values are stored in the decomposed values
       * */
      ReadWriteToDatabase readWriteToDatabase = new ReadWriteToDatabase();
      //

      //
      String nameOfTable = queryCleaner.getTableName();
      readWriteToDatabase.insertIntoTableFromQuery(
        nameOfTable,
        decomposedQueryValues,
        username,
        currentUsedDatabase
      );
      AdminConsole adminConsole = new AdminConsole(username);
      adminConsole.AdminTEMP();
    } else {
      System.out.println("Please Create/Use Database ...");
      AdminConsole adminConsole = new AdminConsole(username);
      adminConsole.AdminTEMP();
    }
  }

  /**
   * This function is used to read all the values in a particular table
   * This is achieved by accessing the txt file by simply reading it and
   * printing the results
   * @param sqlCommand this is the sql query
   * @param username user name
   */
  public void SelectTable(String sqlCommand, String username) {
    String tableName;
    List<String> columnName = new ArrayList<>();
    List<String> columnDefinition = new ArrayList<>();
    ReadWriteToDatabase readWriteToDatabase = new ReadWriteToDatabase();
    if (currentUsedDatabase != null) {
      QueryCleaner queryCleaner = new QueryCleaner();
      String nameOfTable = queryCleaner.queryDecomposer(sqlCommand);
      String userTableDir = currentUsedDatabase + "/" + nameOfTable + ".txt";
      String tableContent = readWriteToDatabase.selectTable(userTableDir);
      // reading from database
      for (int i = 0; i < tableContent.length(); i++) {
        if (tableContent.charAt(i) == '-') {
          // print a single space to separate columns
          System.out.print("   ");
        }
        if (tableContent.charAt(i) == ';') {
          System.out.println("");
        }
        System.out.print(tableContent.charAt(i));
      }
      //
      AdminConsole adminConsole = new AdminConsole(username);
      adminConsole.AdminTEMP();
    } else {
      System.out.println("Please Create/Use Database ...");
      AdminConsole adminConsole = new AdminConsole(username);
      adminConsole.AdminTEMP();
    }
  }

  /**
   * This is for the update statement
   * updates the table for a given value
   * @param sqlCommand
   * @param username
   */
  public void updateTable(String sqlCommand, String username) {
    if (currentUsedDatabase == null) {
      QueryCleaner queryCleaner = new QueryCleaner();
      List<String> decomposedQuery = queryCleaner.queryDecomposer(
              sqlCommand,
              username
      );
      ReadWriteToDatabase readWriteToDatabase = new ReadWriteToDatabase();
      String nameOfTable = queryCleaner.getTableName();
      readWriteToDatabase.deleteTableFromQuery(
              nameOfTable,
              decomposedQuery,
              username,
              currentUsedDatabase
      );
      AdminConsole adminConsole = new AdminConsole(username);
      adminConsole.AdminTEMP();
    } else {
      System.out.println("Updated ...");
      AdminConsole adminConsole = new AdminConsole(username);
      adminConsole.AdminTEMP();
    }
  }

  /**
   * This function is used to delete a table from the database
   * @param sqlCommand this is the sql command
   * @param username this is the user name
   */
  public void deleteTable(String sqlCommand, String username) {
    if (currentUsedDatabase != null) {
      QueryCleaner queryCleaner = new QueryCleaner();
      List<String> decomposedQuery = queryCleaner.queryDecomposer(
        sqlCommand,
        username
      );
      ReadWriteToDatabase readWriteToDatabase = new ReadWriteToDatabase();
      String nameOfTable = queryCleaner.getTableName();
      readWriteToDatabase.deleteTableFromQuery(
        nameOfTable,
        decomposedQuery,
        username,
        currentUsedDatabase
      );
      AdminConsole adminConsole = new AdminConsole(username);
      adminConsole.AdminTEMP();
    } else {
      System.out.println("Please Create/Use Database ...");
      AdminConsole adminConsole = new AdminConsole(username);
      adminConsole.AdminTEMP();
    }
  }
}
