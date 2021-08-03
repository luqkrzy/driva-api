package com.driva.drivaapi.security;

import com.driva.drivaapi.security.jwt.AuthTokenFilter;
import com.driva.drivaapi.security.jwt.JwtAccessDeniedHandler;
import com.driva.drivaapi.security.jwt.JwtAuthenticationEntryPoint;
import com.driva.drivaapi.security.jwt.JwtUtils;
import com.driva.drivaapi.security.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
   
   private static final String[] PUBLIC_URLS = {
		   "/api/auth/**",
		   "/v3/api-docs/**",
		   "/v2/api-docs",
		   "/swagger-resources/**",
		   "/swagger-ui/**",
		   "/webjars/**"};
   private final UserDetailsServiceImpl userDetailsService;
   private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
   private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
   private final JwtUtils jwtUtils;
   
   @Bean
   public AuthTokenFilter authenticationJwtTokenFilter() {
	  return new AuthTokenFilter(jwtUtils, userDetailsService);
   }
   
   @Override
   public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
	  authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
   }
   
   @Bean
   @Override
   public AuthenticationManager authenticationManagerBean() throws Exception {
	  return super.authenticationManagerBean();
   }
   
   @Bean
   public PasswordEncoder passwordEncoder() {
	  return new BCryptPasswordEncoder();
   }
   
   @Override
   protected void configure(HttpSecurity http) throws Exception {
	  http.csrf().disable().cors().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		  .and().authorizeRequests().antMatchers(PUBLIC_URLS).permitAll().anyRequest().authenticated().and()
		  .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
		  .accessDeniedHandler(jwtAccessDeniedHandler).and()
		  .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
   }
}
