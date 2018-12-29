import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

public class RecurrencySolverTest {
    @org.junit.Test
    public void SimpleRecurrencyTest() {
        RecurrencySolver solver = new RecurrencySolver();
        solver.setRecurrency(new Double[]{1.0, 1.0});
        solver.setArguments(new Double[]{1.0, 1.0});
        assertEquals(Double.valueOf(1.0), solver.getKthValue(0));
        assertEquals(Double.valueOf(1.0), solver.getKthValue(1));
        assertEquals(Double.valueOf(2.0), solver.getKthValue(2));
        assertEquals(Double.valueOf(3.0), solver.getKthValue(3));
        assertEquals(Double.valueOf(610.0), solver.getKthValue(14));
        assertEquals(Double.valueOf(4181.0), solver.getKthValue(18));
    }

    @org.junit.Test
    public void SpliteratorTest() {
        RecurrencySolver solver = new RecurrencySolver();
        solver.setRecurrency(new Double[]{1.0, 1.0});
        solver.setArguments(new Double[]{1.0, 1.0});
        List<Double> result = new ArrayList<>();
        List<Double> expected = Arrays.asList(1.0, 1.0, 2.0, 3.0, 5.0, 8.0, 13.0, 21.0, 34.0, 55.0);
        solver.stream().limit(10).forEach(result::add);
        assertEquals(expected, result);
    }
}