package com.lagou.edu.ad;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.lagou.edu.ad.mapper")
public class LagouAdApplication {
    public static void main(String[] args) {
        SpringApplication.run(LagouAdApplication.class, args);
    }
}
