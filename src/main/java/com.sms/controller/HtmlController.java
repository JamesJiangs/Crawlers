package com.sms.controller;

import com.sms.entity.Crawler;
import com.sms.proxy.HttpProxy;
import com.sms.save.download.DownLoadHtml;
import com.sms.save.toLocal.SaveHtml;

import java.net.HttpURLConnection;
import java.util.Queue;

/**
 * Created by james.jiang on 2017/6/13.
 */
public class HtmlController {

    public synchronized void download(Queue<String> queue, Crawler crawler){
        //       循环获取集合中的Url，html下载到本地
        for (String urlStr2:
                queue) {
            if (urlStr2!=null){
                System.out.println(urlStr2);
                DownLoadHtml downLoadHtml=new DownLoadHtml();
                HttpURLConnection urlConnection1= new HttpProxy().createconnection(crawler);

                SaveHtml saveHtml=new SaveHtml(crawler.getLocation(),downLoadHtml.downloadHtml(urlConnection1),urlStr2);
                saveHtml.save();

            }
        }

    }
}
