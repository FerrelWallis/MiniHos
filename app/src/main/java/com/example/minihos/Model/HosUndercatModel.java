package com.example.minihos.Model;

public class HosUndercatModel {
	int cid;
	int ucid;
	String uname;
	
	public HosUndercatModel() {

	}
	
	public void setcid(int c){
		cid=c;
	}
	
	public void setucid(int u){
		ucid=u;
	}
	
	public void setuname(String un){
		uname=un;
	}
	
	public int getcid(){
		return cid;
	}
	
	public int getucid(){
		return ucid;
	}
	
	public String getuname(){
		return uname;
	}
}
