package webSource.rabbit.forgetful.login;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rabbit.service.entity.MessageTemplate;
import rabbit.service.provider.SendMessage;

import java.io.IOException;


/**
 * Created by Administrator on 2016/11/29.
 *
 *@RestController 是rest风格的入口
 *
 * testRepository 通过jpa封装的接口连接数据库
 *
 * jdbcTest 通过jdbc连接数据库
 *
 * sessionFactory 获取hibernate的sessionFactory工厂
 *
 *测试方法入口  http://localhost:8080/rest/1
 */

@Controller
@RequestMapping(value="/add")
public class AddUserController {

    @Autowired
    SendMessage MessageSend;

    @RequestMapping(method= RequestMethod.GET)
    public String AddUserGet() throws IOException {
        return "add";
    }

    @RequestMapping(method= RequestMethod.POST)
    public String AddUserPost(String password,String username) throws IOException {
        MessageTemplate message=new MessageTemplate("add","success","client");
        MessageSend.sendRpcMsg(JSON.toJSONString(message));
        return "success";
    }

}