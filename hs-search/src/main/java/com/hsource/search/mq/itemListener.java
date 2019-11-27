package com.hsource.search.mq;


import com.hsource.search.service.SearchService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class itemListener {

    @Autowired
    private SearchService searchService;

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "search.item.insert.queue", durable = "true"),
                    exchange = @Exchange(name = "hs.item.exchange", type = ExchangeTypes.TOPIC),
                    key = {"item.insert", "item.update"}
            )
    )
    public void listenInsertOrUpdate(String id){
        if(null == id){
            return;
        }
        // 对索引库新增或修改
        searchService.createOrUpdateIndex(id);
    }
}
