package com.zbiir.loundry.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Main {

    @GetMapping("")
    public String main(){

        return "Hello world";
    }
}
