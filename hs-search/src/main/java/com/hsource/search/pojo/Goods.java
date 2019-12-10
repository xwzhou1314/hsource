package com.hsource.search.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.Map;
import java.util.Set;

@Data
@Document(indexName = "goods", type = "docs", shards = 1, replicas = 1)
public class Goods {

        @Id
        private Long id;

        @Field(type = FieldType.Text, analyzer = "ik_max_word")
        private String all;

        @Field(type = FieldType.Keyword, index = false)
        private String subtitle;

        private Long brandId;
        private Long cid1;
        private Long cid2;
        private Long cid3;

        private Date createTime;
        private Set<Double> price;

        @Field(type = FieldType.Keyword, index = false)
        private String skus;
        private Map<String, Object> specs;




}
