package com.sms.save.download;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by james.jiang on 2017/6/13.
 * 下载图片
 */
public class DownLoadImage {

    private String htmlStr;

    public DownLoadImage(String htmlStr) {
        this.htmlStr = htmlStr;
    }

    public List<String> getImageSrc(){

		/*图片url集合*/
        List<String> listImageSrc = new ArrayList<String>();

        Document document= Jsoup.parse(htmlStr);

        Elements elements=document.select("img[src~=(?i)\\.(jpg|gif|png|jpe?g)]");
        for (Element element:
                elements) {
            //获取图片路径
            String srcImage=element.attr("src");

            System.out.println("图片路径："+srcImage);
            listImageSrc.add(srcImage);


        }
        return listImageSrc;
    }

    public List<String> getImageName() {

        List<String> listImageName = new ArrayList<String>();
        Document document = Jsoup.parse(htmlStr);
        Elements elements=document.select("img[src~=(?i)\\.(jpg|gif|png|jpe?g)]");

        for (Element element :
                elements) {
            //获取图片路径
            String srcImage = element.attr("src");
            String imageName = srcImage.substring(srcImage.lastIndexOf("/") + 1, srcImage.length());
            int length=0;

            //截取图片最后路径长度
            if(imageName.contains(".png")){

                length=imageName.lastIndexOf(".png")+4;

            }else if(imageName.contains(".jpg")){

                length=imageName.lastIndexOf(".jpg")+4;

            }else if(imageName.contains(".gif")){

                length=imageName.lastIndexOf(".gif")+4;
            }else if(imageName.contains(".jpeg")){

                length=imageName.lastIndexOf(".jpeg")+5;
            }

            System.out.println("length:"+length);
            String imageName1=imageName.substring(0,length);

            System.out.println("图片名称：" + imageName1);
            listImageName.add(imageName1);

        }
        return listImageName;
    }
}
