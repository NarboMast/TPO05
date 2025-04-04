package com.example.s31641tpo05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class TimeController {
    public static final ZoneId defaultTimeZone = ZoneId.systemDefault();
    public static final String defaultTimeFormatter = "hh:mm:ss.SSSS yyyy/MM/dd";

    @PostMapping("/current-time")
    @ResponseBody
    public String current_time(
            @RequestParam(required = false) String timeZone,
            @RequestParam(required = false) String format
            ) {
        ZonedDateTime zdt;
        DateTimeFormatter formatter;
        if(!timeZone.isEmpty() || !format.isEmpty()) {
            try{
                zdt = ZonedDateTime.now(ZoneId.of(timeZone));
                formatter = DateTimeFormatter.ofPattern(format);
                return timeZone + ": " + zdt.format(formatter);
            } catch (Exception e){
                zdt = ZonedDateTime.now(defaultTimeZone);
                formatter = DateTimeFormatter.ofPattern(defaultTimeFormatter);
                return "Invalid time zone provided. Defaulting to system time zone" +
                        "(" + defaultTimeZone +  "): " +
                        zdt.format(formatter);
            }
        } else {
            zdt = ZonedDateTime.now(defaultTimeZone);
            formatter = DateTimeFormatter.ofPattern(defaultTimeFormatter);
            return "Default time provided" + "(" + defaultTimeZone +  "): " +
                    zdt.format(formatter);
        }
    }

    @GetMapping("/current-year")
    @ResponseBody
    public String currentYear() {
        return Year.now().toString();
    }
}
