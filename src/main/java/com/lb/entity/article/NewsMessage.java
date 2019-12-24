package com.lb.entity.article;

import com.lb.entity.BaseMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * @Author 李彪
 * @Description
 * @Date 14:30 2019/12/24
 * @return
 **/
@XStreamAlias("xml")
public class NewsMessage extends BaseMessage {
    @XStreamAlias("ArticleCount")
    private String articleCount;
    @XStreamAlias("Articles")
    private List<Article> articles=new ArrayList<Article>();

    public String getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(String articleCount) {
        this.articleCount = articleCount;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public NewsMessage(Map<String, String> map, String articleCount, List<Article> articles) {
        super(map);
        this.setMsgType("news");
        this.articleCount = articleCount;
        this.articles = articles;
    }
}
