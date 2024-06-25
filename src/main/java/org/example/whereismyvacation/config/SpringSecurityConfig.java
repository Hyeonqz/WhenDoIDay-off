package org.example.whereismyvacation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
//@EnableWebSecurity
public class SpringSecurityConfig {

	// 일단 다 허용시키고 나중에 수정하기
/*	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests()
			.anyRequest()


		;
		return http.build();
	}*/

/*	@Bean
	public UserDetailsService userDetailsService(UserDetails userDetails) {
		UserDetails user = User.withUserDetails(userDetails)
			.username("user")
			.password("password")
			.roles("USER")
			.build();
		return new InMemoryUserDetailsManager(user);
	}*/
}
