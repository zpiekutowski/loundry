package com.zbiir.loundry.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TestControler {

    @GetMapping("/test")
    public String test(){
        System.out.println("Tu jestem");
        return "SPRING BOOT IS OK";

    }


}
