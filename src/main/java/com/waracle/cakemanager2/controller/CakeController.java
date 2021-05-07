package com.waracle.cakemanager2.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(path = "/")
public class CakeController {

    @GetMapping
    public List<String> getCakes() {
        return Arrays.asList("cake 1", "cake 2", "cake 3");
    }

    @GetMapping(path = "/cakes")
    public List<String> downloadCakes() {
        return Arrays.asList("cake 11", "cake 12", "cake 13");
    }

    @PostMapping(path = "/cakes")
    public String createCake(RequestBody requestBody) {
        return "cake 1";
    }
}
