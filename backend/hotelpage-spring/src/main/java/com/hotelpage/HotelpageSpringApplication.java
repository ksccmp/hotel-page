package com.hotelpage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hotelpage.jwt.JwtInterceptor;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableTransactionManagement
public class HotelpageSpringApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(HotelpageSpringApplication.class, args);
	}

 	private ApiInfo metadata() {
		return new ApiInfoBuilder().title("Hotel Page").description("Hotel Page를 위한 Rest API 제공").version("1.0").build();
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().apiInfo(metadata());
	}
	
	@Autowired
	private JwtInterceptor jwtInterceptor;
	
	private final String excludePath[] = {
			"/user/**", // http://localhost:9090/user/{*} 로 시작하는 모든 url은 Interceptor 적용 X
						// 보통 토큰을 생성하는 곳인 로그인과 회원가입에 관련된 곳에 적용하지 않음
			"/request/**",
			"/v2/api-docs",
			"/swagger-resources/**",
			"/swagger-ui.html/**", // swagger에도 적용 X
			"/webjars/**"
			};
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("*")
		.allowedMethods("*")
		.allowedHeaders("*")
		.exposedHeaders("jwt-auth-token");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor).addPathPatterns("/**") // 기본 적용 경로
		.excludePathPatterns(excludePath); // 적용 제외 경로
	}
}