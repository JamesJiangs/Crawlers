package com.sms.save.toLocal;

import com.sms.entity.Crawler;
import com.sms.proxy.HttpProxy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.List;

/**
 * Created by james.jiang on 2017/6/13.
 */
public class SaveImage {

    private Crawler crawler;
    /**文件名*/
    private String html;

    /**图片url集合*/
    private List<String> listImageSrc;
    /**图片名称集合*/
    private List<String> listImageName;


    public SaveImage(Crawler crawler, List<String> listImageSrc, List<String> listImageName, String html) {
        this.crawler=crawler;
        this.listImageSrc = listImageSrc;
        this.listImageName = listImageName;
        this.html=html;
    }

    /**
     * 获取文件的名字
     * @return name 文件夹名
     */
    private String getName(){
        Document documents= Jsoup.parse(html);
        String name=documents.select("title").first().text();
        System.out.println("名字"+name);
        return name;
    }

    /**
     * 创建本地目录
     * @return  目录路径
     */
    private String makeDir(){
        File file=new File(crawler.getLocation()+"/"+this.getName());
        if(!file.exists()){
            boolean mkdir = file.mkdir();
            if(!mkdir){
                System.out.println("用于存储图片文件夹创建失败！");
            }
        }
        return file.getPath();
    }

    /**
     * 下载图片到本地
     */
    public void save(){


        for (int i=0;i<listImageSrc.size();i++) {
            try {
                String imageSrc=listImageSrc.get(i);
                String imageName=listImageName.get(i);
                System.out.println("图片存放地址："+imageSrc);

                HttpProxy httpProxyss=new HttpProxy();

                HttpURLConnection httpURLConnections=httpProxyss.createconnection(crawler);
                InputStream inputStream=httpURLConnections.getInputStream();

                OutputStream outputStream=new FileOutputStream(new File(this.makeDir(),imageName));
                byte[] buf = new byte[inputStream.available()];
                int l;
                while((l=inputStream.read(buf))!=-1){

                    outputStream.write(buf, 0, l);
                }
                outputStream.flush();
            }catch (Exception e){
                System.out.println("com.starlight.reptile.download.toFile.SaveImage："+
                        "图片下载IO异常");
            }

        }
    }


}
