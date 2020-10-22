package com.zhiyou100;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MongoDBAPI {

    /**
     * 要先创建spitdb 库
     * 查询文档
     */
    @Test
    public void test1() {
        // 连接mongo服务,创建客户端
        MongoClient client = new MongoClient("192.168.110.130");
        // 得到要操作的数据库
        MongoDatabase spitdb = client.getDatabase("spitdb");
        // 得到要操作的集合
        MongoCollection<Document> spit = spitdb.getCollection("spit");
        // 得到集合中所有文档
        Map map = new HashMap();
        }
}
