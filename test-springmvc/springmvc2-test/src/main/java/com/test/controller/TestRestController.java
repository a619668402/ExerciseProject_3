package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TestRestController {

    @RequestMapping("test_rest")
    @ResponseBody
    public Map<String, String> test() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("username", "zhangsan");
        map.put("password", "123");
        return map;
    }

    @RequestMapping("add/{a}/{b}")
    public String add(@PathVariable("a") int aa, @PathVariable("b") int bbb, Model model) {
        model.addAttribute("msg", aa + bbb);
//        return "hello";
        return "redirect:/h1";
    }

    @RequestMapping("test2")
    public String test2(@RequestParam("username") String name, ModelMap modelMap) {
        modelMap.addAttribute("msg", "modelMap");
        return "hello";
    }
}
