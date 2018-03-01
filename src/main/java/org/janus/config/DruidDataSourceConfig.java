package org.janus.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(DruidDataSource.class)
@ConditionalOnProperty(
        name = "spring.datasource.type",
        havingValue = "com.alibaba.druid.pool.DruidDataSource",
        matchIfMissing = true
)
@EnableConfigurationProperties(DruidDataSourceProperties.class)
public class DruidDataSourceConfig {
    @Bean
    public DruidDataSource dataSource(DataSourceProperties dataSourceProperties, DruidDataSourceProperties druidDataSourceProperties) {
        DruidDataSource druidDataSource = (DruidDataSource) dataSourceProperties.initializeDataSourceBuilder().type(DruidDataSource.class).build();
        druidDataSource.configFromPropety(druidDataSourceProperties.toProperties());
        druidDataSource.setInitialSize(druidDataSourceProperties.getInitialSize());
        druidDataSource.setMinIdle(druidDataSourceProperties.getMinIdle());
        druidDataSource.setMaxActive(druidDataSourceProperties.getMaxActive());
        druidDataSource.setMaxWait(druidDataSourceProperties.getMaxWait());
        druidDataSource.setConnectProperties(druidDataSourceProperties.getConnectionProperties());
        return druidDataSource;
    }
    
    @Bean
    public ServletRegistrationBean druidStatViewServlet(DruidDataSourceProperties druidDataSourceProperties) {
    	ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();  
        servletRegistrationBean.setServlet(new StatViewServlet());  
        servletRegistrationBean.addUrlMappings("/druid/*");  
        Map<String, String> initParameters = new HashMap<String, String>();  
        // initParameters.put("loginUsername", "druid");// 用户名  
        // initParameters.put("loginPassword", "druid");// 密码  
        initParameters.put("resetEnable", "false");// 禁用HTML页面上的“Reset All”功能  
        initParameters.put("allow", "127.0.0.1"); // IP白名单 (没有配置或者为空，则允许所有访问)  
        // initParameters.put("deny", "192.168.20.38");// IP黑名单  
        // (存在共同时，deny优先于allow)  
        servletRegistrationBean.setInitParameters(initParameters);  
        return servletRegistrationBean;      
     }
    
    @Bean
    public FilterRegistrationBean druidWebStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid2/*");
        return filterRegistrationBean;
    }
}