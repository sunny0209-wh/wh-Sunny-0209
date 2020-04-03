package cn.qing.tian.test.service.impl;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@ServerEndpoint("/websocket/{userId}")
//此注解相当于设置访问URL
public class WebSocket {

    private static final Logger logger = LoggerFactory.getLogger(WebSocket.class);
    //与某个客户端的连接对话，需要通过它来给客户端发送消息
    private Session session;
    //用于存所用的连接服务的客户端，这个对象储存是安全的
    private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();
    //在线人数
    private static final AtomicInteger OnlineCount = new AtomicInteger(0);
    private static Map<String,Session> sessionPool = new HashMap<String,Session>();
    private int count = 0;
    private String userId;
    @OnOpen
    public void OnOpen(Session session,@PathParam(value = "userId")String userId) {
        this.session = session;
        this.userId = userId;
        count = OnlineCount.incrementAndGet(); // 在线数加1
        webSockets.add(this);
        //userId是用来标识唯一客户端。如果需要指定发送，需要指定发送通过userId来区分
        sessionPool.put(userId,session);
        logger.info("有新的连接，当前连接人数为："+count);
    }

    @OnClose
    public void OnClose(Session session){
        this.session = session;
        webSockets.remove(this);
        count = OnlineCount.decrementAndGet();
        logger.info("有人退出，当前连接人数为："+count);
    }

    @OnMessage
    public void OnMessage(String messAge){
        logger.info("[webSocket消息] 收到消息："+messAge);
        sendAllMessage(messAge);
    }

    // 此为群发消息
    private void sendAllMessage(String messAge) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("messAge",messAge);
        map.put("userPhone",userId);
        for(WebSocket webSocket : webSockets) {
            System.out.println("【websocket消息】广播消息:"+messAge);
            try {
                webSocket.session.getAsyncRemote().sendText(JSON.toJSONString(map));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 此为单点消息
    public void sendOneMessage(String userId, String messAge) {
        System.out.println("【websocket消息】单点消息:"+messAge);
        Session session = sessionPool.get(userId);
        if (session != null) {
            try {
                session.getAsyncRemote().sendText(JSON.toJSONString(messAge));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
