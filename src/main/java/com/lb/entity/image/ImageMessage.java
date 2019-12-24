package com.lb.entity.image;

import com.lb.entity.BaseMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/*
 * @Author 李彪
 * @Description
 * @Date 12:22 2019/12/24
 * @return
 **/
@XStreamAlias("xml")
public class ImageMessage extends BaseMessage {
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public ImageMessage(Map<String, String> map, String mediaId) {
        super(map);
        this.setMsgType("image");
        this.mediaId = mediaId;
    }
}
