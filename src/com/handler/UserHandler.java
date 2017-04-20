package com.handler;

import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.beans.*;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.google.gson.Gson;

import com.utils.HibernateUtil;
import com.utils.LabUtils;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

public class UserHandler implements IoHandler {
	private Lab lab= Lab.getLab();

	@Override
	public void exceptionCaught(IoSession arg0, Throwable arg1) throws Exception {
		System.out.println(arg1.getMessage());
	}

	@Override
	public void messageReceived(IoSession iossession, Object json) throws Exception {

		String decode=URLDecoder.decode(json.toString(),"utf-8");
		Gson gson = new Gson();
		Chater chater = gson.fromJson(decode.toString(), Chater.class);
		System.out.println(decode);
		switch(chater.getOrder()){
			case "create":handleCreate(iossession, chater);
				break;
			case "in":handleIn(iossession, chater);
				break;
			case "out":handleOut(iossession,chater);
				break;
			case "rank":handleRank(iossession, chater);
				break;
			case "introduce":handleIntroduce(iossession, chater);
				break;
			case "ensure_introduce":handleEnsureIntroduce(iossession, chater);
				break;
			case "punishment":handlePunishment(iossession, chater);
				break;
			case "ensure_punishment":handleEnsurePunishment(iossession, chater);
				break;
			case "talk":handleTalk(iossession,chater);
				break;
			case "ensure_warmgame":handleEnsureWarmGame(iossession,chater);
				break;
			case "searchroom":handleSearchRoom(iossession,chater);
				break;
			case "werewolf":handleWerewolf(iossession,chater);
				break;
			case "beginwerewolf":handleBeginWerewolf(iossession,chater);
			default:
				break;
		}
	}

	private void handleSearchRoom(IoSession iossession, Chater chater) {
		String roomname =chater.getMessage();
		List<RoomDTO> roomDTOlist=new ArrayList<>();
		Chater chater2 = new Chater();
		chater2.setOrder("searchroom");
		chater2.setMessage("SUCCEED");
		chater2.setRoomId(chater.getRoomId());
		chater2.setUserId(chater.getUserId());
		for(int i=0;i<lab.getList().size();i++){
			if(lab.getList().get(i).getRoomname().equals(roomname)){
				RoomDTO roomDTO=new RoomDTO();
				roomDTO.setRoomId(lab.getList().get(i).getRoomId());
				roomDTO.setRoomname(lab.getList().get(i).getRoomname());
				roomDTO.setHostId(lab.getList().get(i).getHostId());
				roomDTOlist.add(roomDTO);
			}
		}
		chater2.setObject(roomDTOlist);
		SendSingle(chater2,iossession);
	}

