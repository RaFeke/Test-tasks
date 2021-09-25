package main;

import jsonParser.tiketJsonParser.TicketHolder;
import jsonParser.tiketJsonParser.TicketJsonParserModule;
import statistic.FlightStatisticModule;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            TicketHolder ticketHolder;
            while (true) {
                System.out.println("Введите путь к файлу:");
                try {
                    String path = reader.readLine();
                    //path = "src/main/resources/tickets.json";
                    ticketHolder = TicketJsonParserModule
                            .parse(path);
                    break;
                } catch (Exception e) {
                    System.out.println("Неверный путь к файлу.");
                }
            }

            System.out.println("Среднее время полета между городами Владивосток и Тель-Авив : "
                    + FlightStatisticModule.getAvg(ticketHolder,
                    "Владивосток",
                    "Тель-Авив"));

            System.out.println("90-й процентиль времени полета между городами  Владивосток и Тель-Авив : "
                    + FlightStatisticModule.getPercentile(ticketHolder,
                    90,
                    "Владивосток",
                    "Тель-Авив"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
