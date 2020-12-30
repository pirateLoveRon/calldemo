package com.demo.calldemo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class DruidConfig {

    private static final Logger logger = LoggerFactory.getLogger(DruidConfig.class);

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.druid.initial-size}")
    private Integer initialSize;

    @Value("${spring.datasource.druid.min-idle}")
    private Integer minIdle;

    @Value("${spring.datasource.druid.max-active}")
    private Integer maxActive;

    @Value("${spring.datasource.druid.max-wait}")
    private Integer maxWait;

    @Value("${spring.datasource.druid.time-between-eviction-runs-millis}")
    private Integer timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.druid.min-evictable-idle-time-millis}")
    private Integer minEvictableIdleTimeMillis;

    @Value("${spring.datasource.druid.validation-query}")
    private String validationQuery;

    @Value("${spring.datasource.druid.test-while-idle}")
    private Boolean testWhileIdle;

    @Value("${spring.datasource.druid.test-on-borrow}")
    private Boolean testOnBorrow;

    @Value("${spring.datasource.druid.test-on-return}")
    private Boolean testOnReturn;

    @Value("${spring.datasource.druid.filters}")
    private String filters;

    @Value("${spring.datasource.druid.filter.stat.log-slow-sql}")
    private String logSlowSql;

    @Bean
    public ServletRegistrationBean<StatViewServlet> druidServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>();
        bean.setServlet(new StatViewServlet());
        bean.addUrlMappings("/druid/*");
        Map<String, String> param = new HashMap<>();
        param.put("loginUsername", "zwmin");
        param.put("loginPassword", "12345");
        param.put("logSlowSql", logSlowSql);
        bean.setInitParameters(param);

        return bean;
    }

    @Bean
    public FilterRegistrationBean<WebStatFilter> filterRegistrationBean() {
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        List<String> urls = new ArrayList<>();
        urls.add("/*");
        bean.setUrlPatterns(urls);
        Map<String, String> param = new HashMap<>();
        param.put("exclutions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        param.put("profileEnable", "true");
        bean.setInitParameters(param);
        return bean;
    }

    @Bean
    public DataSource druidDataSource() {
        DruidDataSource source = new DruidDataSource();
        source.setUrl(dbUrl);
        source.setUsername(username);
        source.setPassword(password);
        source.setDriverClassName(driverClassName);
        source.setInitialSize(initialSize);
        source.setMinIdle(minIdle);
        source.setMaxActive(maxActive);
        source.setMaxWait(maxWait);
        source.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        source.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        source.setValidationQuery(validationQuery);
        source.setTestWhileIdle(testWhileIdle);
        source.setTestOnBorrow(testOnBorrow);
        source.setTestOnReturn(testOnReturn);
        try {
            source.setFilters(filters);
        } catch (Exception e) {
            logger.error("DRUID配置过滤器失败！报错：" + e.getMessage(), e);
        }
        return source;
    }

}
