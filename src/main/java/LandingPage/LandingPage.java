package LandingPage;

import UserLoginRegistration.Login;
import UserLoginRegistration.UserRegistration;

import java.util.Scanner;

public class LandingPage {

    public void landingPage() {
        int userSelection;
        System.out.println("Database Management system");
        System.out.println();
        System.out.println();
        System.out.println("[1] -> For existing user....");
        System.out.println("[2] -> For new user....");

        // taking the input from the user -

        Scanner s = new Scanner(System.in);
        userSelection = s.nextInt();
        if (userSelection > 3 && userSelection < 0) {
            incorectInput();
        }
        if (userSelection == 1) {
            getLoginComponent();
        }
        if (userSelection == 2) {
            getUserRegistrationComponent();
        }
    }

    /**
     * Gets the login module if the user exists
     */
    public void getLoginComponent() {
        System.out.println("Redirecting to Login Module ...");
        System.out.println("Please enter the credentials : ");
        System.out.println();
        Scanner s = new Scanner(System.in);
        System.out.println("Username : ");
        String username = s.nextLine();
        System.out.println("Password : ");
        String password = s.nextLine();
        Login login = new Login(username, password);

        // stores the user details in the list obj after fetching from the txt file
        login.storeUserDetails();

        // capture current user details for user Auth -
        login.captureCurrentUserDetails();

        // check for auth status -
        // 1. if the current user dto empty - no user exits with that name
        // 2. if data there but hashed password wont match - incorrect password
        boolean firstAuth = login.checkUserAuthStatus();

        // for second factor authentication

        login.secondFactorAuthentication(firstAuth);

    }

    /**
     * Gets the new registration module if the user does not exists
     */
    public void getUserRegistrationComponent() {
        System.out.println("Redirecting to User Registration Module ...");
        System.out.println("Please enter the credentials : ");
        System.out.println();
        UserRegistration userRegistration = new UserRegistration();
        Scanner s = new Scanner(System.in);
        System.out.println("Username : ");
        String username = s.nextLine();
        System.out.println("Password : ");
        String password = s.nextLine();
        System.out.println("Hint Question : ");
        String question = s.nextLine();
        System.out.println("Answer : ");
        String answer = s.nextLine();
        userRegistration.createNewUser(username, password, question, answer);
    }

    public int incorectInput() {
        return 0;
    }
}
