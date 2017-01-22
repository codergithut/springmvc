package webSource.rtx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import webSource.jms.ReceiveMessage;
import webSource.jms.SendMessage;

import javax.annotation.Resource;
import javax.jms.JMSException;
import java.util.Map;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/1/22
 * @description
 */
@Controller
public class GreetingController {

    @RequestMapping("/helloSocket")
    public String index(){
        return "/websocket";
    }


    @MessageMapping("/change-notice")
    @SendTo("/topic/notice")
    public String greeting(String value) throws InterruptedException, JMSException {
        return value;
    }

    @RequestMapping(value="recwebsocket", method= RequestMethod.GET)
    public String recWebSocket(Map<String,Object> model) {
        return "received";
    }

}