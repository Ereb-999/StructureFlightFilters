import org.junit.jupiter.api.Test;
import ru.skyPro.models.Flight;
import ru.skyPro.models.Segment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightModelTest {
    private static final LocalDateTime DDA = LocalDateTime.now().plusDays(6);
    private static final LocalDateTime AD = DDA.plusHours(2);

    @Test
    void testGetSegments() {
        List<Segment> segments = Arrays.asList(
                new Segment(DDA, AD),
                new Segment(DDA, AD),
                new Segment(DDA, AD)
        );

        Flight flight = new Flight(segments);

        assertEquals(segments, flight.getSegments());
    }

    @Test
    void testToStringSegment() {
        List<Segment> segments = Arrays.asList(
                new Segment(DDA, AD),
                new Segment(DDA, AD)
        );

        Flight flight = new Flight(segments);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String expected = "[" + DDA.format(fmt) +
                "|" + AD.format(fmt) + "]";
        assertEquals(expected + " " + expected, flight.toString());
    }
}
