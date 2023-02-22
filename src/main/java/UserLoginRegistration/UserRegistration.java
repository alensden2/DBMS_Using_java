package UserLoginRegistration;

import FileManagement.ReadFileAuth;
import FileManagement.WriteFileAuth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserRegistration {
    /**
     * Creates new user
     * */
    public void createNewUser(String username, String password){
        // reference - https://www.javatpoint.com/how-to-encrypt-password-in-java

        ReadFileAuth readFileAuth = new ReadFileAuth();
        readFileAuth.checkFileIfExist();
        PasswordHashing passwordHashing = new PasswordHashing();
        String hashedPassword = passwordHashing.hashString(password);
        // storing the hash password and username in the txt file
        WriteFileAuth writeFileAuth = new WriteFileAuth();
        writeFileAuth.writeUserAuthData(username,hashedPassword);
    }


}
