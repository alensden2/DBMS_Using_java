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
        System.out.println("........... ALEN SQL Shell running ..........");
        System.out.println("this shell is whitespace sensitive ...");
        System.out.println("To create a new Database -> Eg : \'create database NAME_OF_DATABASE;\'");
        System.out.println("To use a existing Database -> Eg : \'use database NAME_OF_DATABASE;\'");
        System.out.println("To create a new table -> Eg : \'create table orders ( item_id int, orderDate varchar );\' NOTE - All the columns are dynamic. Eg : don't use varchar(23)");
        System.out.println("........... Please execute query below ..........");
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
