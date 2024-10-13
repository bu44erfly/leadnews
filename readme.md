feign 用法
    这个项目的例子：
        api模块定义接口，加入feign-client注解 ("leadnews-article")
        在wemedia中依赖注入，使用的就是注册中心对应的服务
        （服务由模块article注册提供）

调用者直接依赖注入


 联想词表，redis维护
 拿到表的list<> ,加入trie，然后查询关联词