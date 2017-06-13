package com.sms.controller;

import com.sms.entity.Crawler;
import com.sms.parser.HtmlParseUrl;
import com.sms.proxy.HttpProxy;
import com.sms.save.download.DownLoadHtml;
import com.sms.save.toLocal.SaveHtml;
import com.sms.store.UrlStore;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

/**
 * Created by james.jiang on 2017/6/12.
 */
public class Test {
    public static void main(String[] args) {
        Crawler crawler=new Crawler();
        crawler.setHost("121.8.98.201");
        crawler.setPort(8080);
        crawler.setLocation("E:\\HttpProxy\\downloadFile");
        crawler.setUrlStr("http://mil.huanqiu.com/");

//        设置代理并测试是否成功
        HttpProxy httpProxy=new HttpProxy();
        HttpURLConnection urlConnection= httpProxy.createconnection(crawler.getUrlStr(),crawler.getHost(),crawler.getPort());

        boolean isConn=httpProxy.isConnection(urlConnection);

        if (isConn){

            System.out.println("代理成功！");
            //        爬取url
            HtmlParseUrl htmlParseUrl=new HtmlParseUrl();

            DownLoadHtml downLoadHtml=new DownLoadHtml();

//        获取URL集合
            List<String> firstPageUrl= htmlParseUrl.crawLinksFromHtml(downLoadHtml.downloadHtml(urlConnection),crawler.getUrlStr());

            UrlStore urlStore=new UrlStore();

//     循环URL集合并判读是否存在相同的URL，如果存在就不添加，如果不存在就添加
            for (String link:
                    firstPageUrl) {
                if (!urlStore.isRepetition(link)){
                    urlStore.addHashMap(link);
                    urlStore.addQueue(link);
                }
            }

        Queue<String> queue=urlStore.getStore();
        HashMap<String,Boolean> hashMap=urlStore.getCrawl();

//       循环获取集合中的Url，html下载到本地
        for (String urlStr2:
             queue) {
            if (urlStr2!=null){
                System.out.println(urlStr2+"--------------");
                HttpURLConnection urlConnection1= httpProxy.createconnection(urlStr2,crawler.getHost(),crawler.getPort());
                SaveHtml saveHtml=new SaveHtml(crawler.getLocation(),downLoadHtml.downloadHtml(urlConnection1),urlStr2);
                saveHtml.save();
//                crawler.setUrlStr(urlStr2);
//
//                HttpProxy httpProxy1=new HttpProxy();
//                HttpURLConnection urlConnection1= httpProxy1.createconnection(crawler.getUrlStr(),crawler.getHost(),crawler.getPort());
//
//                boolean isConn1=httpProxy1.isConnection(urlConnection1);
//                if (isConn1){
//                    List<String> pageUrl=htmlParseUrl.crawLinksFromHtml(downLoadHtml.downloadHtml(urlConnection1),crawler.getUrlStr());
//                    for (String link:
//                         pageUrl) {
//                        if (!ur)
//                    }
//                }

            }
        }



        }else {
            System.out.println("代理失败！");
        }


    }
}
