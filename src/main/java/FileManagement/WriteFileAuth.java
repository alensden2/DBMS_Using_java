package FileManagement;

import java.io.FileWriter;
import java.io.IOException;

public class WriteFileAuth {

    public void writeUserAuthData(String username, String password){
        try {
            FileWriter myWriter = null;
            try {
                myWriter = new FileWriter("src/main/java/Assets/userAuth.txt", true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                myWriter.write(username);
                myWriter.write("\n");
                myWriter.write(password);
                myWriter.write("\n");
                myWriter.write(";");
                myWriter.write("\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                myWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Successfully wrote to the file.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
