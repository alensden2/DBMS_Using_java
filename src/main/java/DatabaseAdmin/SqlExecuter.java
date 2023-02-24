package DatabaseAdmin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SqlExecuter {
    List<String> sqlKeywords = new ArrayList<>(Arrays.asList("select", "insert", "update", "delete", "begin", "commit"));
    // for this version of sql termex begin and commit is for transaction

    public boolean sqlQueryDecomposer(String sqlCommand){
        if(sqlCommand.contains(";")){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * breaks query into words and selcts the first word the first word can be ( eg- Create, Insert, Update, Delete)
     * All the input taken from user is converted to lowercase case */

    public String identifySqlCommand(String sqlCommand){
        String sqlKeyword = "";
        for(int i=0; i<sqlCommand.length(); i++){
            if(sqlCommand.charAt(i) == ' '){
                break;
            }
            sqlKeyword += sqlCommand.charAt(i);
            sqlKeyword = sqlKeyword.toLowerCase();
        }
        return sqlKeyword;
    }
}
