package Dto;

public class UserAuthenticationDto {
    String name;
    String hashedPassword;
    String securityQuestion;
    String answer;

    public String getName() {
        return name;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public String getAnswer() {
        return answer;
    }

    public UserAuthenticationDto(String name, String hashedPassword, String securityQuestion, String answer){
        this.name = name;
        this.hashedPassword = hashedPassword;
        this.securityQuestion = securityQuestion;
        this.answer = answer;
    }

    public UserAuthenticationDto() {
        this.name = "";
        this.hashedPassword = "";
        this.securityQuestion = "";
        this.answer = "";
    }

}
