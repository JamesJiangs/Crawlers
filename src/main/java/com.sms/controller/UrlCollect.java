package com.sms.controller;

import com.sms.entity.Crawler;
import com.sms.parser.HtmlParseUrl;
import com.sms.proxy.HttpProxy;
import com.sms.save.download.DownLoadHtml;
import com.sms.store.LoadDate;
import com.sms.store.NormData;
import com.sms.store.StoreBase;

import java.util.List;

/**
 * Created by james.jiang on 2017/6/13.
 *
 */
public class UrlCollect {

    public StoreBase collectUrl(Crawler crawler){
        LoadDate loadDate=new LoadDate();
        NormData normData=new NormData();

        StoreBase storeBase=new StoreBase();

        HttpProxy httpProxy=new HttpProxy();
        DownLoadHtml downLoadHtml=new DownLoadHtml();

        HtmlParseUrl htmlParseUrl=new HtmlParseUrl();

       Boolean flag=true;

       if (normData.getHashMap().isEmpty()){

           String html=downLoadHtml.downloadHtml(httpProxy.createconnection(crawler));
           List<String> list=htmlParseUrl.crawLinksFromHtml(html,crawler.getUrlStr());

           for (String url:
                list) {

               if (!normData.isContain(url)){
                     System.out.println(url);

                     loadDate.add(url);
                       normData.add(url);
               }

           }

       }

        storeBase.setLoadDate(loadDate);
        storeBase.setNormData(normData);
        return storeBase;
    }


}
