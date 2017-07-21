package com.keydak.utils;

import java.io.*;
import java.util.Properties;

/**
 * Created by tandewei on 2016/9/21.
 */
public class PropertiesUtils {
    public void init(InputStream ins) {
        try {
            properties.load(new InputStreamReader(ins, "UTF-8"));
        } catch(IOException e) {
        } finally {
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getValue(String key){
        String str = properties.getProperty(key);
        return str;
    }

    public String getStringValue(String key, String defaultValue){
        String str = properties.getProperty(key);
        if (str == null) {
            return defaultValue;
        }
        return str;
    }

    public String[] getStringValues(String regex, String key){
        String str = getValue(key);
        if (str == null) {
            return null;
        }
        return StringUtil.split(regex, str);
    }

    public boolean getBooleanValue(String key, boolean defaultValue){
        String str = getValue(key);
        if (str == null) {
            return defaultValue;
        }
        try {
            return Boolean.valueOf(str);
        } catch (Exception e) {
        }
        return defaultValue;
    }

    public int getIntValue(String key, int defaultValue){
        String str = getValue(key);
        if (str == null) {
            return defaultValue;
        }
        try {
            return Integer.valueOf(str);
        } catch (Exception e) {
        }
        return defaultValue;
    }

    public long getLongValue(String key, long defaultValue){
        String str = getValue(key);
        if (str == null) {
            return defaultValue;
        }
        try {
            return Long.valueOf(str);
        } catch (Exception e) {
        }
        return defaultValue;
    }

    public short getShortValue(String key, short defaultValue){
        String str = getValue(key);
        if (str == null) {
            return defaultValue;
        }
        try {
            return Short.valueOf(str);
        } catch (Exception e) {
        }
        return defaultValue;
    }

    public void setProperties(String key, String newValue){
        this.properties.setProperty(key, newValue);
        return ;
    }

    public void store(String storePath){
        FileOutputStream file = null;
        try {
            file = new FileOutputStream(storePath);
            this.properties.store(file, "系统配置修改"); //这句话表示重新写入配置文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    Properties properties = new Properties();
}
