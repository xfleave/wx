package com.lb.controller;

import com.alibaba.fastjson.JSONArray;
import com.lb.server.WxServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author libioa
 * @version 1.0
 * @date 2019/12/22 0022 20:59
 */
@RestController
public class TestController {
    @RequestMapping("/")
    public String IndexController() {
        String sql = "select * from Student";
        Map<String, ArrayList> map = new HashMap<String, ArrayList>();
        ArrayList arrayList = new ArrayList();
        arrayList.add("1");
        arrayList.add("2");
        map.put("userId", arrayList);
        return JSONArray.toJSONString(map);
    }

    @RequestMapping("/Wx")
    /**
     *@Description 校验
     *@Param
     *@Return
     *@Author libiao
     *@Date
     */
    public void wxIndex(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        //校验请求
        if (WxServer.check(timestamp, nonce, signature) != false) {
            PrintWriter writer = response.getWriter();
            //原样返回
            writer.print(echostr);
            //System.out.println("校验成功");
            writer.flush();
            writer.close();
        } else {
            System.out.println("校验失败");
        }
    }
}
