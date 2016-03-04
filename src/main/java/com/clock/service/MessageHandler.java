package com.clock.service;

import com.clock.domain.EvenCache;

/**
 * Created by qinjun on 2016/3/4.
 */
public class MessageHandler extends Thread {
    private EvenCache evenCache;

    private volatile boolean stop = false;

    public MessageHandler(EvenCache evenCache){
        this.evenCache = evenCache;
    }
    @Override
    public void run() {
        //deal with evencache
    }

    public void quit(){
        this.stop = true;
        this.interrupt();
        //this.service.shutdown();
    }
}
