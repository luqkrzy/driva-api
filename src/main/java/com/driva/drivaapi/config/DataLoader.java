package com.driva.drivaapi.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DataLoader {
   
   @Bean
   @Profile("test")
   InitializingBean sendDatabase() {
	  return () -> {
		 System.out.println("LOADING SOME DATA!!!!!!!!!!!!!!!!!!!!!!!!!!");
	  };
   }
}
