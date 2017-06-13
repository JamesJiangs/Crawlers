package com.sms.controller;

import com.sms.entity.Crawler;
import com.sms.proxy.HttpProxy;
import com.sms.store.UrlStore;

import java.net.HttpURLConnection;
import java.util.Queue;

/**
 * Created by james.jiang on 2017/6/12.
 *
 */

public class Test {

    public static void main(String[] args) {
        Crawler crawler=new Crawler("111.23.10.33",8080,"http://www.zol.com.cn/","E:\\HttpProxy\\downloadFile");

        UrlStore urlStore=new UrlStore();

//        设置代理并测试是否成功
        HttpProxy httpProxy=new HttpProxy();
        HttpURLConnection urlConnection=httpProxy.createconnection(crawler);
        boolean isConn=httpProxy.isConnection(urlConnection);

        if (isConn){


            System.out.println("代理成功！");

            UrlCollect urlCollect=new UrlCollect();
            UrlStore urlStore1=urlCollect.collectUrl(crawler,urlStore);

            Queue<String> queue=urlStore1.getStore();

           new ImageController().download(urlStore1.getStore(),crawler);

        }else {
            System.out.println("代理失败！");
        }


    }
}
