package com.lb.controller;

import com.alibaba.fastjson.JSONArray;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author libioa
 * @version 1.0
 * @date 2019/12/22 0022 20:59
 */
@RestController
public class TestController {
    @RequestMapping("/")
    public String IndexController(){
        String sql="select * from Student";
        return JSONArray.toJSONString(sql);
    }
}
