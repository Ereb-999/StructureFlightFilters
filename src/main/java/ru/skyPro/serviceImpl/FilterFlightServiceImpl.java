package ru.skyPro.serviceImpl;

import ru.skyPro.models.Flight;
import ru.skyPro.models.Segment;
import ru.skyPro.service.FilterFlightService;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FilterFlightServiceImpl implements FilterFlightService {

    @Override
    public List<Flight> removeFlightUpToTheCurrentPointInTime(List<Flight> flightBuilder) {
        List<Flight> filteredFlights = new ArrayList<>(flightBuilder);
        filteredFlights.removeIf(flight -> flight.getSegments().get(0).getDepartureDate().isBefore(LocalDateTime.now()));
        return filteredFlights;
    }
    @Override
    public List<Flight> removeSegmentsWithTheSameDateEarlierThanTheSplitOnes(List<Flight> flightBuilder) {
        List<Flight> filteredFlights = new ArrayList<>(flightBuilder);
        filteredFlights.removeIf(flight -> {
            for (Segment segment : flight.getSegments()) {
                if (segment.getArrivalDate().isBefore(segment.getDepartureDate())) {
                    return true;
                }
            }
            return false;
        });
        return filteredFlights;
    }

    @Override
    public List<Flight> removeTimeSpentOnEarthExceedsTwoHours(List<Flight> flightBuilder) {
        List<Flight> filteredFlights = new ArrayList<>(flightBuilder);
        filteredFlights.removeIf(flight -> {
            Duration totalGroundTime = Duration.ZERO;
            List<Segment> segments = flight.getSegments();
            for (int i = 0; i < segments.size() - 1; i++) {
                totalGroundTime = totalGroundTime.plus(Duration.between(segments.get(i)
                        .getArrivalDate(), segments.get(i + 1).getDepartureDate()));
            }
            return totalGroundTime.toHours() > 2;
        });

        return filteredFlights;
    }
}
