package com.sms.controller;

import com.sms.entity.Crawler;

/**
 * Created by james.jiang on 2017/6/12.
 *测试
 */

public class Test {

    public static void main(String[] args) {
//       填写相关的参数
        Crawler crawler = new Crawler("220.194.213.52", 8080, "http://soft.hao123.com/", "E:\\HttpProxy\\downloadFile", "utf-8");

         CoreController coreController=new CoreController();
            String url=crawler.getUrlStr();

            while (url!=null){
                System.out.println(url);
                url=coreController.core(crawler);
            }

    }

}
