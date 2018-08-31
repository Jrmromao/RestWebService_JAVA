/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.awt.Component;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
import javax.swing.JOptionPane;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jrmromao
 */
public class DBUtil {

    public JdbcRowSet _rs = null;
    private String USERNAME = null;
    private String PASS = null;
    private String SQL = null;
    private String URL = null;
    private Connection conn = null;
    private PreparedStatement ps = null;

    public DBUtil() {

    }

    public JdbcRowSet connectDB() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            USERNAME = "root";
            PASS = "";
            URL = "jdbc:mysql://localhost:3306/groceriesdb";
            SQL = "SELECT * FROM user";
            _rs = RowSetProvider.newFactory().createJdbcRowSet();
            _rs.setUrl(URL);
            _rs.setUsername(USERNAME);
            _rs.setPassword(PASS);
            _rs.setCommand(SQL);

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null,
                    "DBUtil ERROR: " + ex.getMessage());
        }
        return _rs;
    }//end connectDB

    public ArrayList<User> getAllUsers() {
        ArrayList<User> UserList = new ArrayList<User>();

        try {
            connectDB().execute();

            while (_rs.next()) {

                User user = new User(); //create a new instance of the account class in every loop 

            }
        } catch (Exception ex) {
            Component frame = null;
            JOptionPane.showMessageDialog(frame,
                    "Connection ERROR: " + ex.getMessage());
        }
        return UserList;

    }//end getAllUsers

    public User loginUser(String name, String password) {

        User user = new User();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            USERNAME = "root";
            PASS = "";
            URL = "jdbc:mysql://localhost:3306/groceriesdb";
            SQL = "SELECT * FROM user WHERE name = ? AND password = ?";
            _rs = RowSetProvider.newFactory().createJdbcRowSet();
            _rs.setUrl(URL);
            _rs.setUsername(USERNAME);
            _rs.setPassword(PASS);
            _rs.setCommand(SQL);
            _rs.setString(1, name);
            _rs.setString(2, password);
            _rs.execute();

            if (_rs.next()) {

                user.setId(_rs.getInt(1));
                user.setName(_rs.getString(2));
                user.setEmail(_rs.getString(3));
                user.setPassword(_rs.getString(4));
                user.setDateJoined(_rs.getString(5));

            } else {
                return null;
            }
        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null,
                    "LOGIN USER ERROR: " + ex.getMessage());
        }

        return user;
    }//end login

    public Product getProduct(String upc_num) {

        Product p = new Product();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            USERNAME = "root";
            PASS = "";
            URL = "jdbc:mysql://localhost:3306/groceriesdb";
            SQL = "SELECT * FROM product_source WHERE upc_num = ?";
            _rs = RowSetProvider.newFactory().createJdbcRowSet();
            _rs.setUrl(URL);
            _rs.setUsername(USERNAME);
            _rs.setPassword(PASS);
            _rs.setCommand(SQL);
            _rs.setString(1, upc_num);

            _rs.execute();

            if (_rs.next()) {

                p.setUpc_num(_rs.getInt(1));
                p.setProName(_rs.getString(2));
                p.setProdDescription(_rs.getString(3));
                p.setExpireDate(_rs.getString(4));
                p.setPrice(_rs.getDouble(5));
                p.setBrand(_rs.getString(6));

            } else {
                return null;
            }
        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null,
                    "GET PRODUCT ERROR: " + ex.toString());
        }

        return p;
    }// end getProduct

    public Product getBestProduct() {

        Product p = new Product();
        String upc_num = "20629083";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            USERNAME = "root";
            PASS = "";
            URL = "jdbc:mysql://localhost:3306/groceriesdb";
            SQL = "SELECT * FROM product_source WHERE upc_num = ?";
            _rs = RowSetProvider.newFactory().createJdbcRowSet();
            _rs.setUrl(URL);
            _rs.setUsername(USERNAME);
            _rs.setPassword(PASS);
            _rs.setCommand(SQL);
            _rs.setString(1, upc_num);

            _rs.execute();

            if (_rs.next()) {

                p.setUpc_num(_rs.getInt(1));
                p.setProName(_rs.getString(2));
                p.setProdDescription(_rs.getString(3));
                p.setExpireDate(_rs.getString(4));
                p.setPrice(_rs.getDouble(5));
                p.setBrand(_rs.getString(6));

            } else {
                return null;
            }
        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null,
                    "GET PRODUCT ERROR: " + ex.toString());
        }

        return p;
    }// end best getProduct

    public Boolean regUser(String name, String email, String password) {

        boolean isAvailable = checkUseIfUserExists(email);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();

        if (!isAvailable) {
            return false;
        } else {

            try {
                Class.forName("com.mysql.jdbc.Driver");
                USERNAME = "root";
                PASS = "";
                
                URL = "jdbc:mysql://localhost:3306/groceriesdb";

              Connection connection = DriverManager.getConnection(URL, USERNAME, PASS);

                SQL = "INSERT INTO user"
                        + "(name, email, password, dateJoined)"
                        + "VALUES (?, ?, ?, ?)";

                ps = connection.prepareStatement(SQL);
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, password);
                ps.setString(4, dateFormat.format(date));

                if (ps.executeUpdate() > 0) {
                    return true;
                } else {
                    return false;
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(null,
                        "regUser ERROR: " + ex.toString());
            }

        }
        return true;

    }// end regUser

    // to check in the user is already registered in the system
    private boolean checkUseIfUserExists(String email) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            USERNAME = "root";
            PASS = "";
            URL = "jdbc:mysql://localhost:3306/groceriesdb";

            SQL = "SELECT * FROM user WHERE  email = ?";
            _rs = RowSetProvider.newFactory().createJdbcRowSet();
            _rs.setUrl(URL);
            _rs.setUsername(USERNAME);
            _rs.setPassword(PASS);
            _rs.setCommand(SQL);

            _rs.setString(1, email);
            _rs.execute();

            if (_rs.next()) {
                return false;
            } else {
                return true;
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null,
                    "checkUseIfUserExists ERROR: " + ex.toString());
        }

        return true;
    }//end checkUseIfUserExists

//
//    
//    public  boolean insertUser(String name, String password) throws ClassNotFoundException {
//
//
//      
//            USERNAME = "root";
//            PASS = "";
//            URL = "jdbc:mysql://localhost:3306/groceriesdb";
//     
//try
//    {  
//        SQL = "INSERT INTO  user(name, password) VALUES(?, ?)";
//
//
//      String myDriver = "org.gjt.mm.mysql.Driver";
//      Class.forName(myDriver);
//      
//      java.sql.DBUtil conn = DriverManager.getConnection(URL, USERNAME, PASS);
//    
//
//      // create the mysql insert preparedstatement
//      PreparedStatement preparedStmt = conn.prepareStatement(SQL);
//      preparedStmt.setString (1, name);
//      preparedStmt.setString (2,  password);
//      preparedStmt.execute();
//      
//      conn.close();   
//      return true;
//    }
//    catch (SQLException e)
//    {
//       JOptionPane.showMessageDialog(null,
//                    "InSERT USER ERROR: " + e.getMessage());
//       return  false;
//    }
//
//    }
}
