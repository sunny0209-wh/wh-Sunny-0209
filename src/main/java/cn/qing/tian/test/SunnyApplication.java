package cn.qing.tian.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.qing.tian.test.mapper")
public class SunnyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SunnyApplication.class, args);
    }

}
