package com.heima.wemedia.config;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {


    @Bean
    DirectExchange orderDirect() {
        return ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_ORDER_CANCEL.getExchange())
                .durable(true)
                .build();
    }
    @Bean
    DirectExchange orderTtlDirect() {
        return ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange())
                .durable(true)
                .build();
    }




    @Bean
    public Queue orderCancelQueue() {
        return new Queue(QueueEnum.QUEUE_ORDER_CANCEL.getName());
    }

    /** 死信相关的信息应该常量定义的。。。。这里直接写了 x-dead-letter-exchange  ，key
     *
     * QUEUE_TTL_ORDER_CANCEL 消息过期后，成为死信进入死信队列
     * ttl队列绑了死信交换器
     * @return
     */
    @Bean
    public Queue orderTtlQueue() {
        return QueueBuilder
                .durable(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getName())
                .withArgument("x-dead-letter-exchange", QueueEnum.QUEUE_ORDER_CANCEL.getExchange())
                .withArgument("x-dead-letter-routing-key", QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey())
                .build();
    }


    /**
       以下配置：
           2对交换器和队列的绑定（死信和ttl队列）
     */



    @Bean
    Binding orderBinding(@Autowired DirectExchange orderDirect, @Autowired Queue orderCancelQueue) {
        return BindingBuilder
                .bind(orderCancelQueue)
                .to(orderDirect)
                .with(QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey());
    }
    @Bean
    Binding orderTtlBinding(@Autowired DirectExchange orderTtlDirect, @Autowired Queue orderTtlQueue) {
        return BindingBuilder
                .bind(orderTtlQueue)
                .to(orderTtlDirect)
                .with(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey());
    }
}
