package com.example.s31641tpo05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NumberSystemController {
    @PostMapping("/number-base-conversion")
    @ResponseBody
    public String convert(
            @RequestParam String value,
            @RequestParam int fromBase,
            @RequestParam int toBase
    ){
        if (fromBase < 2 || fromBase > 100 || toBase < 2 || toBase > 100) {
            return "Base must be in the range 2 to 100";
        }

        try {
            int decimalValue = Integer.parseInt(value, fromBase);
            String convertedValue = Integer.toString(decimalValue, toBase).toUpperCase();

            String binary = Integer.toBinaryString(decimalValue);
            String octal = Integer.toOctalString(decimalValue);
            String decimal = String.valueOf(decimalValue);
            String hex = Integer.toHexString(decimalValue).toUpperCase();

            return String.format("Converted: %s [BIN: %s, OCT: %s, DEC: %s, HEX: %s]",
                    convertedValue, binary, octal, decimal, hex);
        } catch (NumberFormatException e) {
            return "<span style='color: red;'>Invalid numeric value for the given base</span>";
        }
    }
}
