package com.xiaoba;


import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.io.IOException;

@SpringBootTest
public class XiaobaSearchApplicationTests extends AbstractTestNGSpringContextTests {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Test
    public void testCreateIndex() throws IOException {
        CreateIndexRequest request=new CreateIndexRequest("xiaobatest");
        CreateIndexResponse response= restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        System.out.print(response);
    }


    @Test
    public void testGetIndex() throws IOException {
        GetIndexRequest request=new GetIndexRequest("xiaobatest");
        boolean b=restHighLevelClient.indices().exists(request,RequestOptions.DEFAULT);
        System.out.print(b);
    }

    @Test
    public void testDeleteIndex() throws IOException {
        DeleteIndexRequest request=new DeleteIndexRequest("xiaobatest");
        AcknowledgedResponse delete= restHighLevelClient.indices().delete(request,RequestOptions.DEFAULT);
        System.out.print(delete.isAcknowledged());
    }
}
