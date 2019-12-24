package com.lb.test;

import com.lb.entity.text.TextMessage;
import com.thoughtworks.xstream.XStream;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/*
 * @Author 李彪
 * @Description
 * @Date 14:34 2019/12/24
 * @return
 **/
public class TestWx {
    @Test
    public void testMsg() {
        Map<String, String> map = new HashMap<>();
        map.put("ToUserName", "to");
        map.put("FromUserName", "from");
        map.put("MsgType", "type");
        TextMessage tm = new TextMessage(map, "还好");
        XStream xStream = new XStream();
        //设置需要处理@XStreamAlias("xml")的类
        xStream.processAnnotations(TextMessage.class);
        System.out.println(xStream.toXML(tm));
    }
}
