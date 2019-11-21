package com.hsource.search.repository;

import com.hsource.search.pojo.Goods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsRepositoryTest {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private ElasticsearchTemplate  template;

    @Test
    public void testCreateIndex(){
        // 1. 常见索引库，及映射
        template.createIndex(Goods.class);
        template.putMapping(Goods.class);
    }

    @Test
    public void loadData(){
        // 2. 加载数据
        // 构建商品 Goods （以来注入  XXXXXClient 或 XXXXService，这个方法中获取Goods）
        List<Goods> goods = null;
        // 存入索引库

        goodsRepository.saveAll(goods);
    }

}