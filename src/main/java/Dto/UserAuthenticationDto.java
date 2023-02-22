package Dto;

public class UserAuthenticationDto {
    String name;
    String hashedPassword;
    String securityQuestion;
    String answer;

    public UserAuthenticationDto(String name, String hashedPassword, String securityQuestion, String answer){
        this.name = name;
        this.hashedPassword = hashedPassword;
        this.securityQuestion = securityQuestion;
        this.answer = answer;
    }

}
