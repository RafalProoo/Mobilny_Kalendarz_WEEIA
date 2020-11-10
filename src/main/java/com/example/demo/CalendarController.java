package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalendarController {

    @GetMapping ("/getSchedule")
    public void getSchedule(@RequestParam int month, @RequestParam int year){
        
    }
}
