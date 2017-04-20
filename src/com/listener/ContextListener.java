package com.listener;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.beans.Lab;
import com.beans.Room;
import com.beans.RoomUser;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.handler.UserHandler;
import com.utils.HibernateUtil;

@WebListener
public class ContextListener implements ServletContextListener {
	private NioSocketAcceptor acceptor = null;
    /**
     * Default constructor. 
     */
    public ContextListener() {

    }

    public void contextDestroyed(ServletContextEvent arg0)  { 

		HibernateUtil.getSessionFactory().close();
		acceptor.dispose();
    }
    public void contextInitialized(ServletContextEvent arg0)  {
    	acceptor=new NioSocketAcceptor();
    	acceptor.setReuseAddress(true);
    	acceptor.getSessionConfig().setReadBufferSize(2048);
    	acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
    	acceptor.getFilterChain().addLast("logger", new LoggingFilter());
    	acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("utf-8"))));
    	acceptor.setHandler(new UserHandler());
    	
    	try {
			acceptor.bind(new InetSocketAddress(8999));
		} catch (IOException e) {
			e.printStackTrace();
		}
		TestLab();
    }

	private void TestLab() {
		Lab lab=Lab.getLab();
		Room room=new Room();
		room.setRoomId("111");
		room.setRoomname("test");
		room.setRoomnum(1);
		room.setHostId("123");
		RoomUser roomuser=new RoomUser();
		roomuser.setUserId("123");
		roomuser.setNickname("nick");
		List<RoomUser> list=new ArrayList<>();
		list.add(roomuser);
		room.setUserlist(list);
		lab.setList(room);
	}

}
