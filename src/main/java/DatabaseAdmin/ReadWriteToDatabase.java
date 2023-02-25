package DatabaseAdmin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReadWriteToDatabase {
    public boolean createTableFromQuery(String tableName, List<String> decomposedQuery, String username, String currentUsedDatabase) {
        /**
         * Creates the table and table schema
         * inside the user dir and the current database dir
         * */
        String userDatabase = currentUsedDatabase;
        String userTableDir = userDatabase + "/" + tableName + ".txt";
        String userTableSchemaDir = userDatabase + "/" + tableName + "_Schema" + ".txt";
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

    public boolean insertIntoTableFromQuery(String tableName, List<String> decomposedQuery, String username, String currentUsedDatabase) {
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
}
