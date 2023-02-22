package FileManagement;

import java.io.File;
import java.io.IOException;

public class ReadFileAuth {
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
}
