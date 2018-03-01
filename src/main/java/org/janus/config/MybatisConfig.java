package org.janus.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;

@MapperScan(basePackages="org.janus.system.dao")
@Configuration
@EnableTransactionManagement
public class MybatisConfig {

	public MybatisConfig() {
		
	}
	
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }	

}
