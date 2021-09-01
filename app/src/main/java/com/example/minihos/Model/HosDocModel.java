package com.example.minihos.Model;

public class HosDocModel {
	int did;
	String dname;
	String dpost;
	int cid;
	int ucid;
	String photo;
	String detail;
	
	public HosDocModel(){
		
	}
	
	public void setdid(int d){
		did=d;
	}
	
	public void setucid(int d){
		ucid=d;
	}
	
	public void setdname(String dn){
		dname=dn;
	}
	
	public void setdpost(String dp){
		dpost=dp;
	}
	
	public void setcid(int c){
		cid=c;
	}
	
	public void setphoto(String p){
		photo=p;
	}
	
	public void setdetail(String dl){
		detail=dl;
	}
	
	public int getdid(){
		return did;
	}
	
	public int getucid(){
		return ucid;
	}
	
	public String getdname(){
		return dname;
	}
	
	public String getdpost(){
		return dpost;
	}
	
	public int getcid(){
		return cid;
	}
	
	public String getphoto(){
		return photo;
	}
	
	public String getdetail(){
		return detail;
	}
	
}
