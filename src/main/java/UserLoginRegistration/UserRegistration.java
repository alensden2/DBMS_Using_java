package UserLoginRegistration;

import FileManagement.ReadFileAuth;
import FileManagement.WriteFileAuth;
import LandingPage.LandingPage;

public class UserRegistration {
    /**
     * Creates new user
     */
    ReadFileAuth readFileAuth = new ReadFileAuth();
    WriteFileAuth writeFileAuth = new WriteFileAuth();


    public void createNewUser(String username, String password) {
        // reference - https://www.javatpoint.com/how-to-encrypt-password-in-java

        // if file exists write or else make a new file
        readFileAuth.checkFileIfExist();
        PasswordHashing passwordHashing = new PasswordHashing();
        String hashedPassword = passwordHashing.hashString(password);
        // storing the hash password and username in the txt file
        writeUserDetailsToFile(username, hashedPassword);
    }

    public void writeUserDetailsToFile(String username, String hashedPassword) {
        //if user already exists then dont add the user
        if (readFileAuth.checkIfUserExists(username) != true) {
            writeFileAuth.writeUserAuthData(username, hashedPassword);
            LandingPage landingPage = new LandingPage();
            landingPage.landingPage();
        }
        else {
            System.out.println("User exists! Please try again.");
            System.out.println("");
            LandingPage landingPage = new LandingPage();
            landingPage.landingPage();
        }
        // else write to auth file
    }

}
