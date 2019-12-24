package com.lb.entity.voice;

import com.lb.entity.BaseMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/*
 * @Author 李彪
 * @Description
 * @Date 12:29 2019/12/24
 * @return
 **/
@XStreamAlias("xml")
public class VoiceMessage extends BaseMessage {
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public VoiceMessage(Map<String, String> map, String mediaId) {
        super(map);
        this.setMsgType("voice");
        this.mediaId = mediaId;
    }
}
