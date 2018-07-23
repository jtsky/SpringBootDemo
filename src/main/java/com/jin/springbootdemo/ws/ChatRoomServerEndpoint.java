package com.jin.springbootdemo.ws;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jin.springbootdemo.util.WebSocketUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


@RestController
@ServerEndpoint("/chat-room/{username}")
public class ChatRoomServerEndpoint {
    private static final Logger log = LoggerFactory.getLogger(ChatRoomServerEndpoint.class);

    //onopen 建立 WebSocket 连接时触发。
    @OnOpen
    public void openSession(@PathParam("username") String username, Session session) {
        WebSocketUtils.LIVING_SESSIONS_CACHE.put(username, session);
        String message = "欢迎用户[" + username + "]来到聊天室!";
        log.info(message);
        WebSocketUtils.sendMessageAll(message);
    }


    //message 客户端监听服务端事件，当服务端向客户端推送消息时会被监听到。
    @OnMessage
    public void onMessage(@PathParam("username") String username, String message) {
        log.info(message);
        JSONObject object = JSON.parseObject(message);
        if(object.getString("type").equals("single")){
            WebSocketUtils.sendMessage(WebSocketUtils.LIVING_SESSIONS_CACHE.get(object.getString("receive")),"[" + object.getString("sender") + "]" + "->[" + object.getString("receive") + "]:" + object.getString("message"));
        }else {
            WebSocketUtils.sendMessageAll("用户[" + object.getString("message") + "]:" + message);
        }

    }

    //close 关闭 WebSocket 连接时触发。
    @OnClose
    public void onClose(@PathParam("username") String username, Session session) {
        //移除当前session
        WebSocketUtils.LIVING_SESSIONS_CACHE.remove(session);
        WebSocketUtils.sendMessageAll("用户[" + username + "] 已经离开聊天室了！");

        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //error WebSocket 发生错误时触发。
    @OnError
    public void onError(Session session, Throwable throwable) {
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        throwable.printStackTrace();
    }

    @GetMapping("chat-room/{sender}/to/{receive}")
    public void onMessage(@PathVariable("sender") String sender, @PathVariable("receive") String receive, String message) {
        WebSocketUtils.sendMessage(WebSocketUtils.LIVING_SESSIONS_CACHE.get(receive), "[" + sender + "]" + "->[" + receive + "]:" + message);
    }
}
