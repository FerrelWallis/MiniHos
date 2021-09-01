package com.example.minihos.Model;

public class CollectionModel {
	
	int id;
	String hname;
	String uname;
	String dname;

	public CollectionModel(int i, String h, String u, String d) {
		id = i;
		hname = h;
		uname = u;
		dname = d;
	}
	
	public CollectionModel(int i,String h, String u) {
		id = i;
		hname=h;
		uname = u;
	}

	public CollectionModel() {

	}

	public int getid() {
		return id;
	}

	public String gethname() {
		return hname;
	}

	public String getuname() {
		return uname;
	}

	public String getdname() {
		return dname;
	}

}
