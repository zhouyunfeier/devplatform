package com.devplatform;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.devplatform.mapper")
public class DevplatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevplatformApplication.class, args);
    }

}