	private void handleBeginWerewolf(IoSession iossession, Chater chater) {
		Werewolf werewolf= (Werewolf) chater.getObject();
		List<RoomUser> playerlist=LabUtils.FindRoom(chater.getRoomId()).getUserlist();
		for(int i=0;i<werewolf.getunPlayerlist().size();i++){
			playerlist.remove(werewolf.getunPlayerlist().get(i));
		}
		Chater chater2=new Chater();
		chater2.setOrder("beginwerewolf");
		chater2.setRoomId(chater.getRoomId());

			//上帝
			chater2.setMessage("God");
			chater2.setUserId(werewolf.getGodId());
			SendSingle(chater2, LabUtils.FindRoomUser(chater.getRoomId(), werewolf.getGodId()).getSession());

			//预言家
			if (werewolf.isSeerIs()) {
				RoomUser player = playerlist.get((int) (Math.random() * 1000) % playerlist.size());
				playerlist.remove(player);
				chater2.setMessage("预言家");
				chater2.setUserId(player.getUserId());
				SendSingle(chater2, player.getSession());
			}
			//女巫
			if (werewolf.isWitchIs()) {
				RoomUser player = playerlist.get((int) (Math.random() * 1000) % playerlist.size());
				playerlist.remove(player);
				chater2.setMessage("女巫");
				chater2.setUserId(player.getUserId());
				SendSingle(chater2, player.getSession());
			}
			//猎人
			if (werewolf.isHunterIs()) {
				RoomUser player = playerlist.get((int) (Math.random() * 1000) % playerlist.size());
				playerlist.remove(player);
				chater2.setMessage("猎人");
				chater2.setUserId(player.getUserId());
				SendSingle(chater2, player.getSession());
			}
			//盗贼
			if (werewolf.isThiefIs()) {
				RoomUser player = playerlist.get((int) (Math.random() * 1000) % playerlist.size());
				playerlist.remove(player);
				chater2.setMessage("盗贼");
				chater2.setUserId(player.getUserId());
				SendSingle(chater2, player.getSession());
			}
			//白痴
			if (werewolf.isIdiotIs()) {
				RoomUser player = playerlist.get((int) (Math.random() * 1000) % playerlist.size());
				playerlist.remove(player);
				chater2.setMessage("白痴");
				chater2.setUserId(player.getUserId());
				SendSingle(chater2, player.getSession());
			}
			//丘比特
			if (werewolf.isCupidIs()) {
				RoomUser player = playerlist.get((int) (Math.random() * 1000) % playerlist.size());
				playerlist.remove(player);
				chater2.setMessage("丘比特");
				chater2.setUserId(player.getUserId());
				SendSingle(chater2, player.getSession());
			}
			//守卫
			if (werewolf.isGuardIs()) {
				RoomUser player = playerlist.get((int) (Math.random() * 1000) % playerlist.size());
				playerlist.remove(player);
				chater2.setMessage("守卫");
				chater2.setUserId(player.getUserId());
				SendSingle(chater2, player.getSession());
			}
			//小女孩
			if (werewolf.isGirlIs()) {
				RoomUser player = playerlist.get((int) (Math.random() * 1000) % playerlist.size());
				playerlist.remove(player);
				chater2.setMessage("小女孩");
				chater2.setUserId(player.getUserId());
				SendSingle(chater2, player.getSession());
			}
			//长老
			if (werewolf.isPresbyterIs()) {
				RoomUser player = playerlist.get((int) (Math.random() * 1000) % playerlist.size());
				playerlist.remove(player);
				chater2.setMessage("长老");
				chater2.setUserId(player.getUserId());
				SendSingle(chater2, player.getSession());
			}
			//狼人
			for (int i = 0; i < werewolf.getWerewolfnum(); i++) {
				RoomUser player = playerlist.get((int) (Math.random() * 1000) % playerlist.size());
				playerlist.remove(player);
				chater2.setMessage("狼人");
				chater2.setUserId(player.getUserId());
				SendSingle(chater2, player.getSession());
			}
			//村民
			for (int i = 0; i < werewolf.getVillagernum(); i++) {
				RoomUser player = playerlist.get(i);
				playerlist.remove(player);
				chater2.setMessage("村民");
				chater2.setUserId(player.getUserId());
				SendSingle(chater2, player.getSession());
			}
	}

	private void handleWerewolf(IoSession iossession, Chater chater) {
		Chater chater2 = new Chater();
		chater2.setOrder("werewolf");
		chater2.setMessage("SUCCEED");
		chater2.setRoomId(chater.getRoomId());
		chater2.setUserId(chater.getUserId());
		chater2.setObject(LabUtils.FindRoom(chater.getRoomId()).getUserlist());
		SendSingle(chater2,iossession);
	}

	private void handleOut(IoSession iossession, Chater chater) {
		Room room=new Room();
		room=LabUtils.FindRoom(chater.getRoomId());
		room.getUserlist().remove(LabUtils.FindRoomUser(chater.getRoomId(), chater.getUserId()));
		room.setRoomnum(room.getRoomnum()-1);

		Chater chater2 = new Chater();
		chater2.setMessage("SUCCEED");
		chater2.setOrder("out");
		chater2.setRoomId(chater.getRoomId());
		chater2.setUserId(chater.getUserId());
		SendSingle(chater2, iossession);

		Chater chater3=new Chater();
		chater3.setMessage(chater.getUserId()+"已经退出房间");
		chater3.setOrder("talk_out");
		chater3.setUserId(chater.getUserId());
		chater3.setRoomId(chater.getRoomId());
		SendAll(chater3, chater.getRoomId());
	}

