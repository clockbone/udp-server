package com.clock.service;

import com.clock.domain.EvenCache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by qinjun on 2016/3/4.
 */
public class MessageHandler extends Thread {
    private EvenCache evenCache;

    private ExecutorService service = Executors.newFixedThreadPool(3);

    private volatile boolean stop = false;

    public MessageHandler(EvenCache evenCache){
        this.evenCache = evenCache;
    }
    @Override
    public void run() {
        //deal with evencache
        service.execute(new Runnable(){
            public void run() {
                //do something...
            }
        });
    }

    public void quit(){
        this.stop = true;
        this.interrupt();
        this.service.shutdown();
    }
}
