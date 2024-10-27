网关过滤器登录鉴权

service：普通的登录过程，即数据库查询用户，比对密码，签token并返回
过滤器检验token


feign 用法
    这个项目的例子：
        api模块定义接口，加入feign-client注解 ("leadnews-article")
        在wemedia中依赖注入，使用的就是注册中心对应的服务
        （服务由模块article注册提供）


 联想词表(引入了redis）
 表记录加入trie，用输入的str在trie上查询关联词


文章延迟发布 
配置rabbitmq 队列,包括死信队列
wmNews 端使用scheduleClient(feign)?? addTASK 发送消息(WmNews) ，设置好TTL
rabbitListener(消费者)监听的deadQueue, 得到wmnews id ,执行审核， 设置状态


文章异步上下架
wmNews端 传一个map(article_id, enable:0/1) , 文章端监听，修改

 
