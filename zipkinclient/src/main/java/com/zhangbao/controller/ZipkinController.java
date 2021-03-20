package com.zhangbao.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangbao
 * @date 2021/3/17 23:17
 **/
@RestController
@RequestMapping("/zipkin")
public class ZipkinController {

    @Value("${server.port}")
    private int port;

    @GetMapping("/index")
    public String index(){
        return this.port+"";
    }
}
