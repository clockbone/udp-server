package com.clock;

import com.clock.domain.EvenCache;
import com.clock.exception.UDPException;
import com.clock.service.EvenReceiver;
import com.clock.service.MessageHandler;
import com.clock.util.ConfigurationManager;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by qinjun on 2016/3/4.
 */
public class Bootstrap {

    public static void main(String[] args) {

       /* ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        //MainService mainService = context.getBean(MainService.class);
       // mainService.service();*/

        //udp socket监听
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket(ConfigurationManager.getIntegerValue("receive-port"));
            socket.setReceiveBufferSize(10240);
        } catch (SocketException e) {
            throw  new UDPException("udp start exception!",e);
        }
        EvenCache evenCache = new EvenCache();
        EvenReceiver evenReceiver = new EvenReceiver(socket,evenCache);
        final ExecutorService executorService = Executors.newFixedThreadPool(3);
        for(int i=0;i<3;i++){
            //启动3个线程 接收数据
            executorService.execute(evenReceiver);
        }
        final MessageHandler messageHandler = new MessageHandler(evenCache);
        messageHandler.start();

        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run(){
                executorService.shutdown();
                messageHandler.quit();

            }
        });


    }

}
