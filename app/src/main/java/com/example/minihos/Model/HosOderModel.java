package com.example.minihos.Model;

public class HosOderModel {
	public static int i=0;
	int oid;
	String time;
	String name;
	String sex;
	int did;
	int number;
	
	public HosOderModel(int o,String t,String n,String s,int d,int nm) {
		// TODO Auto-generated constructor stub
		oid=o;
		time=t;
		name=n;
		sex=s;	
		did=d;
		number=nm;
	}
	
	public int getoid(){
		return oid;
	}
	
	public String gettime(){
		return time;
	}
	
	public String getname(){
		return name;
	}
	
	public String getsex(){
		return sex;
	}
	
	public int getdid(){
		return did;
	}
	
	public int getnumber(){
		return number;
	}
	
	
}
