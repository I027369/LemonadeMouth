package com.moonshot.restaurant.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;


import com.moonshot.restaurant.entity.RestaurantAccountUser;
import com.moonshot.restaurant.service.RestaurantAccountUserService;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;

@EnableResourceServer
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**",
            "/health/**"
    };
    
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		//.antMatchers("/health/**").permitAll()
		.antMatchers(AUTH_WHITELIST).permitAll()
		.antMatchers(HttpMethod.POST, "/accountusers").permitAll()
		.antMatchers(HttpMethod.POST, "/appusers").permitAll()
		.anyRequest().authenticated();
		
		//.antMatchers("/health").permitAll()
		//http.authorizeRequests().antMatchers("/**").permitAll();
		//http.authorizeRequests().anyRequest().authenticated();
	}
	
	
	
	/*
	 * this code is for Swagger UI
	 * -----------Start-------------
	 */
	
	
	 @Bean
	    public SecurityConfiguration security() {
	        return SecurityConfigurationBuilder.builder()
	                .clientId("my-trusted-client")
	                .clientSecret("secret")
	                .realm("realm")
	                .scopeSeparator(",")
	                .additionalQueryStringParams(null)
	                .useBasicAuthenticationWithAccessCodeGrant(false)
	                .build();
	    }
	
	 	private ApiKey apiKey() {
	        return new ApiKey("apiKey", "Authorization", "header");
	    }
	    
	 	private SecurityContext securityContext() {
	        return SecurityContext.builder()
	                .securityReferences(defaultAuth())
	                .forPaths(PathSelectors.regex("/api.*"))
	                .build();
	    }
	    private List<SecurityReference> defaultAuth() {
	        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
	        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	        authorizationScopes[0] = authorizationScope;
	        return Arrays.asList(new SecurityReference("apiKey", authorizationScopes));
	    }
	    
		/*
		 * this code is for Swagger UI
		 * -----------End-------------
		 */ 

}
