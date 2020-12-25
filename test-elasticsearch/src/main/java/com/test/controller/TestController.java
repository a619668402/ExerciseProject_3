package com.test.controller;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @RequestMapping("test")
    @ResponseBody
    public String test() {
        return "aa";
    }
}
