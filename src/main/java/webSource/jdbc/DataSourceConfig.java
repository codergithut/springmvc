package webSource.jdbc;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by Administrator on 2016/12/7.
 */
@Configuration
public class DataSourceConfig
{
    @Bean(name="FirstDataSource")
    @Primary
    @ConfigurationProperties(prefix="datasource.primary")
    public DataSource dataSource(){
        System.out.println("-------------------- FirstDataSource init ---------------------");
        return DataSourceBuilder.create().build();
    }

}







