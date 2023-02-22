package UserLoginRegistration;

import Dto.UserAuthenticationDto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Login {
    String username;
    String password;
    List<UserAuthenticationDto> userAuthenticationDetails = new ArrayList<>();
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

    public Login(String username, String password) {
        this.password = password;
        this.username = username;
    }

    public void storeUserDetails() {
        String currentUserDetailField = "";
        List<String> userRecord = new ArrayList<>();
        for (int i = 0; i < fileContent.length(); i++) {
            if((fileContent.charAt(i) != '-') && (fileContent.charAt(i) != ';')){
                currentUserDetailField += fileContent.charAt(i);
            } else if (fileContent.charAt(i) == '-') {
                userRecord.add(currentUserDetailField);
                currentUserDetailField = "";
            }else if(fileContent.charAt(i) == ';'){
                userRecord.add(currentUserDetailField);
                currentUserDetailField = "";
                UserAuthenticationDto userAuthenticationDto = new UserAuthenticationDto(userRecord.get(0), userRecord.get(1), userRecord.get(2), userRecord.get(3));
                userAuthenticationDetails.add(userAuthenticationDto);
                userRecord.clear();
            }
        }
    }

}
