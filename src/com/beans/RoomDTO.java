package com.beans;

/**
 * Created by Administrator on 2017/4/16.
 */
public class RoomDTO {
    private String roomname;
    private String roomId;
    private String hostId;//主持人的userId

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

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }
}
