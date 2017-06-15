package com.sms.controller;

import com.sms.entity.Crawler;
import com.sms.proxy.HttpConn;
import com.sms.store.PareDate;
import com.sms.store.StoreBase;

/**
 * Created by james.jiang on 2017/6/12.
 *
 */

public class Test {

    public static void main(String[] args) {
//       填写相关的参数
        Crawler crawler = new Crawler("183.166.167.191", 8080, "http://www.tiexue.net/", "E:\\HttpProxy\\downloadFile", "utf-8");

        HttpConn httpConn = new HttpConn();
        Boolean flag=httpConn.test(crawler);


        PareDate pareDate=new PareDate();
        HtmlController htmlController=new HtmlController();
        StoreBase storeBase;

        if (flag){
            storeBase=new UrlCollect().collectUrl(crawler);
            htmlController.download(storeBase,pareDate,crawler);
        }





    }

}