	private void handleEnsureWarmGame(IoSession iossession, Chater chater) {
		String warmgameId;
		Map<String, Object> object = new HashMap<>();
		object = (Map<String, Object>) chater.getObject();
		warmgameId=(String) object.get("warmgameId");

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		WarmGame warmgame = (WarmGame) session.createQuery("from WarmGame where warmGameId=:WarmGameId")
				.setParameter("WarmGameId", warmgameId)
				.uniqueResult();
		session.getTransaction().commit();

		Map<String, Object> object2 = new HashMap<>();
		object2.put("warmgame",warmgame.getWarmGame());
		Chater chater2 = new Chater();
		chater2.setObject(object2);
		chater2.setMessage(CheckHost(chater.getRoomId(), chater.getUserId()));
		chater2.setOrder("ensure_warmgame");
		chater2.setRoomId(chater.getRoomId());
		chater2.setUserId(chater.getUserId());
		SendAll(chater2, chater.getRoomId());

	}

	private void handleTalk(IoSession iossession, Chater chater) {

		SendAll(chater, chater.getRoomId());

	}

	private void handleEnsurePunishment(IoSession iossession, Chater chater) {
		String punishmentId;
		Map<String, Object> object = new HashMap<>();
		object = (Map<String, Object>) chater.getObject();
		punishmentId=(String) object.get("punishmentId");

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		Punishment punishment = (Punishment) session.createQuery("from Punishment where punishmentId=:punishmentId")
				.setParameter("punishmentId", punishmentId)
				.uniqueResult();
		session.getTransaction().commit();
		System.out.println(punishment.getPunishment());
		Map<String, Object> object2 = new HashMap<>();
		if(LabUtils.FindRoomUser(chater.getRoomId(),chater.getUserId())==null){
			System.out.println(123);
		}
		String nickname = LabUtils.FindRoomUser(chater.getRoomId(),chater.getUserId()).getNickname();
		System.out.println(nickname);
		if(nickname!=null&&!nickname.equals("")){
			object2.put("punishment", nickname+":"+punishment.getPunishment());
		}
		else{
			object2.put("punishment", chater.getUserId()+":"+punishment.getPunishment());
		}

		Chater chater2 = new Chater();
		chater2.setObject(object2);
		chater2.setMessage("SUCCEED");
		chater2.setOrder("ensure_punishment");
		chater2.setRoomId(chater.getRoomId());
		SendAll(chater2, chater.getRoomId());

	}

	private void handlePunishment(IoSession iossession, Chater chater) {
		// 房主以广播的方式将惩罚信息发送给全部房员
		// 房间号通过chater.roomId传送
		String roomId = chater.getRoomId();
		Map<String, Object> obj = new HashMap<>();
		obj = (Map<String, Object>) chater.getObject();
		String level = (String) obj.get("punishmentLevel");

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Punishment> punishmentlist = session.createQuery("from Punishment where punishmentLevel=:punishmentLevel")
				.setParameter("punishmentLevel", level).list();
		session.getTransaction().commit();
		// 设定返回值
		Chater chater2 = new Chater();
		chater2.setOrder("punishment");
		chater2.setRoomId(roomId);
		Map<String, Object> object = new HashMap<>();
		object.put("size", String.valueOf(punishmentlist.size()));
		object.put("list", new Gson().toJson(punishmentlist));
		chater2.setObject(object);
		chater2.setMessage("SUCCEED");

		SendSingle(chater2, iossession);
	}

