package com.sms.controller;

import com.sms.entity.Crawler;
import com.sms.parser.HtmlParseUrl;
import com.sms.proxy.HttpProxy;
import com.sms.save.download.DownLoadHtml;
import com.sms.store.UrlStore;

import java.net.HttpURLConnection;
import java.util.List;

/**
 * Created by james.jiang on 2017/6/13.
 */
public class UrlCollect {

    public UrlStore collectUrl(Crawler crawler, UrlStore urlStore){

//        判断urlStore中是否包含url,如果不包含就执行
        if (!urlStore.isRepetition(crawler.getUrlStr())){
            HttpProxy httpProxy=new HttpProxy();
            HttpURLConnection urlConnection=httpProxy.createconnection(crawler);

            boolean isConn=httpProxy.isConnection(urlConnection);
            if (isConn){

                List<String> firstPageUrl= new HtmlParseUrl().crawLinksFromHtml(new DownLoadHtml().downloadHtml(urlConnection),crawler.getUrlStr());

                for (String link:
                        firstPageUrl) {

                    if (!urlStore.isRepetition(link)){

                        urlStore.addHashMap(link);
                        urlStore.addQueue(link);
                    }
                }

        }else {
//                String urlStr=urlStore.getStore().peek();
//                if (urlStore.isContains(urlStr)){
//                    crawler.setUrlStr(urlStr);
//                    urlStore.hasBeUsed(urlStr);
//                }
            }

        }else {
            System.out.println("连接失败！");
        }

        return urlStore;
    }
}
