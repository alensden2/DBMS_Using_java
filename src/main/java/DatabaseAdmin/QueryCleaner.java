package DatabaseAdmin;

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

public class QueryCleaner {
  String tableName;

  public List<String> queryDecomposer(String sqlQuery, String username) {
    sqlQuery = sqlQuery.replace("(", "");
    sqlQuery = sqlQuery.replace(")", "");
    sqlQuery = sqlQuery.replace(",", "");
    List<String> decomposedQuery = new ArrayList<>();
    List<String> decomposedQueryTemp = new ArrayList<>();
    List<String> decomposedQueryTempColumns = new ArrayList<>();

    decomposedQueryTemp = List.of(sqlQuery.split(" "));

    for (int i = 0; i < decomposedQueryTemp.size(); i++) {
      if (!decomposedQueryTemp.get(i).equals(" ")) {
        decomposedQuery.add(decomposedQueryTemp.get(i));
      }
    }

    for (int i = 4; i < decomposedQuery.size(); i++) {
      if ((!decomposedQuery.get(i).equals(";"))) {
        decomposedQueryTempColumns.add(decomposedQuery.get(i));
      }
    }
    String tableName = decomposedQuery.get(2); // the name is always at 2 index
    this.tableName = tableName;
    return decomposedQueryTempColumns;
  }

  public String getTableName() {
    return this.tableName;
  }

  public String queryDecomposer(String sqlQuery) {
    List<String> decomposedQuery = new ArrayList<>();
    sqlQuery = sqlQuery.replace(";", "");
    decomposedQuery = List.of(sqlQuery.split(" "));
    return decomposedQuery.get(3);
  }
}