	private void handleRank(IoSession iossession, Chater chater) {

		int num=LabUtils.FindRoom(chater.getRoomId()).getRoomnum();
		Map<Integer,RoomUser> roomusermap=new HashMap<>();
		List<Integer> ranklist=new ArrayList<>();
		//绑定
		for(int i=0;i<num;i++){
			int seat=(int) (Math.random()*1000);
			ranklist.add(seat);
			roomusermap.put(seat, LabUtils.FindRoom(chater.getRoomId()).getUserlist().get(i));
		}
		//排序
		for(int i=0;i<ranklist.size();i++){
			for(int j=0;j<i;j++){
				if(ranklist.get(i)>ranklist.get(j)){
					int temp=ranklist.get(i);
					ranklist.set(i, ranklist.get(j));
					ranklist.set(j, temp);
				}
			}
		}
		//进行编号
		String rankinformation=1+":"+roomusermap.get(ranklist.get(0)).getNickname();

		for(int i=1;i<num;i++){
			String nickname = roomusermap.get(ranklist.get(i)).getNickname();
			if(nickname!=null&&!nickname.equals("")){
				rankinformation=rankinformation+'\n'+(i+1)+":"+roomusermap.get(ranklist.get(i)).getNickname();
			}
			else{
				rankinformation=rankinformation+'\n'+(i+1)+":"+roomusermap.get(ranklist.get(i)).getUserId();
			}
			roomusermap.get(ranklist.get(i)).setSeat(i);
		}
		Map<String,String> object = new HashMap<>();
		object.put("rank", rankinformation);

		Chater chater2 = new Chater();
		chater2.setOrder("rank");
		chater2.setMessage(CheckHost(chater.getRoomId(), chater.getUserId()));
		chater2.setUserId(chater.getUserId());
		chater2.setRoomId(chater.getRoomId());
		chater2.setObject(object);

		SendAll(chater2, chater.getRoomId());
	}

