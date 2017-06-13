package com.sms.save.toLocal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by james.jiang on 2017/6/13.
 */
public class SaveText {
    /**网页文本信息*/
    private String text;
    /**存储的路径*/
    private String location;
    /**存储的名字*/
    private String name;

    public SaveText(String text, String location, String name) {
        this.text = text;
        this.location=location;
        this.name=name;
    }

    /**
     * 创建本地目录
     * @return  目录路径
     */
    private String makeDir(){
        File file=new File(location+"/"+this.getName());
        if(!file.exists()){
            boolean mkdir = file.mkdir();
            if(!mkdir){
                System.out.println("用于存储图片文件夹创建失败！");
            }
        }
        return file.getPath();
    }

    /**
     * 从网页中获取完整的文本到本地
     */
    public void save(){

        BufferedWriter bufferedWriter=null;
        try {
            bufferedWriter=new BufferedWriter(new FileWriter(new File(location,name+".txt"),true));
            bufferedWriter.write(text);
            bufferedWriter.flush();
        } catch (IOException e) {

            System.out.println("com.starlight.reptile.download.toFile.SaveText："+
                    "文本输入流出现问题！");
        }finally {
            try {
                if(bufferedWriter!=null){
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                System.out.println("com.starlight.reptile.download.toFile.SaveText："
                        +"文本输出流不能关闭！");
            }
        }
    }


    public String getName() {
        return name;
    }
}
