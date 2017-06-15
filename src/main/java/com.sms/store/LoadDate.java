package com.sms.store;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by james.jiang on 2017/6/14.
 */
public class LoadDate implements RepoDate{
    private Queue<String> queue;

    public LoadDate(){
        this.queue=new LinkedList<String>();
    }
    public Queue<String> getQueue() {
        return queue;
    }

    public void setQueue(Queue<String> queue) {
        this.queue = queue;
    }

    public void add(String url) {
              queue.offer(url);
    }

    public String getUrl() {
        return  this.queue.poll();
    }
}
