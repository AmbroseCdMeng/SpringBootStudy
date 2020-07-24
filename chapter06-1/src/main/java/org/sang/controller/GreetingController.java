package org.sang.controller;

import org.sang.bean.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {
    // 用来接收 /app/hello 路径发送来的消息，在注解方法中对消息进行处理后，再将消息转发到 @SendTo 定义的路径上
    @MessageMapping("/hello")
    // @SendTo 路径是一个前缀为 /topic 的路径，因此该消息将被交给消息代理 broker，再由 broker 进行广播
    @SendTo("/topic/greetings")
    public Message greeting(Message message) throws Exception{
        return message;
    }
}
