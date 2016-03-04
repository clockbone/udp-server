package com.clock.service;

import com.clock.domain.EvenCache;
import com.clock.domain.ReceiveData;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by qinjun on 2016/3/4.
 */
public class EvenReceiver extends Thread {

    private final static int RECEIVE_BUFFER_SIZE = 8*1024;

    private DatagramSocket socket;

    private EvenCache evenCache;

    private volatile Boolean stop=true;

    public EvenReceiver(DatagramSocket socket,EvenCache evenCache){
        this.socket = socket;
        this.evenCache = evenCache;
    }

    @Override
    public void run() {
        byte[] revBuffer=new byte[RECEIVE_BUFFER_SIZE];
        DatagramPacket packet = new DatagramPacket(revBuffer,revBuffer.length);
        while(!stop){
            try {
                //receive()将一直等待（该方法会阻塞调用该方法的线程），直到收到一个数据报为止
                socket.receive(packet);
                byte [] data = new byte[packet.getLength()];
                //将接收到的数据拷贝到data字节数组中
                System.arraycopy(packet.getData(), 0, data, 0, packet.getLength());
                ReceiveData receiveData = new ReceiveData(data,packet.getAddress());
                //将接收到的数据放到共公缓冲对象
                evenCache.put(receiveData);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public void quit(){
        this.stop = true;
        this.interrupt();
    }
}
