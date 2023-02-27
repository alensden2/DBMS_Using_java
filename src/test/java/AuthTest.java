import DatabaseAdmin.QueryCleaner;
import DatabaseAdmin.ReadWriteToDatabase;
import DatabaseAdmin.SqlExecuter;
import Dto.UserAuthenticationDto;
import FileManagement.ReadFileAuth;
import FileManagement.WriteFileAuth;
import UserLoginRegistration.Login;
import UserLoginRegistration.UserRegistration;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
/**
 * FOR CLEAN TESTING PLEASE CLEAR all temp files like test tables in
 * /home/cynos/IdeaProjects/B00930528_AlenJohn_A1/src/main/java/Assets/admin and
 * user auth data from file
 *
 * Please run the TEST 1 separately twice as it checks for the auth file during the first test
 */
public class AuthTest {
    /**
     * This test creates a new user and saves the details in a text file 
     * please clean the userAuth.txt for clean testing
     */
    @Test
    public void testNewUserRegistration(){
        String username = "admin";
        String password = "admin";
        String question = "Hi admin is admin";
        String answer = "admin";
        WriteFileAuth writeFileAuth = new WriteFileAuth();
        ReadFileAuth readFileAuth = new ReadFileAuth();
        writeFileAuth.writeUserAuthData(username, password, question, answer);
        boolean result = readFileAuth.checkIfUserExists(username);
        assertEquals(true, result);

    }

    /**
     * This test tries to login in with the newly registered user
     */
    @Test
    public void testNewUserLogin(){
        String username = "admin";
        String password = "admin";
        String question = "Hi admin is admin";
        String answer = "admin";
        String storedAnswer = "";
        Login login = new Login(username, password);
        login.storeUserDetails();
        UserAuthenticationDto currentUserDetails = new UserAuthenticationDto();
        login.captureCurrentUserDetails();
        currentUserDetails = login.getCurrentUserDetails();
        storedAnswer = currentUserDetails.getAnswer();
        login.checkUserAuthStatus();
        boolean secondAuth = login.verifySecondFactorAuth(answer, storedAnswer);
        assertEquals(true,secondAuth);
    }

    /**
     * This test case creates a new table and verify the creation
     * please delete the existing (IF ANY) testing tables present in -/home/cynos/IdeaProjects/B00930528_AlenJohn_A1/src/main/java/Assets/admin
     * if not deleted the program wont overwrite the table it will return False
     */
    @Test
    public void testCreateNewTable(){
        String query = "create table ordersTest ( item_id int, orderDate varchar, nameOfOrder varchar, orderValue int );";
        QueryCleaner queryCleaner = new QueryCleaner();
        List<String> decomposedQuery = queryCleaner.queryDecomposer(
                query,
                "admin"
        );
        ReadWriteToDatabase readWriteToDatabase = new ReadWriteToDatabase();
        boolean taskSuccessful = readWriteToDatabase.createTableFromQuery(
                "ordersTest",
                decomposedQuery,
                "admin",
                "/home/cynos/IdeaProjects/B00930528_AlenJohn_A1/src/main/java/Assets/admin"
        );
        assertEquals(true,taskSuccessful);
    }
}
