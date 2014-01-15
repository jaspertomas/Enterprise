/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author jaspertomas
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySqlDBHelper {

    static MySqlDBHelper  instance;

    public static MySqlDBHelper getInstance() {
        if(instance==null)
        {
            System.err.println("Error: MySqlDBHelper instance not initialized");
            System.exit(1);
        }
        return instance;
    }

    public Connection getConnection() {
        return con;
    }
    
    
    public static boolean init(String url, String user, String password)
    {
        try{
            if(instance==null)
            {
                instance=new MySqlDBHelper( url,  user,  password);
            }
            return true;
        }catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
        
    private MySqlDBHelper(String url, String user, String password) throws SQLException {

            con = DriverManager.getConnection(url, user, password);
//            st = con.createStatement();
//            rs = st.executeQuery("SELECT VERSION()");
//
//            if (rs.next()) {
//                System.out.println(rs.getString(1));
//            }
    }
    
        Connection con = null;
//        Statement st = null;
//        ResultSet rs = null;

    public void close() throws SQLException
    {
        if (con != null) {
            con.close();
        }
    }
}