	private void handleEnsureIntroduce(IoSession iossession, Chater chater) {
		//自我介绍内容通过chater.object传送
		Map<String,Object> obj = new HashMap<>();
		obj=(Map<String, Object>) chater.getObject();
		String NickName=(String) obj.get("introduction");
		LabUtils.FindRoomUser(chater.getRoomId(),chater.getUserId()).setNickname(NickName);
		Date now =new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			now=format.parse(chater.getDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		//判断是否全部人的昵称都已接收,当全部人的昵称都已接收或者过30秒后才群发
		Room room = LabUtils.FindRoom(chater.getRoomId());
		room.getRoomutils().setCount(room.getRoomutils().getCount()-1);

		String message="1:"+room.getUserlist().get(0).getNickname();
		for(int j=1;j<room.getRoomnum();j++){
			message=message+'\n'+(j+1)+":"+room.getUserlist().get(j).getNickname();
		}
		Map<String,String> object=new HashMap<>();
		object.put("introduction", message);
		Chater chater2=new Chater();
		chater2.setMessage("SUCCEED");
		chater2.setObject(object);
		chater2.setOrder("ensure_introduce");
		chater2.setUserId(room.getHostId());
		chater2.setRoomId(room.getRoomId());

		if(room.getRoomutils().getCount()==0){
			SendAll(chater2, chater.getRoomId());
		}
		else if(now.getTime()-room.getRoomutils().getIntroducestart().getTime()>30000){
			SendAll(chater2, chater.getRoomId());
		}
//		//chater新建？
//		SendSingle(chater2, iossession);
	}

	private void handleIntroduce(IoSession iossession, Chater chater) {
		//房主发送自我介绍指令，全部房员进行自我介绍

		//房间号通过chater.roomId传送
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			LabUtils.FindRoom(chater.getRoomId()).getRoomutils().setIntroducestart(format.parse(chater.getDate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		LabUtils.FindRoom(chater.getRoomId()).getRoomutils().setCount(LabUtils.FindRoom(chater.getRoomId()).getRoomnum());
		String roomId=chater.getRoomId();
		//设定返回值
		Chater chater2= new Chater();
		chater2.setOrder("introduce");
		chater2.setRoomId(roomId);
		chater2.setUserId(chater.getUserId());
//		chater2.setMessage(CheckHost(roomId, chater.getUserId()));
		chater2.setMessage("SUCCEED");
		SendAll(chater2,roomId);
	}

	private void handleIn(IoSession iossession, Chater chater) {
		//先设定该房员属性
		RoomUser roomuser = new RoomUser();
		roomuser.setSession(iossession);
		roomuser.setUserId(chater.getUserId());
		//找到此房间，并将此房员加入列表
		//设定返回的chater2
		Chater chater2= new Chater();
		//此时利用chater中的obj传送roomId
		Map<String,Object> obj = new HashMap<>();
		obj=(Map<String, Object>)chater.getObject();
		String roomId=(String) obj.get("roomId");
		Room room =LabUtils.FindRoom(roomId);

		Chater chater3=new Chater();
		chater3.setMessage(chater.getUserId()+"已经进入房间");
		chater3.setOrder("talk_in");
		chater3.setUserId(chater.getUserId());
		chater3.setRoomId(roomId);

		if(room==null){
			chater2.setMessage("No Room");
		}
		else{
			if(LabUtils.FindRoomUser(roomId, chater.getUserId())!=null){
				chater2.setMessage("Already In");
			}
			//无错误
			chater2.setMessage("SUCCEED");
			room.getUserlist().add(roomuser);
			room.setUserlist(room.getUserlist());
			room.setRoomnum(room.getRoomnum()+1);
			roomuser.setNickname("考拉"+room.getRoomnum());

			System.out.println(room.getRoomnum());

			SendAll(chater3,roomId);
		}
		Map<String,Object> object=new HashMap<>();
		object.put("roomName", room.getRoomname());
		chater2.setObject(object);
		chater2.setOrder("in");
		chater2.setRoomId(roomId);
		chater2.setUserId(chater.getUserId());

		SendSingle(chater2,iossession);
		SendSingle(chater3,iossession);
	}

	private void handleCreate(IoSession iossession, Chater chater) {
		//此时房间名通过查Chater的obj传送

		//创建房间时是否要检查其在其他房间（多次创建）

		Map<String,Object> obj = new HashMap<>();
		obj=(Map<String, Object>)chater.getObject();
		String roomname=(String) obj.get("roomName");
		RoomUser roomuser = new RoomUser();
		Room room = new Room();

		String suf= String.valueOf((int)(Math.random()*1000));//通过当前时间产生一个随机数作为后缀
		SessionFactory sessionFactory =  HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		int pre = (int) session.createQuery("select id from User where userId=:userId")
				.setParameter("userId", chater.getUserId())
				.uniqueResult();

		session.getTransaction().commit();

		//设定room的属性
		room.setRoomname(roomname);
		System.out.println(roomname+"设置");
		room.setRoomId(pre+suf);
		Date date = new Date();
		room.setStarttime(date);
		room.setRoomnum(1);
		room.setHostId(chater.getUserId());

		//设定创建人属性
		roomuser.setSession(iossession);
		roomuser.setUserId(chater.getUserId());
		roomuser.setNickname("考拉"+room.getRoomnum());
		room.getUserlist().add(roomuser);
		lab.setList(room);

		//设定返回的chater2
		Chater chater2= new Chater();
		chater2.setObject(obj);
		chater2.setRoomId(room.getRoomId());
		chater2.setUserId(chater.getUserId());
		chater2.setMessage("SUCCEED");
		chater2.setOrder("create");
		SendSingle(chater2,iossession);
	}

	private void SendSingle(Chater chater,IoSession iossession){
		String msg = new Gson().toJson(chater);
		iossession.write(msg);
	}

	private void SendAll(Chater chater,String roomId){
		String msg = new Gson().toJson(chater);
		System.out.println("SendAll");
		Room room=LabUtils.FindRoom(roomId);
		for(int j=0;j<room.getRoomnum();j++){
			room.getUserlist().get(j).getSession().write(msg);
		}
	}

	private String CheckHost(String roomId,String userId){
		if(LabUtils.FindRoom(roomId).getHostId().equals(userId)){
			return "SUCCEED";
		}
		return "Not Host";
	}

	@Override
	public void messageSent(IoSession arg0, Object arg1) throws Exception {
		System.out.println(arg1+"sent");
	}

	@Override
	public void sessionClosed(IoSession ioSession) throws Exception {
		ioSession.close(true);
	}

	@Override
	public void sessionCreated(IoSession arg0) throws Exception {
		System.out.println("faieo");
	}

	@Override
	public void sessionIdle(IoSession arg0, IdleStatus arg1) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionOpened(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub

	}


}