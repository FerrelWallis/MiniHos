package com.example.minihos.Model;

public class OderModel {
	int oid;
	int id;
	String name;
	String time;
	String hos;
	String sex;
	String cat;
	String doc;	
	int number;
	int hosnumber=-1;
	
	
	public OderModel(int o,int u,String n,String tm,String h,String sx,String c,String d,int nm) {
		// TODO Auto-generated constructor stub
		oid=o;
		id=u;
		name=n;
		time=tm;
		hos=h;
		sex=sx;
		cat=c;
		doc=d;		
		number=nm;
		
	}
	
	public OderModel(String n,String tm,String h,String c,String d,int nm) {
		// TODO Auto-generated constructor stub
		name=n;
		time=tm;
		hos=h;
		cat=c;
		doc=d;		
		number=nm;
		
		
	}
	
	public OderModel(int o,String n,String tm,String h,String sx,String c,String d,int nm) {
		// TODO Auto-generated constructor stub
		oid=o;
		name=n;
		time=tm;
		hos=h;
		sex=sx;
		cat=c;
		doc=d;		
		number=nm;
		
	}
	
	public void setHosnumber(int h){
		hosnumber=h;
	}
//	
//	public void setid(int u){
//		id=u;
//	}
//	
//	public void setuname(String un){
//		name=un;
//	}
	public int getHosnumber()
	{
		return hosnumber;
	}
	
	public int getoid()
	{
		return oid;
	}
	
	public int getid()
	{
		return id;
	}

	public String getname()
	{
		return name;
	}
	
	public String gettime()
	{
		return time;
	}
	
	public String gethos()
	{
		return hos;
	}
	
	public String getsex()
	{
		return sex;
	}
	
	public String getcat()
	{
		return cat;
	}
	
	public String getdoc()
	{
		return doc;
	}
	
	public String getstate()
	{
		return name;
	}
	
	public int getnumber()
	{
		return number;
	}
}
