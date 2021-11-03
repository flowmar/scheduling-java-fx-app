package tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DBQuery
{


        // Statement Reference
        private static PreparedStatement statement;
        
        
        //Create Statement Object
        public static void setPreparedStatement(Connection connection, String sqlStatement) throws SQLException
        {
        // Create a statement via the connection
        statement = connection.prepareStatement(sqlStatement);
        }
        
        // Return Statement Object
        public static PreparedStatement getPreparedStatement()
        {
            return statement;
        }
}