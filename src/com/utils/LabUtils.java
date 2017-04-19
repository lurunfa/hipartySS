package com.utils;

import com.beans.Lab;
import com.beans.Room;
import com.beans.RoomUser;

public class LabUtils {
	
	public static Room FindRoom(String roomId){
		Lab lab=Lab.getLab();
		Room room=null;
		for(int i=0;i<lab.getList().size();i++){
			if(lab.getList().get(i).getRoomId().equals(roomId)){
				room=lab.getList().get(i);
			}
		}
		return room;
	}
	public static RoomUser FindRoomUser(String roomId,String userId){
		Lab lab=Lab.getLab();
		RoomUser roomuser=null;
		for(int i=0;i<lab.getList().size();i++){
			if(lab.getList().get(i).getRoomId().equals(roomId)){
				for(int j=0;j<lab.getList().get(i).getRoomnum();j++){					
					if(lab.getList().get(i).getUserlist().get(j).getUserId().equals(userId)){
						roomuser=lab.getList().get(i).getUserlist().get(j);
					}
				}
			}
		}
		return roomuser;
	}
}
