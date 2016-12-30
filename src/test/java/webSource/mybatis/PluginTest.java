package webSource.mybatis;

import org.apache.ibatis.plugin.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2016/12/29
 * @description webSource.mybatis 分页是通过过滤器过滤出当前的页码和每页条数然后对sql进行拼接执行分页sql
 */
public class PluginTest {

    @Test
    public void mapPluginShouldInterceptGet() {
        Map map = new HashMap();
        map = (Map) new AlwaysMapPlugin().plugin(map);
        assertEquals("Always", map.get("Anything"));
    }

    @Test
    public void shouldNotInterceptToString() {
        Map map = new HashMap();
        map = (Map) new AlwaysMapPlugin().plugin(map);
        assertFalse("Always".equals(map.toString()));
    }

    @Intercepts({
            @Signature(type = Map.class, method = "get", args = {Object.class})})
    public static class AlwaysMapPlugin implements Interceptor {
        public Object intercept(Invocation invocation) throws Throwable {
            return "Always";
        }

        public Object plugin(Object target) {
            return Plugin.wrap(target, this);
        }

        public void setProperties(Properties properties) {
        }
    }

}