package com.sms.proxy;

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
     * 创建代理
     * @param url 网页链接
     * @param host
     * @param port
     * @return HttpURLConnection
     */
    public HttpURLConnection createconnection(String url,String host,int port){

        HttpURLConnection httpURLConnection=null;
        try {

            httpURLConnection= (HttpURLConnection) new URL(url).openConnection(new Proxy(Proxy.Type.HTTP,new InetSocketAddress(host,port)));
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
                System.out.println("连接正常......");
                return true;
            case HttpURLConnection.HTTP_NOT_FOUND:
                System.out.println("找不到资源！");
                return false;
            case HttpURLConnection.HTTP_INTERNAL_ERROR:
                System.out.println("服务器发生错误！");
                return false;
            case HttpURLConnection.HTTP_NOT_MODIFIED:
                System.out.println("请求资源未更新！");
                return false;
            case HttpURLConnection.HTTP_UNAUTHORIZED:
                System.out.println("未授权！");
                return false;
        }
        System.out.println("请求失败！");
        return false;
    }
}
