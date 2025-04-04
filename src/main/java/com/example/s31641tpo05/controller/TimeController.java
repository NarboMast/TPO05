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
    private static final String DEFAULT_TIME_ZONE = "Europe/Warsaw";
    private static final String DEFAULT_TIME_FORMAT = "hh:mm:ss.SSSS yyyy/MM/dd";

    @GetMapping("/current-time")
    @ResponseBody
    public String current_time(
            @RequestParam(name = "timeZone", required = false, defaultValue = DEFAULT_TIME_ZONE) String timeZone,
            @RequestParam(name = "timeFormat", required = false, defaultValue = DEFAULT_TIME_FORMAT) String format
            ) {
        String res;
        try{
            ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of(timeZone));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return timeZone + ": " + zdt.format(formatter);
        } catch (Exception e){
            res = "<span style='color: red;'>Invalid time zone provided. Defaulting to system time zone</span><br>";
        }
        return res + "(" + DEFAULT_TIME_ZONE +  "): " +
                getDefaultParameters();
    }

    public String getDefaultParameters(){
        return ZonedDateTime.now(ZoneId.of(DEFAULT_TIME_ZONE))
                .format(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT));
    }

    @GetMapping("/current-year")
    @ResponseBody
    public String currentYear() {
        return Year.now().toString();
    }
}
