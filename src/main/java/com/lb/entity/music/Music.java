package com.lb.entity.music;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/*
 * @Author 李彪
 * @Description
 * @Date 14:23 2019/12/24
 * @return
 **/
@XStreamAlias("xml")
public class Music {
    private String title;
    private String description;
    private String musicUrl;
    private String hQMusicUrl;
    private String thumbMediald;

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

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String gethQMusicUrl() {
        return hQMusicUrl;
    }

    public void sethQMusicUrl(String hQMusicUrl) {
        this.hQMusicUrl = hQMusicUrl;
    }

    public String getThumbMediald() {
        return thumbMediald;
    }

    public void setThumbMediald(String thumbMediald) {
        this.thumbMediald = thumbMediald;
    }

    public Music(String title, String description, String musicUrl, String hQMusicUrl, String thumbMediald) {
        this.title = title;
        this.description = description;
        this.musicUrl = musicUrl;
        this.hQMusicUrl = hQMusicUrl;
        this.thumbMediald = thumbMediald;
    }
}
