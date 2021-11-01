package tools;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class DBQuery
{


        // Statement Reference
        private static Statement statement;
        
        
        //Create Statement Object
        public static void setStatement(Connection connection) throws SQLException
        {
        // Create a statement via the connection
        statement = connection.createStatement();
        }
        
        // Return Statement Object
        public static Statement getStatement()
        {
            return statement;
        }
}