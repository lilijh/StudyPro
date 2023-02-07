package com.studyonline.content;

import com.spring4all.swagger.EnableSwagger2Doc;
import com.studyonline.base.exception.GlobalExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "com.studyonline")
@EnableSwagger2Doc
public class ContentApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContentApiApplication.class, args);

    }
}
