package org.example.whereismyvacation.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private List<String> SWAGGER = List.of(
		"/swagger-ui.html",
		"/swagger-ui/**"
	);

	@Override
	public void addInterceptors (InterceptorRegistry registry) {
	/*	registry.addInterceptor()
			.excludePathPatterns(SWAGGER);*/
	}

}
