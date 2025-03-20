package employee.management.system;



import java.sql.*;


public class DBConnection {

    // Static method to establish a connection
    public static Connection getConnection() {
        Connection conn = null;
        
     String URL = "jdbc:mysql://localhost:3306/emp"; 
     String USER = "root"; 
     String PASSWORD = ""; 
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            if(conn!=null){
            System.out.println("Database connected successfully!");}
            else{
                System.out.println("connection failed");
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
        return conn;
    }
}
