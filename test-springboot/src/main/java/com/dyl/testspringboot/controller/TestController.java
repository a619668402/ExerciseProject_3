package com.dyl.testspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
public class TestController {

    @RequestMapping("hello")
    public String test(Model model) {
        model.addAttribute("msg", "hello thymeleaf");
        model.addAttribute("arr", Arrays.asList(1, 2, 3, 4, 5));
        return "hello";
    }

}
