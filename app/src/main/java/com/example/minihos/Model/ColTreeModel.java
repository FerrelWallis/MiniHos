package com.example.minihos.Model;

import java.util.ArrayList;
import java.util.List;

public class ColTreeModel {

	private String name;
	private List<ColTreeModel> childList=new ArrayList<ColTreeModel>();
	int flag=1;
	String hname;
	String undercat;
//	HosDocModel doc=null;
	
	
	
	
	
	public String getHname() {
		return hname;
	}
	public String getUndercat() {
		return undercat;
	}
	public void setUndercat(String undercat) {
		this.undercat = undercat;
	}
	public void setHname(String hname) {
		this.hname = hname;
	}
	
//	public HosDocModel getDoc() {
//		return doc;
//	}
//	public void setDoc(HosDocModel doc) {
//		this.doc = doc;
//	}
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ColTreeModel> getChildList() {
		return childList;
	}
	public void setChildList(List<ColTreeModel> childList) {
		this.childList = childList;
	}
	
	
}
