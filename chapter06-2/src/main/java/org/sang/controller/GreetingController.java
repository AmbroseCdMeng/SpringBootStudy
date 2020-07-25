package org.sang.controller;

import org.sang.bean.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class GreetingController {

    /**
     * 群发消息，与之前相同，依然用 @SendTo 实现
     */
    // 用来接收 /app/hello 路径发送来的消息，在注解方法中对消息进行处理后，再将消息转发到 @SendTo 定义的路径上
    @MessageMapping("/hello")
    // @SendTo 路径是一个前缀为 /topic 的路径，因此该消息将被交给消息代理 broker，再由 broker 进行广播
    @SendTo("/topic/greetings")
    public Message greeting(Message message) throws Exception{
        return message;
    }


    /**
     * 点对点发送消息则使用 SimpMessagingTemplate 来实现
     */
    @Autowired
    SimpMessagingTemplate messagingTemplate;
    // 表示处理来自 /app/chat 的消息
    @MessageMapping("/chat")
    // 第一个参数 Principal 用来获取当前登录用户的信息，第二个参数为客户端发送来的消息
    public void chat(Principal principal, Chat chat){
        // 获取当前用户的用户名
        String from =  principal.getName();
        // 设置给 chat 对象的 from 属性
        chat.setFrom(from);
        // 将消息发送出去，发送目标就是 chat 对象的 to 属性。
        // 该方法内部调用了 convertAndSend 方法，并对消息进行了处理
        messagingTemplate.convertAndSendToUser(chat.getTo(), "/queue/chat", chat);
    }
}
