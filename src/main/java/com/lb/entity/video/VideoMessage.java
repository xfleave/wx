package com.lb.entity.video;

import com.lb.entity.BaseMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/*
 * @Author 李彪
 * @Description
 * @Date 14:15 2019/12/24
 * @return
 **/
@XStreamAlias("xml")
public class VideoMessage extends BaseMessage {
    private String mediaId;
    private String title;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public VideoMessage(Map<String, String> map, String mediaId, String title, String description) {
        super(map);
        this.setMsgType("video");
        this.mediaId = mediaId;
        this.title = title;
        this.description = description;
    }
}
