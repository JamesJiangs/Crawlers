package com.sms.store;

import java.util.HashMap;

/**
 * Created by james.jiang on 2017/6/14.
 */
public class NormData{

    private HashMap<String,Boolean> hashMap;

    public NormData(){
        this.hashMap=new HashMap<String, Boolean>();
    }
    public HashMap<String, Boolean> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<String, Boolean> hashMap) {
        this.hashMap = hashMap;
    }

    public void add(String url) {
        hashMap.put(url,false);
    }

    public Boolean isContain(String url){
        return hashMap.containsKey(url);
    }

    public Boolean isUse(String url){
        if (url==null)return false;
        return hashMap.get(url);
    }

    public void setTrue(String url){
        hashMap.put(url,true);
    }




}



