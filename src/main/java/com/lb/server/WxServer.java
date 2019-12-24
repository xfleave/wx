package com.lb.server;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import com.lb.entity.BaseMessage;
import com.lb.entity.article.Article;
import com.lb.entity.article.NewsMessage;
import com.lb.entity.image.ImageMessage;
import com.lb.entity.music.Music;
import com.lb.entity.music.MusicMessage;
import com.lb.entity.text.TextMessage;
import com.lb.entity.video.VideoMessage;
import com.lb.util.JuheDemo;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/*
 * @Author 李彪
 * @Description
 * @Date 9:36 2019/12/23
 * @return
 **/
public class WxServer {
    private static final String TOKEN = "123abc";

    /**
     *@Description 校验
     *@Param
     *@Return
     *@Author libiao
     *@Date
     */
    /**
     * @param timestamp
     * @param nonce
     * @param signature
     * @return
     */
    public static boolean check(String timestamp, String nonce, String signature) {
        String[] strs = new String[]{TOKEN, timestamp, nonce};
        Arrays.sort(strs);
        String str = strs[0] + strs[1] + strs[2];
        String mysign = sha1(str);
        DigestUtils.sha1(str);
        return mysign.equalsIgnoreCase(signature);
    }

    private static String sha1(String str) {
        try {
            //获取加密对象
            MessageDigest md = MessageDigest.getInstance("sha1");
            //加密
            byte[] digest = md.digest(str.getBytes());
            char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            StringBuilder sb = new StringBuilder();
            //处理加密结束
            for (byte b : digest) {
                sb.append(chars[(b >> 4) & 15]);
                sb.append(chars[b & 15]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析xml数据包
     *
     * @param inputStream
     * @return
     */
    public static Map<String, String> parseRequest(InputStream inputStream) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        //读取输入流获取文档对象
        Document document = saxReader.read(inputStream);
        //获取根节点
        Element rootElement = document.getRootElement();
        Map<String, String> map = new HashMap<>();
        //获取根节点中的所有子节点
        List<Element> elements = rootElement.elements();
        for (Element e : elements
        ) {
            map.put(e.getName(), e.getStringValue());
        }
        System.out.println(map);
        return map;
    }

    /**
     * 用于处理所有的事件和消息的回复
     *
     * @return
     */
    public static String parseResponse(Map<String, String> map) {
        BaseMessage baseMessage = null;
        String msgType = map.get("MsgType");
        switch (msgType) {
            //处理文本消息
            case "text":
                baseMessage = dealText(map);
                break;
            case "image":
                baseMessage = dealImage(map);
                break;
            case "voice":
                baseMessage = dealVvoice(map);
                break;
            case "video":
                baseMessage = dealVideo(map);
                break;
            case "shortvideo":
                baseMessage = dealShortvideo(map);
                break;
            case "location":
                baseMessage = dealLocation(map);
                break;
            case "link":
                baseMessage = dealLink(map);
                break;
            default:
                break;
        }
        //把消息对象处理为xml包数据
        if (baseMessage != null) {
            return beanToXml(baseMessage);
        }
        return null;
    }

    private static String beanToXml(BaseMessage baseMessage) {
        XStream xStream = new XStream();
        //设置需要处理@XStreamAlias("xml")的类
        xStream.processAnnotations(TextMessage.class);
        xStream.processAnnotations(Article.class);
        xStream.processAnnotations(NewsMessage.class);
        xStream.processAnnotations(Music.class);
        xStream.processAnnotations(MusicMessage.class);
        xStream.processAnnotations(ImageMessage.class);
        xStream.processAnnotations(VideoMessage.class);
        String xml = xStream.toXML(baseMessage);
        //xStream.processAnnotations(Article.class);
        System.out.println(xml);
        return xml;
        //System.out.println(xStream.toXML(baseMessage));
    }

    /**
     * 處理连接消息
     *
     * @param map
     */
    private static BaseMessage dealLink(Map<String, String> map) {
        return null;
    }

    private static BaseMessage dealLocation(Map<String, String> map) {
        return null;
    }

    private static BaseMessage dealShortvideo(Map<String, String> map) {
        return null;
    }

    private static BaseMessage dealVideo(Map<String, String> map) {
        return null;
    }

    private static BaseMessage dealVvoice(Map<String, String> map) {
        return null;
    }

    /**
     * 处理图片消息
     *
     * @param map
     */
    private static BaseMessage dealImage(Map<String, String> map) {
        return null;
    }

    /**
     * 处理文本消息
     *
     * @param map
     */
    private static BaseMessage dealText(Map<String, String> map) {

        String content = map.get("Content");
        /*手机号查询*/
        String msg = JuheDemo.getRequest1(content);
        TextMessage textMessage = new TextMessage(map, msg);
        if (content.equals("图文")) {
            List<Article> articles = new ArrayList<>();
            articles.add(new Article("这是图文消息的标题", "详细介绍",
                    "http://mmbiz.qpic.cn/mmbiz_jpg/elXWwtdDL2aMbFTWwFSAeJE9ia4Q6VXAVticI73sPc9A94e0RJO3BqiaYdm2OVpTp2JPYg2WrILX7fX0s6PDehkqA/0", "http://mmbiz.qpic.cn/mmbiz_jpg/elXWwtdDL2aMbFTWwFSAeJE9ia4Q6VXAVticI73sPc9A94e0RJO3BqiaYdm2OVpTp2JPYg2WrILX7fX0s6PDehkqA/0"));
            NewsMessage newsMessage = new NewsMessage(map, "1", articles);
            return newsMessage;
        }
        return textMessage;
    }
}
