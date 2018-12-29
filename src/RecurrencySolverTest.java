import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    public void SpliteratorSimpleTest() {
        RecurrencySolver solver = new RecurrencySolver();
        solver.setRecurrency(new Double[]{1.0, 1.0});
        solver.setArguments(new Double[]{1.0, 1.0});
        List<Double> result = new ArrayList<>();
        List<Double> expected = Arrays.asList(1.0, 1.0, 2.0, 3.0, 5.0, 8.0, 13.0, 21.0, 34.0, 55.0);
        solver.stream().limit(10).forEach(result::add);
        assertEquals(expected, result);
    }

    @org.junit.Test
    public void StreamTest(){
        RecurrencySolver solver = new RecurrencySolver();
        solver.setRecurrency(new Double[]{-1.0, 2.0});
        solver.setArguments(new Double[]{1.0, 1.0});
        List<Integer> result = new ArrayList<>();
        List<Integer> expected = Arrays.asList(-79, -7, 1, 1, 1, 1, 3, 11, 51, 283);
        solver.stream().limit(10).sorted().map(Double::intValue).forEach(result::add);
        assertEquals(expected, result);
    }

    @org.junit.Test
    public void ParallelStreamSimpleTest(){
        RecurrencySolver solver = new RecurrencySolver();
        solver.setRecurrency(new Double[]{-0.5, 1.0});
        solver.setArguments(new Double[]{1.0, 1.0});
        List<Long> seq = new ArrayList<>();
        List<Long> par = new ArrayList<>();
        solver.stream().limit(100).map(Double::longValue).forEach(seq::add);
        solver.stream().limit(100).map(Double::longValue).forEach(System.out::println);
        assertFalse(solver.isTriedToSplit());
        solver.parallelStream().limit(100).map(Double::longValue).forEach(par::add);
        assertTrue(solver.isTriedToSplit());
        assertEquals(seq, par);
    }

    @org.junit.Test
    public void ParallelSortTest(){
        RecurrencySolver solver = new RecurrencySolver();
        solver.setRecurrency(new Double[]{-0.5, 1.0});
        solver.setArguments(new Double[]{1.0, 1.0});
        List<Long> seq = new ArrayList<>();
        List<Long> par = new ArrayList<>();
        solver.stream().limit(100).sorted().map(Double::longValue).forEach(seq::add);
//        solver.stream().limit(100).sorted().map(Double::longValue).forEach(System.out::println);
        assertFalse(solver.isTriedToSplit());
        solver.parallelStream().limit(100).sorted().map(Double::longValue).forEachOrdered(par::add);
        assertTrue(solver.isTriedToSplit());
        System.out.println(par);
        List<Long> sortedPar = new ArrayList<>(par);
        System.out.println(sortedPar);
        Collections.sort(sortedPar);
        assertEquals(seq, sortedPar);
        assertEquals(seq, par);
    }
}