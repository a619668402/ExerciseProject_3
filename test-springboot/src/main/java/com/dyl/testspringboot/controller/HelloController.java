package com.dyl.testspringboot.controller;

import com.dyl.testspringboot.pojo.Result;
import com.dyl.testspringboot.pojo.User;
import com.dyl.testspringboot.utils.ResultUtil;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class HelloController {

    @RequestMapping(value = "test")
    public Result hello(@Valid User user, BindingResult bindResult) {
//        for (ObjectError error : bindResult.getAllErrors()) {
//            System.err.println(error.getObjectName() + " : " + error.getDefaultMessage());
//        }
        if (bindResult.hasErrors()) {
            return ResultUtil.error(400, bindResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).reduce((m1, m2) -> m1 + "; " + m2).orElse("参数有误"));
        } else {
            return ResultUtil.success();
        }
    }

    @GetMapping("test1")
    public Result test1() {
        return ResultUtil.error(500, "服务器错误");
    }
}
