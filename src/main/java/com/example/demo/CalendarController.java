package com.example.demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CalendarController {

    @GetMapping("/getSchedule")
    public void getSchedule(@RequestParam String year, @RequestParam String month) throws IOException {
        String address = "http://www.weeia.p.lodz.pl/pliki_strony_kontroler/kalendarz.php?rok=" + year + "&miesiac=" + month;

        Document doc = Jsoup.connect(address).get();
        Elements activeDays = doc.select("td.active");

        for (Element element : activeDays){

            String dateFrom = year + month;
            String dateTo = year + month;

            String day = element.select("a").text();

            if (Integer.parseInt(day) < 10) {
                dateFrom += "0";
            }

            if ((Integer.parseInt(day) + 1) < 10) {
                dateTo += "0";
            }

            dateFrom += day;
            dateTo += (Integer.parseInt(day) + 1);

            System.out.println(dateFrom + " " + dateTo);
        }
    }
}
