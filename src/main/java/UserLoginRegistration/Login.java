package UserLoginRegistration;

import DatabaseAdmin.AdminConsole;
import Dto.UserAuthenticationDto;
import LandingPage.LandingPage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Login {
    String username;
    String password;
    List<UserAuthenticationDto> userAuthenticationDetails = new ArrayList<>();
    File file = new File("src/main/java/Assets/userAuth.txt");
    Path filePath = Path.of("src/main/java/Assets/userAuth.txt");
    UserAuthenticationDto currentUserDetails = new UserAuthenticationDto();
    String fileContent;

    {
        try {
            fileContent = Files.readString(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Login(String username, String password) {
        this.password = password;
        this.username = username;
    }

    public void storeUserDetails() {
        String currentUserDetailField = "";
        List<String> userRecord = new ArrayList<>();
        for (int i = 0; i < fileContent.length(); i++) {
            if ((fileContent.charAt(i) != '-') && (fileContent.charAt(i) != ';')) {
                currentUserDetailField += fileContent.charAt(i);
            } else if (fileContent.charAt(i) == '-') {
                userRecord.add(currentUserDetailField);
                currentUserDetailField = "";
            } else if (fileContent.charAt(i) == ';') {
                userRecord.add(currentUserDetailField);
                currentUserDetailField = "";
                UserAuthenticationDto userAuthenticationDto = new UserAuthenticationDto(userRecord.get(0), userRecord.get(1), userRecord.get(2), userRecord.get(3));
                userAuthenticationDetails.add(userAuthenticationDto);
                userRecord.clear();
            }
        }
    }

    public void captureCurrentUserDetails() {
        for (int i = 0; i < userAuthenticationDetails.size(); i++) {
            if (userAuthenticationDetails.get(i).getName().equals(username)) {
                currentUserDetails = new UserAuthenticationDto(userAuthenticationDetails.get(i).getName(),
                        userAuthenticationDetails.get(i).getHashedPassword(),
                        userAuthenticationDetails.get(i).getSecurityQuestion(),
                        userAuthenticationDetails.get(i).getAnswer());
            }
        }

    }

    public boolean checkUserAuthStatus(){
        PasswordHashing passwordHashing = new PasswordHashing();
        String hashedPassword = passwordHashing.hashString(password);
        if(currentUserDetails == null){
            return false;
        } else if ((currentUserDetails.getName().equals(username)) && (currentUserDetails.getHashedPassword().equals(hashedPassword))){
            return true;
        } else {
            return false;
        }
    }

    public boolean secondFactorAuthentication(boolean firstAuthFlow){
        if(firstAuthFlow){
            System.out.println("Hi "+ username + " please answer your security question ...");
            System.out.println();
            System.out.println(currentUserDetails.getSecurityQuestion());
            String answer;
            Scanner s = new Scanner(System.in);
            String questionAnswer = s.nextLine();
            answer = currentUserDetails.getAnswer();
            if(verifySecondFactorAuth(answer,questionAnswer)){
                System.out.println("Login Success!");
                // redirect to admin console
                AdminConsole adminConsole = new AdminConsole(username);
                adminConsole.AdminTEMP();
                return true;
            } else {
                System.out.println("Multi-factor authentication failed please try again ...");
                LandingPage landingPage = new LandingPage();
                landingPage.landingPage();
                return false;
            }
        }
        else {
            System.out.println("Multi-factor authentication failed please try again ...");
            LandingPage landingPage = new LandingPage();
            landingPage.landingPage();
            return false;
        }
    }

    public boolean verifySecondFactorAuth(String userAnswer, String realAnswer){
        if(userAnswer.equals(realAnswer)){
            return true;
        }
        else {
            return false;
        }
    }
}
