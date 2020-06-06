package com.xiaoba.util;

import com.xiaoba.constans.PathContants;

public class PathLoadUtil {
    public static String loadImages(String pathInDb){
        return PathContants.IMG_PATH+pathInDb;
    }

    public static String loadEssay(String pathInDb){
        return PathContants.ESSAY_PATH+pathInDb;
    }


    public static String loadQuestion(String pathInDb){
        return PathContants.QUESTION_PATH+pathInDb;
    }
}
