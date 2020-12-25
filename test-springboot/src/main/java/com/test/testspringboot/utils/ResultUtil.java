package com.test.testspringboot.utils;

import com.test.testspringboot.pojo.Result;

public class ResultUtil {

    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(200);
        result.setData(object);
        result.setMsg("success");
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setMsg(msg);
        result.setCode(code);
        result.setData(null);
        return result;
    }
}
