package com.douzone.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.douzone.config.web.MVCConfig;
import com.douzone.config.web.MessagesConfig;
import com.douzone.config.web.SecurityConfig;

@Configuration
@ComponentScan("com.douzone.mysite.controller")//자동 설정 사용
@Import(value= {MVCConfig.class,SecurityConfig.class,MessagesConfig.class})
public class WebConfig {
	//여기서 하는건 명시적 설정
	
}
