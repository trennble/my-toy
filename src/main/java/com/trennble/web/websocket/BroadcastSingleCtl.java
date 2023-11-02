package com.trennble.web.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class BroadcastSingleCtl {
    private static final Logger logger = LoggerFactory.getLogger(BroadcastSingleCtl.class);

    // 收到消息记数
    private AtomicInteger count = new AtomicInteger(0);


    @Autowired
    ApplicationContext context;

    // @MessageMapping 指定要接收消息的地址，类似@RequestMapping。除了注解到方法上，也可以注解到类上
    // 不能使用类似/{path-variable}的方式传递参数
    @MessageMapping("/receive-single/")
    /**
     * 基于WebSocket的STOMP有个属性@SendTo。
     * @SendTo默认 消息将被发送到与传入消息相同的目的地，但是目的地前面附加前缀（默认情况下为“/topic”）。
     * 也可以使用sendToUser批注，可以将将消息定向到特定用户
     * 消息的返回值是通过{@link org.springframework.messaging.converter.MessageConverter}进行转换。
     *
     * 这里使用 @SendToUser，而不是使用 @SendTo
     */
    @SendToUser
    public String broadcast(SimpMessageHeaderAccessor sha,String requestMessage){
        logger.info("start broadcast = {}" , requestMessage);
        String responseMessage="BroadcastCtlSingle receive ["+requestMessage+
                "] total received [" + count.incrementAndGet() + "] records";

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(()->{
            logger.info("start thread");
            for (int i = 0; i < 3; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            logger.info("end thread");
        });
        logger.info("end broadcast = {}" , requestMessage);

        // 也可以使用SimpMessagingTemplate来发送消息
        // SimpMessagingTemplate template = context.getBean(SimpMessagingTemplate.class);
        // template.convertAndSendToUser(sha.getSessionId(),"/topic/getResponse",responseMessage,sha.getMessageHeaders());

        return responseMessage;
    }


    @RequestMapping(value="/broadcast-single/index")
    public String broadcastIndex(){
        return "websocket/simple/ws-broadcast-single";
    }

}
