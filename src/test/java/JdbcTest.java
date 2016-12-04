import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import webSource.Example;
import webSource.jpa.entry.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/1.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Example.class)
public class JdbcTest {

    static String TEST_SQL="select id,name,sex from users where id = 1";

    static String TEST_SQL1="select id from users where id = 1";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void test(){
        List<Object> data=new ArrayList<Object>();
        Object user=jdbcTemplate.queryForList(TEST_SQL);

//         这段代码有误待研究 现在可以获取某一列的值
        String s=jdbcTemplate.queryForObject(TEST_SQL1,String.class);

//        这段代码有误待研究 现在可以获取某一列的很多值
        List<String> t=jdbcTemplate.queryForList(TEST_SQL1,String.class);

        //用基本的代码封装
        User lamtestUser=jdbcTemplate.queryForObject(TEST_SQL,(rs, rowNum)->{
                    return new User(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getString("sex"));
                }
        );
    }
}
