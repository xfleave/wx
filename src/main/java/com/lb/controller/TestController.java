package com.lb.controller;

import com.alibaba.fastjson.JSONArray;
import com.lb.server.WxServer;
import org.dom4j.DocumentException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
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

    @GetMapping("/Wx")
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
        /*ServletInputStream inputStream = request.getInputStream();
        byte[] bytes = new byte[1024];
        int len;

        StringBuffer stringBuffer = new StringBuffer();
        while ((len = inputStream.read(bytes)) != -1) {
            stringBuffer.append(new String(bytes, 0, len));
        }
        System.out.println(stringBuffer.toString());*/
    }

    /**
     * 接收消息 和事件推送
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @PostMapping("/Wx")
    public void wxMessage(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //处理消息和事件推送
        Map<String, String> map = WxServer.parseRequest(request.getInputStream());

        String resXmlStr = WxServer.parseResponse(map);

         /*       "<xml><ToUserName><![CDATA[" + map.get("FromUserName") + "]]></ToUserName>" +//此处要填写 发送方帐号（一个OpenID）
                "<FromUserName><![CDATA[" + map.get("ToUserName") + "]]></FromUserName>" +//此处填写开发者微信号
                "<CreateTime>" + System.currentTimeMillis() / 1000 + "</CreateTime>" +
                "<MsgType><![CDATA[text]]></MsgType>" +
                "<Content><![CDATA[" + map.get("Content") + "]]></Content></xml>";*/
        PrintWriter writer = response.getWriter();
        writer.print(resXmlStr);
        writer.flush();
        writer.close();
        /*        StringBuffer respXml = new StringBuffer();
        respXml.append("<xml>");
        respXml.append("<ToUserName><![CDATA[");
        respXml.append(map.get("FromUserName"));
        respXml.append("]]></ToUserName>");
        respXml.append("<FromUserName><![CDATA[");
        respXml.append(map.get("FromUserName"));
        respXml.append("]]></FromUserName>");
        respXml.append("<CreateTime>");
        respXml.append(System.currentTimeMillis() / 1000);
        respXml.append("</CreateTime>");
        respXml.append("<MsgType><![CDATA[text]]></MsgType>");
        respXml.append("<Content><![CDATA[");
        respXml.append("hello,word!");
        respXml.append("]]></Content></xml>");*/
        //String respXml = WxServer.getResponse(map);
        //System.out.println(respXml);

    }

}
