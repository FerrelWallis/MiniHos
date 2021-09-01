package com.example.minihos.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HosDBUtil1 {
	String user="root";
	String password="root";
	String url="jdbc:mysql://localhost:3306/hospital";
	String driver="com.mysql.jdbc.Driver";
	
	//��ȡ1��ҽԺ��con
	public Connection getcon() 
	{
		Connection con=null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, password);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
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

