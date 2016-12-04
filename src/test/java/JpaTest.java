import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import webSource.Example;

import webSource.jpa.repository.JpaRepositoryBean;

/**
 * Created by Administrator on 2016/12/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Example.class)
public class JpaTest {
    long id =1;

    @Autowired
    private JpaRepositoryBean userRepository;


    @Test
    public void test() throws Exception {

        assert(userRepository.findOne(id).getId()==1);

    }
}