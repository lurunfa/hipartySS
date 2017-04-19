package com.beans;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
import com.utils.RoomUserUtils;
import org.apache.mina.core.session.IoSession;

//@Entity
//@Table(name="roomuser")
public class RoomUser {
	
	private String userId;//用户id
	private String nickname="考拉";//昵称
	private int seat;//座位号
	private IoSession session;
	private RoomUserUtils roomUserUtils=new RoomUserUtils();
//	private String sign;//标签
//	private String roomId;//房间号	
//	private int id;//虚拟主键
	
	public RoomUser() {
		super();
	}

	public RoomUserUtils getRoomUserUtils() {
		return roomUserUtils;
	}

	public void setRoomUserUtils(RoomUserUtils roomUserUtils) {
		this.roomUserUtils = roomUserUtils;
	}

	//	public String getRoomId() {
//		return roomId;
//	}
//	public void setRoomId(String roomId) {
//		this.roomId = roomId;
//	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
//	public String getSign() {
//		return sign;
//	}
//	public void setSign(String sign) {
//		this.sign = sign;
//	}
	public int getSeat() {
		return seat;
	}
	public void setSeat(int seat) {
		this.seat = seat;
	}
	
//	@Id
//	@GeneratedValue
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public IoSession getSession() {
		return session;
	}

	public void setSession(IoSession session) {
		this.session = session;
	}
}
