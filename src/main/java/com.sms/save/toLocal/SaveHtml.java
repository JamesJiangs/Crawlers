package com.sms.save.toLocal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by james.jiang on 2017/6/13.
 */
public class SaveHtml {
    /**存储路径*/
    private String location;
    /**文件*/
    private String html;
    /**网络路径*/
    private String url;

    public SaveHtml(String location, String html,String url) {
        this.location = location;
        this.html = html;
        this.url=url;
    }

    private  String getName(){
//		Document documents= Jsoup.parse(html);
//
//		if(documents.select("title").first().text()!=null){
//			String name=documents.select("title").first().text();
//			System.out.println("名字"+name);
//			return name;
//		}

        if(url.split("/")!=null){
            String []str=url.split("/");
            return str[str.length-1];
        }

        return "demo" ;
    }

    private String makeDir(){
        String locations=location;

        if(this.getName()!=null){
            locations=location+"/"+this.getName();
        }
        File file=new File(locations);

        if(!file.exists()){
            boolean mkdir = file.mkdir();
            if(!mkdir){
                System.out.println("用于存储网页的文件夹创建失败！");
            }
        }
        return file.getPath();
    }

    public void save(){

        OutputStreamWriter outputStreamWriter = null;
        try {
            outputStreamWriter = new OutputStreamWriter(
                    new FileOutputStream(
                            new File(this.makeDir(),this.getName()+".html"), true), "utf-8");

            outputStreamWriter.write(html);
            outputStreamWriter.flush();

        } catch (IOException e) {
            System.out.println("com.starlight.reptile.download.toFile.SaveHTML:"+
                    "输出流异常！");
        }finally {
            try {
                if(outputStreamWriter!=null){
                    outputStreamWriter.close();
                }
            } catch (IOException e) {
                System.out.println("com.starlight.reptile.download.toFile.SaveHTML:"+
                        "输出流关闭异常！");
            }
        }

    }

}
