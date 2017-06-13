package com.sms.parser;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by james.jiang on 2017/6/12.
 */
public class HtmlParseUrl {

    public List<String> crawLinksFromHtml(String htmlStr,String urlStr){
        List<String> list=new ArrayList<String>();
        Document document= Jsoup.parse(htmlStr);

        Elements elements=document.select("a");

        for (Element element:
                elements) {
            //连接
            String link=element.attr("href");

            if(link.matches("[a-zA-z]+://[^\\s]*") && link.contains(this.getDomainForUrl(urlStr))
                    && !link.contains("download")&&!link.contains("client")){
                list.add(link);

            }else if(link.matches("/[\\w\\d]*/[^\\s]*")&& !link.contains("download")
                    &&!link.contains("client")){
                //如果有动态链接就获取并拼接
                String newLink=urlStr+link.substring(1,link.length()-1);
                list.add(newLink);
            }

        }
        return list;
    }

    private String getDomainForUrl(String urlStr){
        String domainUrl = null;
        if (urlStr == null) {
            return null;
        } else {
            Pattern p = Pattern.compile("(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)",Pattern.CASE_INSENSITIVE);
            Matcher matcher = p.matcher(urlStr);
            while(matcher.find()){
                domainUrl = matcher.group();
            }
            return domainUrl;
        }
    }
}
