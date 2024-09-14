package com.heima.wemedia.config;


import lombok.Getter;

@Getter
public enum QueueEnum {
    /**
     * 消息通知队列 /死信
     */
    QUEUE_ORDER_CANCEL("wmNews.order.direct", "wmNews.order.dead", "wmNews.order.dead"),
    /**
     * 消息通知ttl队列
     */
    QUEUE_TTL_ORDER_CANCEL("wmNews.order.direct.ttl", "wmNews.order.cancel.ttl", "wmNews.order.cancel.ttl");

    /**
     * ttl ms
     */
    public static final Long default_ttl = 5000L ;

    /**
     * 交换名称
     */
    private String exchange;
    /**
     * 队列名称
     */
    private String name;
    /**
     * 路由键
     */
    private String routeKey;

    QueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }
}
