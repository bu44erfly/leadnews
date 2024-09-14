package com.heima.wemedia.config;


import com.heima.model.schedule.dtos.Task;
import com.heima.model.wemedia.pojos.WmNews;
import com.heima.utils.common.ProtostuffUtil;
import com.heima.wemedia.service.WmNewsAutoScanService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "wmNews.order.dead")
public class TaskConsumer {

    @Autowired
    private WmNewsAutoScanService scanService;
    @RabbitHandler
    public void handle(Task task){
        //反序列化
        WmNews wmNews = ProtostuffUtil.deserialize(task.getParameters(), WmNews.class);

        scanService.autoScanWmNews(wmNews.getId());
        System.out.println("-----审核完成------ " + task.toString());
    }
}
