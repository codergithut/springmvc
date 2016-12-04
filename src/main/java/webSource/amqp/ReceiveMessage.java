//package webSource.amqp;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//import org.springframework.test.context.ContextConfiguration;
//import webSource.configuration.RabbitConfiguration;
//
///**
// * Created by Administrator on 2016/12/1.
// */
//@Configuration
//public class ReceiveMessage {
//    @RabbitListener(queues = "someQueue")
//    public void processMessage(Object content) {
//        System.out.println(content);
//    }
//
//}
