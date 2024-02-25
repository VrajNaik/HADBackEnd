package com.example.EmotionSync.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adminDetails")
public class adminDetailsControllerDemo {
    @GetMapping("/check")
    public String testApi(){
        return "Hello";
    }
}
