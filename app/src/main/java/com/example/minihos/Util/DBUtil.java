package com.example.minihos.Util;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static android.support.constraint.Constraints.TAG;

public class DBUtil {

	String user="root";
	String password="root";
	String url="jdbc:mysql://192.168.137.1:3306/minihos";
	String driver="com.mysql.jdbc.Driver";
	public Connection getcon() 
	{
		Connection con=null;

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, password);
			Log.d("链接成功","链接成功");
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			Log.d("链接失败","链接失败");
			e.printStackTrace();

		}
		return con;
	}

	public void closeCon(Connection con){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
