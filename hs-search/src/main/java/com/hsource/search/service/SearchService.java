package com.hsource.search.service;

import com.hsource.search.pojo.Goods;
import com.hsource.search.repository.GoodsRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

@Service
public class SearchService {


    @Autowired
    private GoodsRepository repository;

    public Page<Goods> search(){

        //创建查询构造器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        // 过来结果
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{"id",""}, null));

        // 分页
        Integer page = 1;
        Integer pageSize = 20;
        queryBuilder.withPageable(PageRequest.of(page, pageSize));

        // 过滤
        // 查询条件
        String key = "";
        queryBuilder.withQuery(QueryBuilders.matchQuery("all", key));

        // 查询
       Page<Goods> goods = repository.search(queryBuilder.build());

       return goods;
    }
}
