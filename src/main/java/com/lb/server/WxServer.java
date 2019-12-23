package com.lb.server;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;

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
}
