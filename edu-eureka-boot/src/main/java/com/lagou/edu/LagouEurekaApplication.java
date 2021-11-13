package com.lagou.edu;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author yemingjie
 */
@SpringBootApplication
@EnableEurekaServer
public class LagouEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(LagouEurekaApplication.class);
    }
}
