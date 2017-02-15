/*
 * A programme to demonstrate how to use the database
 * metadata and it properties
 */
package dbmetadata;
import java.sql.*;
import java.sql.DriverManager;

/**
 *
 * @author harisu
 */
public class DBMetaData {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded");
         // onnect to the database
         Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javaDBProgramming","harisu","");
        System.out.println("Database connected");
        
        DatabaseMetaData dbMetaData = connection.getMetaData();
        System.out.println("database url: "+dbMetaData.getURL());
        System.out.println("Database user name: "+dbMetaData.getUserName());
        System.out.println("Database product name: "+dbMetaData.getDatabaseProductName());
        System.out.println("database product version: "+dbMetaData.getDatabaseProductVersion());
        System.out.println("JDBC Driver name: "+dbMetaData.getDriverName());
        System.out.println("JDBC Driver version: "+dbMetaData.getDriverVersion());
        System.out.println("JDBC driver major version: "+dbMetaData.getDriverVersion());
        System.out.println("JDBC driver minor version: "+dbMetaData.getDriverMinorVersion());
        System.out.println("Max number of connections: "+dbMetaData.getMaxConnections());
        System.out.println("Max table name length: "+dbMetaData.getMaxTableNameLength());
        System.out.println("Max columns in table "+dbMetaData.getMaxColumnsInTable());
        
        
        System.out.println("Result of the finding a users tables");
        //find the user tables 
        ResultSet rsTable = dbMetaData.getTables(null, null, null, new String[]{"TABLE"});
        System.out.println("User Tables");
        while(rsTable.next())
            System.out.print(rsTable.getString("TABLE_NAME")+" ");
        connection.close();
    }
    
}
