package com.example.ecommerce_d;
import java.sql.*;

public class DatabaseConnection {
    Connection con=null;
    String SQLURL="jdbc:mysql://localhost:3306/ecommerce?useSSL=false";
    String userName= "root";
    String password="@Karansingh4";
    DatabaseConnection() throws SQLException {
        con = DriverManager.getConnection(SQLURL,userName,password);
        if(con!=null){
            System.out.println("OUR CONNECTION IS NOT ESTABLISHED WITH THE DATABASE");
        }
    }
    //select * from temporary;
    public ResultSet executeQuery(String Query) throws SQLException {
        ResultSet result=null;
        try{
            Statement statement= con.createStatement();
             result= statement.executeQuery(Query);
            return result;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public int executeUpdate(String query){
        int row=0;
        try {
            Statement statement= con.createStatement();
            row=statement.executeUpdate(query);
            return row;
        }
        catch(Exception e){
           e.printStackTrace();
        }
        return row;
    }
}
