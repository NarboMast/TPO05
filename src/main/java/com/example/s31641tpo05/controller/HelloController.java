package com.example.s31641tpo05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("/first")
    @ResponseBody
    public String first(){
        return "First method";
    }

    @GetMapping("/second")
    @ResponseBody
    public String second(){
        return "Second method";
    }

    @GetMapping("/hello2")
    @ResponseBody
    public String hello2(@RequestParam("name") String text){
        return "Hi " + text + "!";
    }

    @GetMapping("/hello3")
    @ResponseBody
    public String hello3(@RequestParam(required = false, defaultValue = "Anonymous") String name){
        return "Good morning, " + name + "!";
    }

    @PostMapping("/calc")
    @ResponseBody
    public String calc(@RequestParam int val1, @RequestParam int val2, @RequestParam String operator){
        return switch (operator){
            case "+" -> "<h1>" + (val1 + val2) + "</h1>";
            case "-" -> "<h1>" + (val1 - val2) + "</h1>";
            case "*" -> "<h1>" + (val1 * val2) + "</h1>";
            case "/" -> "<h1>" + (val1 / val2) + "</h1>";
            default -> "Error";
        };
    }
}
