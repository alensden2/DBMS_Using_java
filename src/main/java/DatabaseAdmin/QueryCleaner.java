package DatabaseAdmin;

import java.util.ArrayList;
import java.util.List;

public class QueryCleaner {
    String tableName;
    public List<String> queryDecomposer(String sqlQuery, String username) {
        sqlQuery = sqlQuery.replace("(", "");
        sqlQuery = sqlQuery.replace(")", "");
        sqlQuery = sqlQuery.replace(",", "");
        List<String> decomposedQuery = new ArrayList<>();
        List<String> decomposedQueryTemp = new ArrayList<>();
        List<String> decomposedQueryTempColumns = new ArrayList<>();

        decomposedQueryTemp = List.of(sqlQuery.split(" "));

        for (int i = 0; i < decomposedQueryTemp.size(); i++) {
            if (!decomposedQueryTemp.get(i).equals(" ")) {
                decomposedQuery.add(decomposedQueryTemp.get(i));
            }
        }

        for (int i = 4; i < decomposedQuery.size(); i++) {
            if ((!decomposedQuery.get(i).equals(";"))) {
                decomposedQueryTempColumns.add(decomposedQuery.get(i));

            }
        }
        String tableName = decomposedQuery.get(2); // the name is always at 2 index
        this.tableName = tableName;
        return decomposedQueryTempColumns;
    }

    public String getTableName(){
        return this.tableName;
    }
}
