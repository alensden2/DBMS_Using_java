package DatabaseAdmin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SqlExecuter {
    List<String> sqlKeywords = new ArrayList<>(Arrays.asList("select", "insert", "update", "delete", "begin", "commit", "create", "use"));
    Query query = new Query();
    // for this version of sql termex begin and commit is for transaction

    public boolean sqlQueryDecomposer(String sqlCommand) {
        if (sqlCommand.contains(";")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * breaks query into words and selcts the first word the first word can be ( eg- Create, Insert, Update, Delete)
     * All the input taken from user is converted to lowercase case
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

    public boolean checkIfValidKeyword(String sqlKeyword) {
        return sqlKeywords.contains(sqlKeyword);
    }

    public void executeSqlQuery(String sqlStatementKeyword, String sqlCommand, String username) {
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
