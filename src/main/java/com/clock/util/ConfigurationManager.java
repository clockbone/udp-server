package com.clock.util;

import com.clock.exception.UDPException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by qinjun on 2016/3/4.
 */
public class ConfigurationManager {

    private static Properties properties;

    static{
        properties = new Properties();
        try {
            properties.load(ConfigurationManager.class.getResourceAsStream("/config.properties"));
        } catch (FileNotFoundException e) {
            throw new UDPException("配置文件config.properties不存在！");
        } catch (IOException e) {
            throw new UDPException("读取配置文件异常！");
        }

    }

    /**
     * 获取配置文件属性值
     * @param key
     * @return
     */
    public static String getProperty(String key){
        if(properties != null){
            return properties.getProperty(key);
        }
        return null;
    }

    /**
     * 获取整数值属性
     * @param key
     * @return
     */
    public static Integer getIntegerValue(String key){
        if(properties == null) return null;
        String value = properties.getProperty(key);
        return value == null ? null : Integer.parseInt(value);
    }
}
