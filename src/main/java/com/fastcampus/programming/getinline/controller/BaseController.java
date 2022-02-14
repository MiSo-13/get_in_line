package com.fastcampus.programming.getinline.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  // 컨트롤러 빈으로 등록됨
public class BaseController implements ErrorController {
    @GetMapping("/")
    public String root() {
        return "index";
    }

    @RequestMapping("/error")  // 기본 error 페이지를 우리가 만들겠다 > implements ErrorController
    public String error() {
        return "error";
    }
}
