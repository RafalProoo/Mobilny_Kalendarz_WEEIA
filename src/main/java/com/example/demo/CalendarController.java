package com.example.demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@RestController
public class CalendarController {

    public static final String version = "VERSION:1.0\r\n";
    private static final String beginVCalendar = "BEGIN:VCALENDAR\r\n";
    private static final String endVCalendar = "END:VCALENDAR\r\n";
    private static final String beginVEvent = "BEGIN:VEVENT\r\n";
    private static final String endVEvent = "END:VEVENT\r\n";
    private static final String startDate = "DTSTART;VALUE=DATE:";
    private static final String endDate = "DTEND;VALUE=DATE:";
    private static final String summary = "SUMMARY:";

    @GetMapping("/getSchedule")
    public void getSchedule(@RequestParam String year, @RequestParam String month) throws IOException {
        String address = "http://www.weeia.p.lodz.pl/pliki_strony_kontroler/kalendarz.php?rok=" + year + "&miesiac=" + month;

        Document doc = Jsoup.connect(address).get();
        Elements activeDays = doc.select("td.active");

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(beginVCalendar);
        stringBuilder.append(version);

        for (Element element : activeDays) {

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

            stringBuilder.append(beginVEvent);

            stringBuilder.append(startDate).append(dateFrom).append("\r\n");
            stringBuilder.append(endDate).append(dateTo).append("\r\n");

            String description = element.select("p").text();
            stringBuilder.append(summary).append(description).append("\r\n");

            stringBuilder.append(endVEvent);
        }

        stringBuilder.append(endVCalendar);

        String fileName = year + month + ".ics";

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(stringBuilder.toString());
        writer.close();
    }
}
