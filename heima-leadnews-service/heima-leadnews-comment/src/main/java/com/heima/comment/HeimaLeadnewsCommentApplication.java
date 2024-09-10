package com.heima.comment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.heima.comment.feign")  // 确保包路径正确
public class HeimaLeadnewsCommentApplication {
    public static void main(String[] args) {
        SpringApplication.run(HeimaLeadnewsCommentApplication.class, args);
    }
}
