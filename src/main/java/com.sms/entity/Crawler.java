package com.sms.entity;

/**
 * Created by james.jiang on 2017/6/12.
 * 相关输入信息实体
 */
public class Crawler {
    private String urlStr;
    private String location;
    private String host;
    private int port;
    private String formatCode;

    public Crawler(){}

    public Crawler(String host,int port,String urlStr,String location){
        this.host=host;
        this.port=port;
        this.location=location;
        this.urlStr=urlStr;
    }

    public Crawler(String host,int port,String urlStr,String location,String formatCode){
        this.host=host;
        this.port=port;
        this.location=location;
        this.urlStr=urlStr;
        this.formatCode=formatCode;
    }

    public String getUrlStr() {
        return urlStr;
    }

    public void setUrlStr(String urlStr) {
        this.urlStr = urlStr;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getFormatCode() {
        return formatCode;
    }

    public void setFormatCode(String formatCode) {
        this.formatCode = formatCode;
    }
}
