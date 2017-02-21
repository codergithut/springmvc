package webSource.rabbit.forgetful.server_login;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import rabbit.jpa.entry.DemoInfo;
import rabbit.jpa.repository.DemoInfoRepository;
import rabbit.service.entity.FileMessageTemplate;
import rabbit.service.entity.MessageTemplate;

import java.io.*;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/2/13
 * @description
 */
@Service
@Component
public class MyMessageContainer implements ChannelAwareMessageListener {

    DemoInfoRepository demoInfoRepository;

    private String filePath="D:\\upload\\";

    @Autowired
    public MyMessageContainer(DemoInfoRepository demoInfoRepository){
        this.demoInfoRepository=demoInfoRepository;

    }

    @Override
    public void onMessage(Message message, Channel channel) throws IOException {
        byte[] body = message.getBody();
        MessageTemplate rec_message= JSON.parseObject(new String(body),MessageTemplate.class);
        System.out.println("received message: "+JSON.toJSONString(rec_message.getMessageTemplate().getContent()));
        FileMessageTemplate fileMessage=rec_message.getMessageTemplate();
        if(fileMessage!=null){
            InputStream is = new ByteArrayInputStream(fileMessage.getContent());
            OutputStream fos = new FileOutputStream(filePath+fileMessage.getFileName());

            //这里对is进行赋值，略
            //...

            // 文件输出流fos
            // openFile()为自定义函数，判断文件是否存在等（略）
            // 将输入流is写入文件输出流fos中
            int ch = 0;
            try {
                while((ch=is.read()) != -1){
                    fos.write(ch);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally{
                //关闭输入流等（略）
                fos.close();
                is.close();
            }

            if(demoInfoRepository!=null){
                DemoInfo demoInfo=new DemoInfo();
                demoInfo.setPath(filePath+fileMessage.getFileName());
                demoInfo.setName(fileMessage.getFileName());
                demoInfoRepository.save(demoInfo);
                for(DemoInfo detail:demoInfoRepository.findAll()){
                    System.out.println("数据库的操作======="+detail.getPath());
                }
            }
        }

        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true); //确认消息成功消费

    }

}
