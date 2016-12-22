package webSource.jdbc;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by Administrator on 2016/12/7.
 */
public class DataSourceConfig
{
    @Bean
    @Primary
    @ConfigurationProperties(prefix="datasource.primary")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

}







