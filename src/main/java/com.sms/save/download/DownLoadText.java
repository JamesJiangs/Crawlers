package com.sms.save.download;

import org.jsoup.Jsoup;

/**
 * Created by james.jiang on 2017/6/13.
 * 下载文本
 */
public class DownLoadText {
    /**文件名*/
    private String html;

    public DownLoadText(String html) {
        this.html = html;
    }

    /**
     * 获取网页纯文本信息
     * @return 网页文本
     */
    public String getText(){
        return Jsoup.parse(html).text();
    }

    /**
     * 获取完整的网页文本
     * @return 网页文本
     */
    public String getHtml(){
        return html;
    }
    /**
     * 获取文本的名字
     * @return name
     */
    public String getName(){
        String name=Jsoup.parse(html).select("title").first().text();
        System.out.println("名字"+name);
        return name;
    }

}
