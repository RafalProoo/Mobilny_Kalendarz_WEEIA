# Mobilny_Kalendarz_WEEIA
## Dokumentacja
### Endpointy
* http://localhost:8080/getSchedule?year={year}&month={month}
##### Parametry
year - rok w formacie yyyy

month - miesiąc w formacie MM
##### Przykład
* http://localhost:8080/getSchedule?year=2020&month=03

Zwróci: plik w formacie .ics z wydarzeniami z marca 2020 roku.
