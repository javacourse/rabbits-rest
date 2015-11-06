package ru.alvion.data.jpa;

import com.mangofactory.swagger.plugin.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.alvion.data.jpa.baserepos.DefaultRepositoryFactoryBean;

@SpringBootApplication(scanBasePackages = "ru.alvion.data.jpa")
@EnableJpaRepositories(repositoryFactoryBeanClass = DefaultRepositoryFactoryBean.class,
        basePackages = "ru.alvion.data.jpa.repository")
@EnableSwagger
public class SampleDataJpaApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleDataJpaApplication.class, args);
    }

}
