package com.sushant.verma.common.dbConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
/**
 * Contains frequently used functions related to classic Java JDBC based database connection
 * @author Sushant Verma
 * @version 1.0
 * @since 13th may 2011
 */
public class DbConnection {

	Logger log=LogManager.getLogger(DbConnection.class);
	Connection conn = null;

	/**
	 * Use this method to get Database Connection using classic JDBC connection
	 * @return	java.sql.Connection object
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection getDbConnect() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
        String userName = "root";
        String password = "admin";
        String url = "jdbc:mysql://localhost/test";
        Class.forName ("com.mysql.jdbc.Driver").newInstance ();
        conn = DriverManager.getConnection (url, userName, password);
        if (conn!=null){
        	log.info ("Database connection established");
            return conn;
        }
        else 
        {
        	log.error("Cannot connect to database server");
        	throw new RuntimeException();
        }
        
    }
        


}
