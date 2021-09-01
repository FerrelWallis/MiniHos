package com.example.minihos.DataDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.example.minihos.Model.HosCatModel;
import com.example.minihos.Model.HosDocModel;
import com.example.minihos.Model.HosOderModel;
import com.example.minihos.Model.HosUndercatModel;

public class HosDataDao {
	
	/*
	 * 获取医院主科室信息
	 */
	public void getCatData(Connection con,List<HosCatModel> catlist)throws SQLException{
		String sql="select * from category";
		PreparedStatement pstmt =con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			HosCatModel catModel=new HosCatModel();
			catModel.setcid(rs.getInt("cid"));
			catModel.setcname(rs.getString("cname"));
			
			catlist.add(catModel);
		}
		rs.close();
		pstmt.close();
	}
	
	
	/*
	 * ��ȡҽԺ��������Ϣ
	 */
	public void getUndercatData(Connection con,List<HosUndercatModel> undercatlist)throws SQLException{
		String sql="select * from undercat";
		PreparedStatement pstmt =con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			HosUndercatModel undercatModel=new HosUndercatModel();
			undercatModel.setcid(rs.getInt("cid"));
			undercatModel.setucid(rs.getInt("ucid"));
			undercatModel.setuname(rs.getString("uname"));
			
			undercatlist.add(undercatModel);
		}
		rs.close();
		pstmt.close();
	}
	
	/*
	 * ��ȡҽԺҽ����Ϣ
	 */
	public void getDocData(Connection con,List<HosDocModel> doclist)throws SQLException{
		String sql="select * from doctor";
		PreparedStatement pstmt =con.prepareStatement(sql);
		
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			HosDocModel docModel=new HosDocModel();
			docModel.setdid(rs.getInt("did"));
			docModel.setdname(rs.getString("dname"));
			docModel.setdpost(rs.getString("dpost"));
			docModel.setcid(rs.getInt("cid"));
			docModel.setphoto(rs.getString("dphoto"));
			docModel.setdetail(rs.getString("detail"));
			docModel.setucid(rs.getInt("ucid"));
			
			doclist.add(docModel);
		}
		rs.close();
		pstmt.close();
	}
	
	/*
	 * ͨ�����Һ�ҽ��������ȡĳλҽ����Ϣ
	 */
	public void getDocDataFrName(Connection con,String dname,String undercat,HosDocModel doc)throws SQLException{
		String sql="select doctor.* from doctor,undercat where doctor.cid=undercat.cid and doctor.ucid=undercat.ucid and dname=? and uname=?";
		PreparedStatement pstmt =con.prepareStatement(sql);
		pstmt.setString(1, dname);
		pstmt.setString(2, undercat);
		
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			
			doc.setdid(rs.getInt("did"));
			doc.setdname(rs.getString("dname"));
			doc.setdpost(rs.getString("dpost"));
			doc.setcid(rs.getInt("cid"));
			doc.setphoto(rs.getString("dphoto"));
			doc.setdetail(rs.getString("detail"));
			doc.setucid(rs.getInt("ucid"));
			
			
		}
		rs.close();
		pstmt.close();
	}
	
	
	public int getAllOderNum(Connection con) throws SQLException{
		int i=0;
		
		String sql="select * from orders";
		PreparedStatement pstmt =con.prepareStatement(sql);
		
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			i++;
		}
		rs.close();
		pstmt.close();
		return i;
		
	}
	
	public int getDocQueNum(Connection con,int did) throws SQLException{
		int i=0;
		
		String sql="select * from expertqueue where did=?";
		PreparedStatement pstmt =con.prepareStatement(sql);
		pstmt.setInt(1, did);
		
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			i++;
		}
		rs.close();
		pstmt.close();
		return i;
		
	}
	
	public int getCurrQueNum(Connection con,String doc) throws SQLException{		
		String sql="select num from doctor,currentqueue where doctor.did=currentqueue.did and dname=?";
		PreparedStatement pstmt =con.prepareStatement(sql);
		pstmt.setString(1, doc);
		
		ResultSet rs=pstmt.executeQuery();
		int n=0;
		while(rs.next()){
			n= rs.getInt("num");	
		}		
		rs.close();
		pstmt.close();
		return n;
	}
	
	public void insertOderData(Connection con,HosOderModel order) throws SQLException{
		String sql="insert into orders values(?,?,?,?,?,?)";
		PreparedStatement pstmt =con.prepareStatement(sql);
		
		pstmt.setInt(1, order.getoid());
		pstmt.setString(2, order.gettime());
		pstmt.setString(3, order.getname());
		pstmt.setString(4, order.getsex());
		pstmt.setInt(5, order.getdid());
		pstmt.setInt(6, order.getnumber());
		
		pstmt.executeUpdate();
		pstmt.close();
	}
	
	public void insertDocQueData(Connection con,int a,int b,int c) throws SQLException{
		String sql="insert into expertqueue values(?,?,?)";
		PreparedStatement pstmt =con.prepareStatement(sql);
		
		pstmt.setInt(1, a);
		pstmt.setInt(2, b);
		pstmt.setInt(3, c);
		
		
		pstmt.executeUpdate();
		pstmt.close();
	}
}
