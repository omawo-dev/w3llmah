/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3llmah.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author mapvsnp
 */
public class DB {
    
    private static Connection conn;
    
    public static final int DUPLICATE_ERROR_CODE = 19;
    
    public static Connection connect() {
        if(conn != null) {
            return conn;
        }
        conn = null;
        String url = "jdbc:sqlite:src/db/wa3llamah_SQlite.db";
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            System.err.println("error in connenting to database");
        }
        return conn;
    }
    public static String getCurrentDate(){
        
          DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	Date date = new Date();
	System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
        
        return " "+dateFormat.format(date);
    }
}
