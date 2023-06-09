package DatabaseAdmin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
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

public class ReadWriteToDatabase {

  /**
   * This function is used to create 2 tiles
   * the schema that stores column definination and the user name
   * the table txt file that stores the records seperated by "-" and ";"
   * @param tableName the table name
   * @param decomposedQuery the decomposed query
   * @param username the username of the user
   * @param currentUsedDatabase the current used database
   * @return returns true if table is created
   */
  public boolean createTableFromQuery(
    String tableName,
    List<String> decomposedQuery,
    String username,
    String currentUsedDatabase
  ) {
    /**
     * Creates the table and table schema
     * inside the user dir and the current database dir
     * */
    String userDatabase = currentUsedDatabase;
    String userTableDir = userDatabase + "/" + tableName + ".txt";
    String userTableSchemaDir =
      userDatabase + "/" + tableName + "_Schema" + ".txt";
    File valueFile = new File(userTableDir);
    File metaValueFile = new File(userTableSchemaDir);

    /**
     * dividing columns and columns definition
     * */
    List<String> columns = new ArrayList<>();
    List<String> columnsDefinition = new ArrayList<>();
    for (int i = 0; i < decomposedQuery.size(); i++) {
      // if i is even its the column name
      if (i % 2 == 0) {
        columns.add(decomposedQuery.get(i));
      } else {
        columnsDefinition.add(decomposedQuery.get(i));
      }
    }

    try {
      if (!valueFile.exists()) {
        valueFile.createNewFile();
        metaValueFile.createNewFile();
      } else {
        //table exists
        return false;
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    // writing to the file schema
    FileWriter myWriterSchema = null;
    try {
      myWriterSchema = new FileWriter(userTableSchemaDir, true);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    for (int i = 0; i < columnsDefinition.size(); i++) {
      try {
        myWriterSchema.write(columns.get(i));
        myWriterSchema.write("-");
        myWriterSchema.write(columnsDefinition.get(i));
        myWriterSchema.write("-");
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    try {
      myWriterSchema.write("DB_User-" + username);
      myWriterSchema.close();
      System.out.println("Table created successfully ...");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return true;
  }

  /**
   * This function adds records to the text file
   * @param tableName the table name
   * @param decomposedQuery the decomposed query
   * @param username the username of the user
   * @param currentUsedDatabase the current used database
   * @return true or false depending on the operation
   */
  public boolean insertIntoTableFromQuery(
    String tableName,
    List<String> decomposedQuery,
    String username,
    String currentUsedDatabase
  ) {
    /**
     * Creates the table and table schema
     * inside the user dir and the current database dir
     * */
    String userDatabase = currentUsedDatabase;
    String userTableDir = userDatabase + "/" + tableName + ".txt";
    File valueFile = new File(userTableDir);

    if (!valueFile.exists()) {
      System.out.println("Table does not exist ...");
    } else {
      //table exists
      // writing to the table txt
      FileWriter myWriterSchema = null;
      try {
        myWriterSchema = new FileWriter(userTableDir, true);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      for (int i = 0; i < decomposedQuery.size(); i++) {
        try {
          myWriterSchema.write(decomposedQuery.get(i));
          myWriterSchema.write("-");
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
      try {
        myWriterSchema.write(";");
        myWriterSchema.close();
        System.out.println("Table created successfully ...");
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      return false;
    }

    return true;
  }

  /**
   * This function returns the file content of the specified table
   * @param directoryOfTable this is the directory of the txt file
   * @return this contains the file content
   */
  public String selectTable(String directoryOfTable) {
    String tableContent = null;
    File file = new File(directoryOfTable);
    Path filePath = Path.of(directoryOfTable);

    try {
      tableContent = Files.readString(filePath);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return tableContent;
  }

  /**
   * This function deletes the table.
   * @param tableName the table name
   * @param decomposedQuery the decomposed query
   * @param username the username of the user
   * @param currentUsedDatabase the current used database
   */
  public void deleteTableFromQuery(
    String tableName,
    List<String> decomposedQuery,
    String username,
    String currentUsedDatabase
  ) {
    // cleaning the tableName

    tableName = tableName.replace(";", "");
    String userDatabase = currentUsedDatabase;
    String userTableDir = userDatabase + "/" + tableName + ".txt";
    String userTableSchemaDir =
      userDatabase + "/" + tableName + "_Schema" + ".txt";
    File valueFile = new File(userTableDir);
    File metaValueFile = new File(userTableSchemaDir);
    if (!valueFile.exists()) {
      // table does not exists
      System.out.println("Table does not exists!");
    } else {
      //table exists
      valueFile.delete();
      metaValueFile.delete();
      System.out.println("Table deleted!");
    }
  }
}
