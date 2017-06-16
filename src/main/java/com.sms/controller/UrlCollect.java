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

//    循环获取URl并用LoadDate、NormDate存储
    public StoreBase collectUrl(Crawler crawler){
        LoadDate loadDate=new LoadDate();
        NormData normData=new NormData();

        StoreBase storeBase=new StoreBase();

        HttpProxy httpProxy=new HttpProxy();
        DownLoadHtml downLoadHtml=new DownLoadHtml();

        HtmlParseUrl htmlParseUrl=new HtmlParseUrl();

       Boolean flag=true;
//    判断是否为空，如果是空则表示第一次进入
       if (normData.getHashMap().isEmpty()){
//        下载第一张网页信息
           String html=downLoadHtml.downloadHtml(httpProxy.createconnection(crawler));
//           爬取第一张网页信息的Url
           List<String> list=htmlParseUrl.crawLinksFromHtml(html,crawler.getUrlStr());
//          循环加入LoadDate、NormDate库
           for (String url:
                list) {
//          判断有没有重复，如果没有就添加到库中
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
