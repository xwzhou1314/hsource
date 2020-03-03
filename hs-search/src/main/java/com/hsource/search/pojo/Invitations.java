package com.hsource.search.pojo;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "invitations", type = "docs", shards = 1, replicas = 1)
public class Invitations {

}
