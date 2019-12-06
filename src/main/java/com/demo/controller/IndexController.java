package com.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: beibe
 * @date: 2019/12/6 10:38
 * 设置默认首页
 * https://www.cnblogs.com/itshare/archive/2018/04/02/8694595.html
 */

@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping("/")
    public String Index() {
        return "forward:/poker.html";
    }
}
