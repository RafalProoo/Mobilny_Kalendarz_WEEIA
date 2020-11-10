package com.example.demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CalendarController {

    @GetMapping("/getSchedule")
    public void getSchedule(@RequestParam int month, @RequestParam int year) throws IOException {
        String address = "http://www.weeia.p.lodz.pl/pliki_strony_kontroler/kalendarz.php?rok=" + year + "&miesiac=" + month;

        Document doc = Jsoup.connect(address).get();

        System.out.println(doc.toString());
    }
}
