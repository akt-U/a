package com.techacademy.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopController {
    /** トップページを表示 */
    @GetMapping("/")
    public String getTop() {
        // top.htmlに画面遷移
        return "top";
    }
}
