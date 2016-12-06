package webSource.controller.rest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import webSource.jpa.repository.JpaRepositoryBean;
import webSource.tool.GetUrlResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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

@RestController
@RequestMapping(value="/rest")
public class RestControllerDemo {
    @Autowired
    JpaRepositoryBean testRepository;

    @Autowired
    JavaMailSender mailSender;

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public List<Object> getUser(@PathVariable Long id) throws IOException {

        List<Object> users=new ArrayList<Object>();

        GetUrlResource getResource=new GetUrlResource();

        users.add(getResource.getWealthByUrlResource());

        users.add(testRepository.readUserByQueryAndCache(id));

        return users;
    }
}