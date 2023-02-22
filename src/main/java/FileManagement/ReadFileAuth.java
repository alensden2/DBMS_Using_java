package FileManagement;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadFileAuth {
    File file = new File("src/main/java/Assets/userAuth.txt");
    Path filePath = Path.of("src/main/java/Assets/userAuth.txt");
    String fileContent;

    {
        try {
            fileContent = Files.readString(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean checkFileIfExist() {
        // reference - https://www.w3schools.com/java/java_files_create.asp

        File myObj = new File("src/main/java/Assets/userAuth.txt");
        try {
            if (myObj.createNewFile()) {
                return false;
            } else {
                return true;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks if the username is present
     */
    public boolean checkIfUserExists(String username) {
        return fileContent.contains(username);
    }

}
