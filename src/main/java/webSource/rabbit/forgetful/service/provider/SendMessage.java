package webSource.rabbit.forgetful.service.provider;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rabbit.service.configure.ReceiveConfig;

import java.util.UUID;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/2/10
 * @description
 */

/**
 * 消息生产者
 *
 * @author liaokailin
 * @version $Id: Send.java, v 0.1 2015年11月01日 下午4:22:25 liaokailin Exp $
 */
@Component
public class SendMessage implements RabbitTemplate.ConfirmCallback {

    @Autowired
    SendMessage send;

    private RabbitTemplate rabbitTemplate;

    /**
     * 构造方法注入
     */
    @Autowired
    public SendMessage(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this); //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
    }

    public void sendTopicMsg(String user) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(ReceiveConfig.EXCHANGE_TOPIC, ReceiveConfig.TOPIC_SEND, user, correlationId);
    }

    public void sendDirectMsg(String user) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(ReceiveConfig.EXCHANGE_DIRECT, ReceiveConfig.KEY, user, correlationId);
    }

    public void sendFanoutMsg(String user) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(ReceiveConfig.EXCHANGE_FANOUT, ReceiveConfig.KEY, user, correlationId);
    }

    public void sendRpcMsg(String user) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(ReceiveConfig.EXCHANGE_RPC, ReceiveConfig.KEY_RPC, user, correlationId);
    }

    public void sendRpcMsg(Object user) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(ReceiveConfig.EXCHANGE_DIRECT, ReceiveConfig.KEY, user, correlationId);
    }
    /**
     * 回调
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println(" client1  :" + correlationData);
        if (ack) {
            System.out.println("消息发送成功");
        } else {
            System.out.println("消息发送失败:" + cause);
        }
    }
}