/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NewsAgency4.newsagency;
import java.sql.*;
/**
 *
 * @author elsow
 */
public class DBConnection {
   
    static final String DB_URL = "jdbc:mysql://localhost/newsagencies?allowPublicKeyRetrieval=true&useSSL=false";
    static final String USER = "agencyadmin";
    static final String PASS = "agencyadmin";
    public Connection connectDB(){
         Connection conn = null;
        try{
            //
            Class.forName("com.mysql.jdbc.Driver");
            //open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            return conn;
        }catch(Exception ex){
            
            System.out.println("Error Connecting to DB");
            ex.printStackTrace();
            return null;
        }
    }
    
    public static void main(String[] args){
        DBConnection conn = new DBConnection();
        Connection con = conn.connectDB();
        System.out.println("yay");
        
        try{
      Statement st = con.createStatement();
      ResultSet res = st.executeQuery("SELECT *FROM AGENCIES");
      while(res.next()){
          System.out.println(res.getString(1));
          System.out.println(res.getString(2));
      }
        
        }catch(SQLException s){
        
        }
    }
}
