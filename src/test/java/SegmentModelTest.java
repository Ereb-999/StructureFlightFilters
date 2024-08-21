import org.junit.jupiter.api.Test;
import ru.skyPro.models.Segment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class SegmentModelTest {
    @Test
    public void testGetDepartureDate() {
        LocalDateTime departureDate = LocalDateTime.of(2024, 6, 6, 6, 6);
        LocalDateTime arrivalDate = LocalDateTime.of(2024, 7, 7, 7, 0);
        Segment segment = new Segment(departureDate, arrivalDate);

        LocalDateTime result = segment.getDepartureDate();

        assertEquals(departureDate, result);
    }

    @Test
    public void testGetArrivalDate() {
        LocalDateTime departureDate = LocalDateTime.of(2024, 3, 4, 12, 0);
        LocalDateTime arrivalDate = LocalDateTime.of(2024, 8, 2, 4, 0);
        Segment segment = new Segment(departureDate, arrivalDate);

        LocalDateTime result = segment.getArrivalDate();

        assertEquals(arrivalDate, result);
    }

    @Test
    public void testToString() {
        LocalDateTime departureDate = LocalDateTime.of(2024, 1, 1, 8, 0);
        LocalDateTime arrivalDate = LocalDateTime.of(2024, 1, 1, 7, 0);
        Segment segment = new Segment(departureDate, arrivalDate);

        String result = segment.toString();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String expected = "[" + departureDate.format(formatter) + "|" + arrivalDate.format(formatter) + "]";

        assertEquals(expected, result);
    }
}
