import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import webSource.Example;
import webSource.jms.ReceiveJMSMessage;
import webSource.jms.SendJMSMessage;

import javax.jms.JMSException;


/**
 * Created by Administrator on 2016/12/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Example.class)
public class JMSTest {

    @Autowired
    SendJMSMessage sendJMSMessage;

    @Autowired
    ReceiveJMSMessage receiveMessage;

    @Test
    public void test() throws InterruptedException, JMSException {
        while(true){
            sendJMSMessage.sendMessageTest("this is test");
        }

    }
}
