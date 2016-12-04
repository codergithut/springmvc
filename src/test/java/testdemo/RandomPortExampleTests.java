package testdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import webSource.Example;
import webSource.jpa.entry.User;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by Administrator on 2016/12/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes=Example.class)
public class RandomPortExampleTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void exampleTest() {
        User user = this.restTemplate.getForObject("http://localhost:8081", User.class);
        assertThat(user.getId()).isEqualTo(1l);
    }

}