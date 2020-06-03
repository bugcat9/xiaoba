package com.xiaoba.util;

public class PathLoadUtil {
    /**
     * 图片路径
     */
    public final static String IMG_PATH="http://39.99.203.80:8080/images/";

    /**
     *文章路径
     */
    public final static String ESSAY_PATH="http://39.99.203.80:8080/md/";

    public static String loadImages(String pathinDb){
        return IMG_PATH+pathinDb;
    }

    public static String loadEssay(String pathinDb){
        return ESSAY_PATH+pathinDb;
    }

}
