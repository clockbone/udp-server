package com.clock.domain;

import java.net.InetAddress;

/**
 * Created by qinjun on 2016/3/4.
 */
public class ReceiveData {

    private byte[] data;

    private InetAddress address;


    public ReceiveData(byte[] data,InetAddress address) {
        this.address = address;
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }
}
