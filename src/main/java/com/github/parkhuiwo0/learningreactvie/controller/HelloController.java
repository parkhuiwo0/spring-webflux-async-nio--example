package com.github.parkhuiwo0.learningreactvie.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping
    public String hello(@RequestParam(required = false, defaultValue = "") String name) {
        return name.equals("") ? "Hey!" : "Hey, " + name + "!";
    }
}
