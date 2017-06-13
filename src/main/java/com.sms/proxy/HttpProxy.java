package com.sms.proxy;

import com.sms.entity.Crawler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

/**
 * Created by james.jiang on 2017/6/12.
 */
public class HttpProxy {


    /**
     *
     * @param crawler
     * @return
     */
    public HttpURLConnection createconnection(Crawler crawler){

        HttpURLConnection httpURLConnection=null;
        try {
            httpURLConnection= (HttpURLConnection) new URL(crawler.getUrlStr())
                    .openConnection(new Proxy(Proxy.Type.HTTP,new InetSocketAddress(crawler.getHost(),crawler.getPort())));
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) ...");
            httpURLConnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpURLConnection;
    }

    /**
     * 获取代理状态
     * @param httpURLConnection
     * @return
     */
    public boolean isConnection(HttpURLConnection httpURLConnection){

        int stautsCode=500;
        try {
            stautsCode=httpURLConnection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (stautsCode){
            case HttpURLConnection.HTTP_OK:
                System.out.println(stautsCode+"：连接正常......");
                return true;
            case HttpURLConnection.HTTP_NOT_FOUND:
                System.out.println(stautsCode+"：找不到资源！");
                return false;
            case HttpURLConnection.HTTP_INTERNAL_ERROR:
                System.out.println(stautsCode+"：服务器发生错误！");
                return false;
            case HttpURLConnection.HTTP_NOT_MODIFIED:
                System.out.println(stautsCode+"：请求资源未更新！");
                return false;
            case HttpURLConnection.HTTP_UNAUTHORIZED:
                System.out.println(stautsCode+"：未授权！");
                return false;
            case HttpURLConnection.HTTP_BAD_REQUEST:
                System.out.println(stautsCode+"：非法请求！");
        }
        System.out.println("请求失败！");
        return false;
    }
}
