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
}