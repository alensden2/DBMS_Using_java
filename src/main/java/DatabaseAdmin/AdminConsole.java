package DatabaseAdmin;

import java.util.Scanner;

public class AdminConsole {
    String username;

    public AdminConsole(String username) {
        this.username = username;
    }

    public void AdminTEMP() {
        System.out.println("Hi " + username + " welcome to AlenSQL...");
        Scanner s = new Scanner(System.in);
        String currentSqlCommand = s.nextLine();
        findSqlCodeKeyWord(currentSqlCommand);
    }

    public void findSqlCodeKeyWord(String sqlCommand) {
        SqlExecuter sqlExecuter = new SqlExecuter();
        if (sqlExecuter.sqlQueryDecomposer(sqlCommand)) {
            // executes if statement closed with ";"
            String sqlStatementKeyword = sqlExecuter.identifySqlCommand(sqlCommand);

            System.out.println(sqlStatementKeyword);
        } else {
            System.out.println("Please include \";\" in your command");
            AdminTEMP();
        }
    }
}
