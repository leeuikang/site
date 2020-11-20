package com.example.site.config;

import com.example.site.common.resolver.CustomMapArgumentResolver;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class ResolverConfig implements WebMvcConfigurer {

    @Bean
    public CustomMapArgumentResolver customMapArgumentResolver(){
        return new CustomMapArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolverList){
        resolverList.add(customMapArgumentResolver());
        WebMvcConfigurer.super.addArgumentResolvers(resolverList);
    }
}
