package statistic;

import jsonParser.tiketJsonParser.Ticket;
import jsonParser.tiketJsonParser.TicketHolder;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;

import java.time.Duration;
import java.util.OptionalDouble;

public class FlightStatisticModule {

    public static String getAvg(TicketHolder ticketHolder, String originName, String destinationName) {

        OptionalDouble avg = ticketHolder
                .getTickets()
                .stream()
                .filter(ticket -> {
                    if (ticket.getOrigin_name().equals(originName)
                            && ticket.getDestination_name().equals(destinationName)) {
                        return true;
                    } else
                        return ticket.getOrigin_name().equals(destinationName)
                                && ticket.getDestination_name().equals(originName);
                }).map(Ticket::getDuration)
                .mapToDouble(Duration::getSeconds)
                .average();

        Duration avgDuration = Duration.ofSeconds((long) avg.getAsDouble());
        String avgDecimal = avgDuration.getSeconds() / 3600 + " часы " + (avgDuration.getSeconds() / 60) % 60 + " минуты";
        return avgDecimal;
    }

    public static String getPercentile(TicketHolder ticketHolder, double percentileValue, String originName, String destinationName) {

        double[] array = ticketHolder
                .getTickets()
                .stream()
                .filter(ticket -> {
                    if (ticket.getOrigin_name().equals(originName)
                            && ticket.getDestination_name().equals(destinationName)) {
                        return true;
                    } else
                        return ticket.getOrigin_name().equals(destinationName)
                                && ticket.getDestination_name().equals(originName);
                }).map(Ticket::getDuration)
                .mapToDouble(Duration::getSeconds)
                .toArray();


        Percentile percentile = new Percentile();
        percentile.setData(array);
        Duration percentileDuration = Duration.ofSeconds((long) percentile.evaluate(percentileValue));
        String procent = percentileDuration.getSeconds() / 3600 + " часы " + (percentileDuration.getSeconds() / 60) % 60 + " минуты";

        return procent;
    }
}
