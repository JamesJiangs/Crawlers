package com.sms.controller;

import com.sms.entity.Crawler;
import com.sms.proxy.HttpConn;
import com.sms.proxy.HttpProxy;
import com.sms.save.download.DownLoadHtml;
import com.sms.save.toLocal.SaveHtml;
import com.sms.store.LoadDate;
import com.sms.store.NormData;
import com.sms.store.PareDate;
import com.sms.store.StoreBase;

/**
 * Created by james.jiang on 2017/6/13.
 *
 */
public class HtmlController {

    public void download(StoreBase storeBase, PareDate pareDate, Crawler crawler){

        LoadDate loadDate=storeBase.getLoadDate();
        NormData normData=storeBase.getNormData();
        HttpConn httpConn=new HttpConn();
        DownLoadHtml downLoadHtml=new DownLoadHtml();
        HttpProxy httpProxy=new HttpProxy();
        Boolean flag=true;

        while (flag){

            String url;
            if ((url=loadDate.getUrl())!=null){

                if (!normData.isUse(url)){
                    pareDate.add(url);
                }

            }else {
                flag=false;
            }

        }

        for (String url:
             pareDate.getQueue()) {
            crawler.setUrlStr(url);
            if (httpConn.test(crawler)){
                System.out.println(url);
                String htmlStr;
                if ((htmlStr=downLoadHtml.downloadHtml(httpProxy.createconnection(crawler)))==null)continue;
                SaveHtml saveHtml=new SaveHtml(crawler.getLocation(),htmlStr,crawler.getUrlStr());
                saveHtml.save();
                normData.setTrue(url);
            }else {
                continue;
            }

        }

    }
}
