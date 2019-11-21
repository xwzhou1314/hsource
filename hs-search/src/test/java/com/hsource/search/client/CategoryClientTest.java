package com.hsource.search.client;

import com.hsource.item.entity.TbCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryClientTest {


    @Autowired
    CategoryClient client;

    @Test
    public void queryCategoryByIds() {
        List<TbCategory> list = client.queryCategoryByIds(Arrays.asList("1","2"));
        for (TbCategory f:list
             ) {
            System.out.println(f);
        }
    }
}