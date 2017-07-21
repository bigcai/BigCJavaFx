package com.keydak.utils;

/**
 * Created by tandewei on 2016/10/10.
 */
public class StringUtil {
    public static String[] split(String regex, String in) {
        if (in == null) {
            return null;
        }
        return in.split(regex);
    }

    public static boolean hasLength(CharSequence str) {
        return (str != null && str.length() > 0);
    }

    public static boolean hasLength(String str) {
        return hasLength((CharSequence) str);
    }

    public static boolean isEmpty(String src) {
        return src == null || src.isEmpty();
    }

}
