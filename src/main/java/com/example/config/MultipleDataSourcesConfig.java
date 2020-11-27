package com.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.example.common.DataSourceConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author yds
 * @version 1.0
 * @date 2020/11/27 20:26
 * @description:
 */
@Configuration
public class MultipleDataSourcesConfig {

    @Bean(name = "mySqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysqldb")
    public DataSource mySqlDataSource(){
        return new DruidDataSource();
    }

    @Bean(name = "oracleDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.oracledb")
    public DataSource oracleDataSource(){
        return new DruidDataSource();
    }

    @Bean
    @Primary
    public DataSource dataSourceChangeAspect(){
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDefaultTargetDataSource(mySqlDataSource());
        dataSourceConfig.setTargetDataSources(new HashMap<Object,Object>(){{
            put("mySqlDataSource",mySqlDataSource());
            put("oracleDataSource",oracleDataSource());
        }});
        return dataSourceConfig;
    }


    /**
     * 配置@Transactional注解事物
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSourceChangeAspect());
    }

    /**
     * 配置一个servlet
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        Map map = new HashMap<String,String>(){
            {
                put("loginUsername","admin");
                put("loginPassword","123456");
                put("allow","localhost");//默认允许所有访问
                put("deny","192.168.28.78");
            }
        };
        servletRegistrationBean.setInitParameters(map);
        return servletRegistrationBean;
    }

    /**
     * 配置一个web的过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean WebStatFilter(){
        FilterRegistrationBean<WebStatFilter> webStatFilterFilterRegistrationBean = new FilterRegistrationBean<>(new WebStatFilter());
        Map<String,String> map = new HashMap<String,String>(){
            {
                put("exclusions","*.js,*.css,/druid/*");
            }
        };
        webStatFilterFilterRegistrationBean.setInitParameters(map);
        webStatFilterFilterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        return webStatFilterFilterRegistrationBean;
    }
}
