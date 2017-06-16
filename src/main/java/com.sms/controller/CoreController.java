package com.sms.controller;

import com.sms.entity.Crawler;
import com.sms.proxy.HttpConn;
import com.sms.store.LoadDate;
import com.sms.store.PareDate;
import com.sms.store.StoreBase;

/**
 * Created by james.jiang on 2017/6/16.
 *
 */
public class CoreController {


    public String core(Crawler crawler){
        if (crawler!=null ){
            if (crawler.getUrlStr()!=null){

                HttpConn httpConn = new HttpConn();
                PareDate pareDate=new PareDate();
                HtmlController htmlController=new HtmlController();
                StoreBase storeBase;

                if (httpConn.test(crawler)){
                    storeBase=new UrlCollect().collectUrl(crawler);
                    htmlController.download(storeBase,pareDate,crawler);
                    return  getNext(storeBase,crawler);
                }
            }
        }
        return  null;
    }



    private String getNext(StoreBase storeBase,Crawler crawler) {
        String url=null;
        if(storeBase!=null){
            LoadDate loadDate=storeBase.getLoadDate();
            if ((url=loadDate.getUrl())!=null)crawler.setUrlStr(url);
        }
        return url;
    }
}
