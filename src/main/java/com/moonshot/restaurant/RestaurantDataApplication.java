package com.moonshot.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.moonshot.restaurant.entity.RestaurantAccountUser;
import com.moonshot.restaurant.service.RestaurantAccountUserService;

@SpringBootApplication
public class RestaurantDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantDataApplication.class, args);		
		
	}
	
	
	@Autowired	
	public void configureGlobal(AuthenticationManagerBuilder builder, RestaurantAccountUserService restActUserService) throws Exception{
	
		builder.userDetailsService(new UserDetailsService(){			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				// here use the RestaurantAccountUserService is used to find the user and create a new UserDetails object				
				RestaurantAccountUser restActUser = restActUserService.getRestActUserByEmail(username);
				return new User(restActUser.getEmailId(), restActUser.getPassword(), true, true, true, true,
			                AuthorityUtils.createAuthorityList("USER"));
			}
		});
	
}		


	
}

