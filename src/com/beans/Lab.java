package com.beans;

import java.util.ArrayList;
import java.util.List;


public class Lab {
	private static Lab lab=null;
	private List<Room> roomlist=new ArrayList<>();

	private Lab() {		
		super();
	}
	
	public static Lab getLab(){
		if(lab==null){
			lab=new Lab();
			return lab;
		}
		return lab;
	}

	public List<Room> getList() {
		return roomlist;
	}

	public void setList(Room room) {
		this.roomlist.add(room);
	}
	public void setList(){
		this.roomlist = new ArrayList<>();
	}
	
}
