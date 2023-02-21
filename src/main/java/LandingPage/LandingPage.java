package LandingPage;

import java.util.Scanner;

public class LandingPage {
    public void landingPage(){
        int userSelection;
        System.out.println("Database Management system");
        System.out.println();
        System.out.println();
        System.out.println("[1] -> For existing user....");
        System.out.println("[2] -> For new user....");

        // taking the input from the user -

        Scanner s =  new Scanner(System.in);
        userSelection = s.nextInt();
        if (userSelection>3 && userSelection<0){
            incorectInput();
        }
        if(userSelection == 1){
            getLoginComponent();
        }
        if (userSelection == 2){
            getUserRegistrationComponent();
        }
    }

    /**
     * Gets the login module if the user exists
     * */
    public void getLoginComponent(){
        System.out.println("Redirecting to Login Module ...");
    }

    /**
     * Gets the new registration module if the user does not exists
     * */
    public void getUserRegistrationComponent(){
        System.out.println("Redirecting to User Registration Module ...");

    }

    public int incorectInput(){
        return 0;
    }

}
