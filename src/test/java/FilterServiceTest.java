
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import ru.skyPro.models.Flight;
import ru.skyPro.models.Segment;
import ru.skyPro.service.FilterFlightService;
import ru.skyPro.serviceImpl.FilterFlightServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FilterServiceTest {

    private static final LocalDateTime DDA = LocalDateTime.now().plusDays(1);
    private static final LocalDateTime DepartureDateNotActual = LocalDateTime.now().minusDays(1);
    private static final LocalDateTime AD = DDA.plusHours(4);
    private static final LocalDateTime ArrivalDateNotCorrect = LocalDateTime.now().minusHours(4);
    private Segment segmentCorrect;
    private Segment segmentNotCorrect1;
    private Segment segmentNotCorrect2;
    private final List<Flight> flightList = new ArrayList<>();
    private final List<Segment> segmentList1 = new ArrayList<>();
    private final List<Segment> segmentList2 = new ArrayList<>();
    private FilterFlightService filterService;

    @BeforeEach
    public void setup() {
        filterService = new FilterFlightServiceImpl();
        segmentCorrect = new Segment(DDA, AD);
        segmentNotCorrect1 = new Segment(DepartureDateNotActual, AD);
        segmentNotCorrect2 = new Segment(DDA, ArrivalDateNotCorrect);
    }

    @Test
    public void testRemoveFlightUpToTheCurrentPointInTime() {
        segmentList1.add(segmentCorrect);
        segmentList2.add(segmentNotCorrect1);
        flightList.add(new Flight(segmentList1));
        flightList.add(new Flight(segmentList2));

        assertEquals(2, flightList.size());
        List<Flight> result = filterService.removeFlightUpToTheCurrentPointInTime(flightList);
        assertEquals(1, result.size());
        assertEquals(DDA, result.get(0).getSegments().get(0).getDepartureDate());
    }

    @Test
    public void testRemoveSegmentsWithTheSameDateEarlierThanTheSplitOnes() {
        segmentList1.add(segmentCorrect);
        segmentList2.add(segmentNotCorrect2);
        flightList.add(new Flight(segmentList1));
        flightList.add(new Flight(segmentList2));

        assertEquals(2, flightList.size());
        List<Flight> result = filterService.removeSegmentsWithTheSameDateEarlierThanTheSplitOnes(flightList);
        assertEquals(1, result.size());
        assertEquals(AD, result.get(0).getSegments().get(0).getArrivalDate());
    }

    @Test
    public void testRemoveTimeSpentOnEarthExceedsTwoHours() {
        segmentList1.add(segmentCorrect);
        segmentList2.add(segmentNotCorrect2);
        segmentList2.add(segmentNotCorrect1);
        flightList.add(new Flight(segmentList1));
        flightList.add(new Flight(segmentList2));

        assertEquals(2, flightList.size());
        List<Flight> result = filterService.removeTimeSpentOnEarthExceedsTwoHours(flightList);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getSegments().size());
        assertEquals(AD, result.get(0).getSegments().get(0).getArrivalDate());
    }
}
