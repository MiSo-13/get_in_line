package com.fastcampus.programming.getinline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  // 컨트롤러 빈으로 등록됨
public class BaseController {
    @GetMapping("/")
    public String root(){
        return "index";
    }
}
