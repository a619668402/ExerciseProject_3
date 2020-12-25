package com.test;

import com.alibaba.fastjson.JSON;
import com.test.pojo.User;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;

@SpringBootTest
public class ApplicationTest {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    // 创建索引
    @Test
    void createIndex() throws IOException {
        // 1. 创建 Index 索引请求
        CreateIndexRequest indexRequest = new CreateIndexRequest("test1");
        // 2. 执行请求
        CreateIndexResponse response = restHighLevelClient.indices().create(indexRequest, RequestOptions.DEFAULT);

        System.out.println(response);
    }

    // 获取索引
    @Test
    void getIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("test55");
        boolean flag = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(flag);
    }

    // 删除索引
    @Test
    void deleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("test5");
        AcknowledgedResponse response = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(response);
    }

    // 创建文档
    @Test
    void createDocument() throws IOException {
        User user = new User("zhangsan", 20);
        IndexRequest request = new IndexRequest("test1");
        request.id("1");
        request.timeout(TimeValue.timeValueSeconds(1));

        request.source(JSON.toJSONString(user), XContentType.JSON);
        IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        System.out.println(response.toString());
    }

    // 判断文档是否存在
    @Test
    void existDocument() throws IOException {
        GetRequest request = new GetRequest("test1", "1");
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none");
        boolean exists = restHighLevelClient.exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    // 获取文档信息
    @Test
    void getDocument() throws IOException {
        GetRequest request = new GetRequest("test1", "1");
        GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        System.out.println(response.getSource());
        System.out.println(response);
    }

    // 更新文档信息
    @Test
    void updateDocument() throws IOException {
        UpdateRequest request = new UpdateRequest("test1", "1");
        User user = new User();
//        user.setName("呵呵");
        user.setAge(15);
        request.doc(JSON.toJSONString(user), XContentType.JSON);
        UpdateResponse response = restHighLevelClient.update(request, RequestOptions.DEFAULT);
    }

    // 删除文档信息
    @Test
    void deleteDocument() throws IOException {
        DeleteRequest request = new DeleteRequest("test1", "1");
        DeleteResponse response = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        System.out.println(response);
    }

    // 批量插入
    @Test
    void batchInsert() throws IOException {
        BulkRequest request = new BulkRequest("test1");

        ArrayList<User> users = new ArrayList<>();
        for (int i = 1; i < 1000; i ++) {
            User user = new User("测试 " + i, i);
            IndexRequest request1 = new IndexRequest("test1")
                    .id(i + "")
                    .source(JSON.toJSONString(user), XContentType.JSON);
            request.add(request1);
        }
        BulkResponse bulk = restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        System.out.println(bulk);
    }

    // 搜索
    @Test
    void search() throws IOException {
        SearchRequest request = new SearchRequest("test1");

        SearchSourceBuilder searchBuilder = new SearchSourceBuilder();
//        TermQueryBuilder queryBuilder = QueryBuilders.termQuery("name", "测试 2");
        QueryStringQueryBuilder queryBuilder = QueryBuilders.queryStringQuery("测试");

        searchBuilder.query(queryBuilder);

        request.source(searchBuilder);

        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        System.out.println(response.getHits().getTotalHits());
    }

}
