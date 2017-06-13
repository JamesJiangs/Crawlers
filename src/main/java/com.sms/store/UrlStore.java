package com.sms.store;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by james.jiang on 2017/6/12.
 */
public class UrlStore {
    private HashMap<String ,Boolean> hashMap;
    private Queue<String> queue;

    public UrlStore() {
        this.hashMap=new HashMap<String, Boolean>();
        this.queue=new LinkedList<String>();
    }

    public void addQueue(String urlStr){
        if (urlStr!=null){
            queue.offer(urlStr);
        }
    }

    public void addHashMap(String urlStr){
        if (urlStr!=null){
            hashMap.put(urlStr,true);
        }
    }

    public boolean isRepetition(String urlStr){
        return hashMap.containsKey(urlStr);
    }

    public void hasBeUsed(String urlStr){
            hashMap.put(urlStr,false);
    }

   public Boolean isContains(String urlStr){
        return hashMap.get(urlStr);
   }

    public HashMap<String, Boolean> getCrawl() {
        return hashMap;
    }

    public Queue<String> getStore() {
        return queue;
    }
}
