package webSource.rabbit.forgetful.service.configure;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import rabbit.server_login.MyMessageContainer;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/2/10
 * @description
 */
@Configuration
@Import(Connection.class)
public class ReceiveConfig {

    private String filePath="D:\\upload\\";

    public enum EnumTest {
        KEY, KEY1;
    }

    @Autowired
    MyMessageContainer myMessageContainer;

    @Autowired
    ConnectionFactory connectionFactory;

    public static final String EXCHANGE_FANOUT   = "spring-fanout";
    public static final String EXCHANGE_DIRECT   = "spring-direct";
    public static final String EXCHANGE_TOPIC   = "spring-topic";
    public static final String EXCHANGE_RPC  ="rpc";
    public static final String KEY = "key";
    public static final String TOPIC_KEY="topic.#";
    public static final String TOPIC_SEND="topic.M*#";

    public static final String KEY_RPC="ping";


    /**
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     HeadersExchange ：通过添加属性key-value匹配
     DirectExchange:按照routingkey分发到指定队列
     TopicExchange:多关键字匹配
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_FANOUT);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_DIRECT);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE_TOPIC);
    }

    @Bean
    public Queue queue() {
        return new Queue("spring-queue", true); //队列持久

    }

    @Bean
    public Binding fanoutBinding() {
        return BindingBuilder.bind(queue()).to(fanoutExchange());
    }

    @Bean
    public Binding directBinding() {
        return BindingBuilder.bind(queue()).to(directExchange()).with(KEY);
    }

    @Bean
    public Binding topicBinding() {
        return BindingBuilder.bind(queue()).to(topicExchange()).with(TOPIC_KEY);
    }


//    @Bean
//    public SimpleMessageListenerContainer messageContainer() {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
//        container.setQueues(queue());
//        container.setExposeListenerChannel(true);
//        container.setMaxConcurrentConsumers(8);
//        container.setConcurrentConsumers(8);
//        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
//        container.setMessageListener(new MyMessageContainer());
//        return container;
//    }


    @Bean
    public SimpleMessageListenerContainer messageContainer() {
        MessageListenerContainer container = new MessageListenerContainer(connectionFactory);
        container.setQueues(queue());
        container.setListener(myMessageContainer);
        return container;
    }

    @Bean
    public Queue queueRpc() {
        return new Queue("ping", true); //队列持久
    }



    @Bean
    public DirectExchange directExchangeRpc() {
        return new DirectExchange(EXCHANGE_RPC,true,false);
    }

    @Bean
    public Binding directBindingRpc() {
        return BindingBuilder.bind(queueRpc()).to(directExchangeRpc()).with(KEY_RPC);
    }



//    @Bean
//    public SimpleMessageListenerContainer messageContainerRpc() {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
//        container.setQueues(queue());
//        container.setExposeListenerChannel(true);
//        container.setMaxConcurrentConsumers(8);
//        container.setConcurrentConsumers(8);
//        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
//        container.setMessageListener(new ChannelAwareMessageListener(){
//            @Override
//            public void onMessage(Message message, Channel channel) throws Exception {
//                byte[] body = message.getBody();
//                MessageTemplate rec_message= JSON.parseObject(new String(body),MessageTemplate.class);
//                System.out.println("received message: "+JSON.toJSONString(rec_message.getMessageTemplate().getContent()));
////                channel.basicPublish("",reply_to.get("reply_to").toString(),null,"Pong".getBytes());
//                FileMessageTemplate fileMessage=rec_message.getMessageTemplate();
//                if(fileMessage!=null){
//                    BufferedOutputStream stream =
//                            new BufferedOutputStream(new FileOutputStream(new File(filePath+fileMessage.getFileName())));
//                    stream.write(fileMessage.getContent().getBytes("UTF-8"));
//                    stream.close();
//                }
//                channel.basicAck(message.getMessageProperties().getDeliveryTag(), true); //确认消息成功消费
//            }
//        });
//        return container;
//    }




}