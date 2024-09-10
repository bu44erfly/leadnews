package com.heima.common.mongo;

import com.mongodb.client.MongoClients;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Data
@Configuration
@PropertySource("classpath:mongo.properties")
@ConfigurationProperties(prefix = "mongo")
public class MongoDBconfigure {

    private String host;
    private Integer port;
    private String dbname;

    @Bean
    public MongoTemplate mongoTemplate() {
        // 构建 MongoDB 的连接字符串
        String uri = "mongodb://" + host + ":" + port + "/" + dbname;

        // 使用 MongoClients 来创建 MongoDB 连接
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(MongoClients.create(uri), dbname));
    }
}
