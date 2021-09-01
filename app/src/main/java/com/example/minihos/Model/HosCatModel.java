package com.example.minihos.Model;

public class HosCatModel {
	int cid;
	String cname;
	
	public HosCatModel() {

	}
	
	
	
	public void setcid(int c){
		cid=c;
	}
	
	public void setcname(String cn){
		cname=cn;
	}
	
	public int getcid(){
		return cid;
	}
	
	public String getcname(){
		return cname;
	}
	
}
