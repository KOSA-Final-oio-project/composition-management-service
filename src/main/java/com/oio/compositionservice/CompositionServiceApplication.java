package com.oio.compositionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CompositionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompositionServiceApplication.class, args);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowCredentials(true);
//		configuration.setExposedHeaders(Arrays.asList("accessToken", "refreshToken", "Content-Type"));
		configuration.addExposedHeader("accessToken");
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setAllowedOrigins(Arrays.asList("https://www.mapbegood.site", "http://localhost:5173"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
