package com.xiaoba.util;

import java.util.HashMap;

/**
 * @author zhouning
 */
public class Result extends HashMap<String, Object> {
    public Result() {
//        put("code", 200);
//        put("msg", "success");
    }

    public Result(int initialCapacity){
        super(initialCapacity);
    }

    public static Result ok() {
        return new Result();
    }

    @Override
    public Result put(String key, Object value) {
         super.put(key, value);
         return this;
    }
}
