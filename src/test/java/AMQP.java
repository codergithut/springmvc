import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import webSource.Example;
//import webSource.amqp.ReceiveMessage;
//import webSource.amqp.SendMessage;
import webSource.jpa.entry.User;

/**
 * Created by Administrator on 2016/12/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Example.class)
public class AMQP {

//    @Autowired
//    ReceiveMessage rec;
//
//    @Autowired
//    SendMessage send;

    @Test
    public void test(){
        while(true){
            int i=0;
            if(i<1000&&i>=0){
//                send.sendMessage(new User((long)i++,"ss","ssss"));
            }else{
                i-=1000;
            }
        }

    }

}
