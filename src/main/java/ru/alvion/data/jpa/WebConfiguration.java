package ru.alvion.data.jpa;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;


@Configuration
@EnableWebMvc
@ComponentScan("ru.alvion.data.jpa.web")
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new YamlHttpMessageConverter());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:8080")
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS");
//                .allowedHeaders("Content-Type", "api_key", "Authorization")
//                .exposedHeaders("header1", "header2")
//                .allowCredentials(false).maxAge(3600);
    }

}
