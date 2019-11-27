package com.hsource.search.service;

import com.hsource.search.pojo.Goods;
import com.hsource.search.repository.GoodsRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

@Service
public class SearchService {


    @Autowired
    private GoodsRepository repository;


    @Autowired
    private ElasticsearchTemplate template;

    public Page<Goods> search(){

        //创建查询构造器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        // 0 过滤结果
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{"id",""}, null));

        // 1 分页
        Integer page = 1;
        Integer pageSize = 20;
        queryBuilder.withPageable(PageRequest.of(page, pageSize));


        // 2 过滤
        // 过滤条件
        String key = "";
        queryBuilder.withQuery(QueryBuilders.matchQuery("all", key));

//        // 3 聚合分类
//        String categoryAggName = "category_agg";
//        queryBuilder.addAggregation(AggregationBuilders.terms(categoryAggName).field("cid3"));
//        // 4. 过滤查询
//        AggregatedPage<Goods> result = template.queryForPage(queryBuilder.build(), Goods.class);

        // 查询
       Page<Goods> goods = repository.search(queryBuilder.build());

       return goods;
    }

    public void createOrUpdateIndex(String id) {
        // 构建 Goods
        Goods goods = null;
        // 存入索引库
        repository.save(goods);
    }
}
