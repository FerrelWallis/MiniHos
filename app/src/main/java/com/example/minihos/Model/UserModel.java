package com.example.minihos.Model;

public class UserModel {
	int id;
	String name;
	String identity;
	String sex;
	String tel;
	String pwd;

	public UserModel(int idd,String un,String s,String t,String p,String iden) {
		// TODO Auto-generated constructor stub
		id=idd;
		pwd=p;
		name=un;
		sex=s;
		tel=t;
		identity=iden;
	}
	
	public UserModel(String un,String s,String t,String iden) {
		// TODO Auto-generated constructor stub
		name=un;
		sex=s;
		tel=t;
		identity=iden;
	}
	
	public UserModel(){
		
	}
	
	public void setid(int u){
		id=u;
	}
	
	public void setpwd(String p){
		pwd=p;
	}
	
	public void setname(String un){
		name=un;
	}
	
	public void setsex(String s){
		sex=s;
	}
	
	public void settel(String t){
		tel=t;
	}
	
	public void setident(String i){
		identity=i;
	}
	
	public int getid(){
		return id;
	}
	
	public String getpwd(){
		return pwd;
	}
	
	public String getsex(){
		return sex;
	}
	
	public String gettel(){
		return tel;
	}
	
	public String getname(){
		return name;
	}
	
	public String getiden(){
		return identity;
	}
}
