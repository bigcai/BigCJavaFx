package com.keydak.utils;

import java.util.HashMap;

/**
 * User: caisz
 * Date: 2017/5/5
 * Time: 15:46
 * Description:
 */
public class Paramap extends  HashMap<String, Object>{

    public Paramap(){
    }

    public static Paramap create(){
        return new Paramap();
    }
    public Paramap putParam(String key, Object value) {
        this.put(key, value);
        return this;
    }
}
