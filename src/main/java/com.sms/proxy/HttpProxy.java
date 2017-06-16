package com.sms.proxy;

import com.sms.entity.Crawler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

/**
 * Created by james.jiang on 2017/6/12.
 * 模拟浏览器代理
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

}
