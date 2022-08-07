package com.codeIT.moonCalendar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class WebConfig implements WebMvcConfigurer {
    @Bean
    MappingJackson2JsonView jsonView(){
        return new MappingJackson2JsonView();
    }
}
