package DatabaseAdmin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Query {

    static String currentUsedDatabase;

    public void createQuery(String sqlCommand, String username) {
        // this method checks if the query is for creation of database of creation of table
//        sqlCommand = sqlCommand.toLowerCase();
        List<String> sqlCommandDecomposed = new ArrayList<>();
//        sqlCommand = sqlCommand.replace(";","");
//        sqlCommandDecomposed = List.of(sqlCommand.split(" "));
        sqlCommandDecomposed = sqlCommandRefactor(sqlCommand);

        if (sqlCommandDecomposed.contains("database")) {
            // create database executed
            createDatabase(sqlCommand, username);
        } else if (sqlCommandDecomposed.contains("table")) {
            // create table executed
            createTable(sqlCommand,username);

        } else {
            System.out.println("Syntax error please re-login ...");
        }
    }

    public void createDatabase(String sqlCommand, String username) {
        // makes a new dir with the db name inside the username directory that was created when loggin in for the first time
        List<String> sqlCommandDecomposed = new ArrayList<>();
        sqlCommandDecomposed = sqlCommandRefactor(sqlCommand);
        // the command will be like Create database <DATABASE_NAME>
        // so the index:2 is the database name
        String user_directory = "src/main/java/Assets/" + username;
        String user_database_directory = user_directory + "/" + sqlCommandDecomposed.get(2);
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

    public void useQuery(String sqlCommand, String username) {
        List<String> sqlCommandDecomposed = new ArrayList<>();
        sqlCommandDecomposed = sqlCommandRefactor(sqlCommand);
        // eg - "use database <DATABASE_NAME>"

        String user_directory = "src/main/java/Assets/" + username;
        String user_database_directory = user_directory + "/" + sqlCommandDecomposed.get(2);
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
            QueryCleaner queryCleaner= new QueryCleaner();
            List<String> decomposedQuery = queryCleaner.queryDecomposer(sqlCommand,username);
            ReadWriteToDatabase readWriteToDatabase = new ReadWriteToDatabase();
            //

            //
            String nameOfTable = queryCleaner.getTableName();
            readWriteToDatabase.createTableFromQuery(nameOfTable,decomposedQuery,username,currentUsedDatabase);
            AdminConsole adminConsole = new AdminConsole(username);
            adminConsole.AdminTEMP();

        } else {
            System.out.println("Please Create/Use Database ...");
            AdminConsole adminConsole = new AdminConsole(username);
            adminConsole.AdminTEMP();
        }
    }

    public List<String> sqlCommandRefactor(String sqlCommand){
        sqlCommand = sqlCommand.toLowerCase();
        List<String> sqlCommandDecomposed = new ArrayList<>();
        sqlCommand = sqlCommand.replace(";","");
        sqlCommandDecomposed = List.of(sqlCommand.split(" "));
        return sqlCommandDecomposed;
    }
}
