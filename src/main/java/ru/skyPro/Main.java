package ru.skyPro;

import ru.skyPro.models.Flight;
import ru.skyPro.serviceImpl.FilterFlightServiceImpl;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        allFlightList();
        removeFlyingToTheCurrentPointInTime();
        removeSegmentsWithTheSameDateEarlierThanTheSplitOnes();
        removeTimeSpentOnEarthExceedsTwoHours();
    }
    private static final FilterFlightServiceImpl serviceImpl = new FilterFlightServiceImpl();
    private static final List<Flight> flightList = FlightBuilder.createFlights();

    private static void allFlightList() {
        System.out.println(flightList);
        border();
    }
    private static void removeFlyingToTheCurrentPointInTime() {
        System.out.println(serviceImpl.removeFlightUpToTheCurrentPointInTime(flightList));
        border();
    }

    private static void removeSegmentsWithTheSameDateEarlierThanTheSplitOnes() {
        System.out.println(serviceImpl.removeSegmentsWithTheSameDateEarlierThanTheSplitOnes(flightList));
        border();
    }

    private static void removeTimeSpentOnEarthExceedsTwoHours() {
        System.out.println(serviceImpl.removeTimeSpentOnEarthExceedsTwoHours(flightList));
        border();
    }

    private static void border() {
        System.out.println("----------------------------------------");
    }
}
