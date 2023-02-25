package DatabaseAdmin;

import java.io.File;
import java.nio.file.Path;
import java.util.Scanner;

public class AdminConsole {
    String username;

    public AdminConsole(String username) {
        this.username = username;
    }

    public void AdminTEMP() {
        System.out.println("SQL Shell running ...");
        Scanner s = new Scanner(System.in);
        String currentSqlCommand = s.nextLine();
        findSqlCodeKeyWord(currentSqlCommand);

    }

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

    public boolean checkIfValidKeyword(String sqlKeyword) {
        SqlExecuter sqlExecuter = new SqlExecuter();
        return sqlExecuter.checkIfValidKeyword(sqlKeyword);
    }

    public void executeSqlStatement(String sqlStatementKeyword, String sqlCommand, String username) {
        SqlExecuter sqlExecuter = new SqlExecuter();
        sqlExecuter.executeSqlQuery(sqlStatementKeyword, sqlCommand, username);
    }

}
