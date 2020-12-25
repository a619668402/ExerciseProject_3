package com.test;

import com.alibaba.fastjson.JSON;
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
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.*;

@SpringBootTest
public class ApplicationTest {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    // 创建索引
    @Test
    void createIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("test2");
        CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(response);
    }

    // 获取索引
    @Test
    void getIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("test2");
        GetIndexResponse response = restHighLevelClient.indices().get(request, RequestOptions.DEFAULT);
        System.out.println(response);
    }

    // 删除索引
    @Test
    void deleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("test2");
        AcknowledgedResponse response = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(response.isAcknowledged());
    }

    // 添加数据
    @Test
    void createDoc() throws IOException {
        IndexRequest request = new IndexRequest("test2");
        request.id("1");
        Map<String, Object> map = new HashMap<>();
        map.put("userName", "张三");
        map.put("passWord", "123456");
        map.put("age", "23");
        List<String> list = new ArrayList<>();
        list.add("LOL");
        list.add("篮球");
        list.add("足球");
        map.put("hobby", list);
        request.source(map);
        IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        System.out.println(response.status());
    }

    // 获取数据
    @Test
    void getDoc() throws IOException {
        GetRequest request = new GetRequest("test2", "1");
        GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        System.out.println(response.getSource());
        System.out.println(response);
    }

    // 修改数据
    @Test
    void updateDoc() throws IOException {
        UpdateRequest request = new UpdateRequest("test2", "1");
        Map<String, Object> map = new HashMap<>();
        map.put("userName", "王五");
        request.doc(map);
        UpdateResponse response = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        System.out.println(response.status());
    }

    // 删除数据
    @Test
    void deleteDoc() throws IOException {
        DeleteRequest request = new DeleteRequest("test2", "1");
        DeleteResponse response = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        System.out.println(response.status());
    }

    // 批量插入
    @Test
    void batchInsert() throws IOException {
        BulkRequest request = new BulkRequest("test2");
        for (int i = 1; i < 10000; i ++) {
            IndexRequest indexRequest = new IndexRequest("test2");
            indexRequest.id(i + "");
            Map<String, Object> map = new HashMap<>();
            map.put("userName", "张三" + i);
            map.put("passWord", "123456");
            map.put("age", i);
            List<String> list = new ArrayList<>();
            list.add("LOL");
            list.add("篮球");
            list.add("足球");
            map.put("hobby", list);
            indexRequest.source(map);
            request.add(indexRequest);
        }
        BulkResponse response = restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        System.out.println(response.status());
    }

    // 搜索
    @Test
    void searchDoc() throws IOException {
        SearchRequest request = new SearchRequest("test2");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        TermQueryBuilder queryBuilder = new TermQueryBuilder("userName.keyword", "张三1");
//        MatchQueryBuilder queryBuilder = new MatchQueryBuilder("userName", "张三");
        searchSourceBuilder.query(queryBuilder);
        request.source(searchSourceBuilder);
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        System.out.println(response.getHits().getTotalHits());
    }
}
