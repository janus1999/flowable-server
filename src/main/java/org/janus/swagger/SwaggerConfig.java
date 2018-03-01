package org.janus.swagger;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.google.common.collect.Lists;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig{

	
    ApiInfo apiInfo() {
    	return new ApiInfoBuilder()
    			.title("demo")
    			.description("demo")
    			.termsOfServiceUrl("")
    			.version("1.0.0")
    			.contact(new Contact("janus1999", "www.janus1999.com", "janus1999@gmail.com"))
    			.build();
    }
    
    @Bean
	public Docket createRestApi(){
        ParameterBuilder builder = new ParameterBuilder();
        Parameter parameter = builder
                // 从cookie中获取token
                .parameterType("header") //参数类型支持header, cookie, body, query etc
                .name("x-token") //参数名
                .defaultValue("") //默认值
                .description("请输入token")
                .modelRef(new ModelRef("string")) //指定参数值的类型
                .required(false).build(); //非必需，这里是全局配置，然而在登陆的时候是不用验证的
        List<Parameter> parameters = Lists.newArrayList(parameter);
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(this.apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("org.janus"))
				.paths(PathSelectors.any())
				.build()
				.globalOperationParameters(parameters);
	}
}
