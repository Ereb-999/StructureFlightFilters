package ru.skyPro.service;

import ru.skyPro.models.Flight;

import java.util.List;

public interface FilterFlightService {
    List<Flight> removeFlightUpToTheCurrentPointInTime(List<Flight> flightBuilder);

    List<Flight> removeSegmentsWithTheSameDateEarlierThanTheSplitOnes(List<Flight> flightBuilder);

    List<Flight> removeTimeSpentOnEarthExceedsTwoHours(List<Flight> flightBuilder);
}
