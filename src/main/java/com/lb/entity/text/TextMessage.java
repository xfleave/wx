package com.lb.entity.text;

import com.lb.entity.BaseMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/*
 * @Author 李彪
 * @Description
 * @Date 12:09 2019/12/24
 * @return
 **/
@XStreamAlias("xml")
public class TextMessage extends BaseMessage {
    @XStreamAlias("Content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    //设置文本消息
    public TextMessage(Map<String, String> map, String content) {
        super(map);
        this.setMsgType("text");
        this.content = content;
    }

    @Override
    public String toString() {
        return "TextMessage{" +
                "content='" + content + '\'' +
                '}';
    }
}
