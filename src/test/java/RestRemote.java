import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import webSource.Example;
import webSource.jms.ReceiveMessage;
import webSource.jms.SendMessage;
import webSource.jpa.entry.User;
import webSource.remoterest.RestBean;

/**
 * Created by Administrator on 2016/12/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Example.class)
public class RestRemote {

    @Autowired
    RestBean restBean;

    @Autowired
    RestTemplateBuilder restTemplateBuilder;



    /**
     * 基本测试通过但是配置测试未通过ssssfsfasffsdfasdfasdfallll
     * 测试失败按逻辑上来说会取消连接的创建但是然并卵代码为RestBean 27 ; ProxyCustomizer 35
     */
    @Test
    public void test(){
        User user=restBean.someRestCall(1l);
        System.out.println(user.getSex());
        User user1=restTemplateBuilder.build().getForObject("http://localhost:8081", User.class, 1l);
        System.out.println(user1.getSex());
    }


}
