package com.sms.save.download;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by james.jiang on 2017/6/13.
 * 下载html转为字符串
 */
public class DownLoadHtml {

    public String downloadHtml(HttpURLConnection httpURLConnection){

        InputStream inputStream= null;
        BufferedReader bufferedReader=null;
        String formartCode="utf-8";
        StringBuilder res=null;
        try {
            inputStream = httpURLConnection.getInputStream();
            bufferedReader=new BufferedReader(new InputStreamReader(inputStream,formartCode));
            String line;
            res=new StringBuilder();

            while ((line=bufferedReader.readLine())!=null){
                res.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();

        }finally {
            try {
                if(bufferedReader!=null){
                    bufferedReader.close();
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return  res.toString();
    }
}
