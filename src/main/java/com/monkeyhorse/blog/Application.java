package com.monkeyhorse.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 *  @author uoor<i@monkeyhorse.cn thx raysmood>
 */
@SpringBootApplication
@EnableCaching
public class Application {

    public static void main(String[] args) {
        System.out.println("会执行Application??");
        SpringApplication.run(Application.class, args);
    }
}
