package com.clock.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by qinjun on 2016/3/4.
 */
public class EvenCache {

    private final static int  CACHE_SIZE=1024*300;

    //现成安全的队列
    public BlockingQueue<ReceiveData> blockingQueue = new LinkedBlockingQueue<ReceiveData>(CACHE_SIZE) ;


    public List<ReceiveData> batchGet(){
        List<ReceiveData> list = new ArrayList<ReceiveData>();
        blockingQueue.drainTo(list,1000);
        return list;
    }

    public void put(ReceiveData receiveData){
        try {
            blockingQueue.put(receiveData);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
