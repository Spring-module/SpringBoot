package com.wisely.ch9_1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.wisely.ch9_1.security.CustomUserService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{//1
	
	@Bean
	UserDetailsService customUserService(){ //2
		return new CustomUserService(); 
	}
	
	/**
	 * 认证需要我们有一套用户数据的来源，而授权则是对于用户有相应的角色权限。
	 * 在 Spring Security 里通过重写此方法来实现定制。
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService()); //3
		
	}
	
	/**
	 * Spring Security是通过重写此方法来实现请求拦截的
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()//通过 authorizeRequests 方法来开始请求权限配置
						.anyRequest().authenticated() //4
						.and()
						.formLogin()
							.loginPage("/login")
							.failureUrl("/login?error")
							.permitAll() //5
						.and()
						.logout().permitAll(); //6
		

	}


}
