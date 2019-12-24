package com.lb.entity.music;

import com.lb.entity.BaseMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/*
 * @Author 李彪
 * @Description
 * @Date 14:18 2019/12/24
 * @return
 **/
@XStreamAlias("xml")
public class MusicMessage extends BaseMessage {
    private Music music;

    public MusicMessage(Map<String, String> map, Music music) {
        super(map);
        this.setMsgType("music");
        this.music = music;
    }

}
