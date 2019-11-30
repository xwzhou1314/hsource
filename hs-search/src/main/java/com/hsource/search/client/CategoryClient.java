package com.hsource.search.client;


import com.hsource.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("item-service")
public interface CategoryClient extends GoodsApi{

}
