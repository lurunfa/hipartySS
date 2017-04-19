package com.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.utils.RoomUtils;

public class Room {

	private String roomname;
	private String roomId;
	private Date starttime;//创建时间
	private int	roomnum;//房间人数
	private String hostId;//主持人的userId
	private List<RoomUser> userlist= new ArrayList<>();
	private RoomUtils roomutils=new RoomUtils();

	public Room() {
		super();
	}
	public String getHostId() {
		return hostId;
	}
	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

	public List<RoomUser> getUserlist() {
		return userlist;
	}
	public void setUserlist(List<RoomUser> userlist) {
		this.userlist = userlist;
	}
	public String getRoomname() {
		return roomname;
	}
	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {

		this.starttime = starttime;
	}
	public int getRoomnum() {
		return roomnum;
	}
	public void setRoomnum(int roomnum) {
		this.roomnum = roomnum;
	}

	public RoomUtils getRoomutils() {
		return roomutils;
	}
	public void setRoomutils(RoomUtils roomutils) {
		this.roomutils = roomutils;
	}

}
