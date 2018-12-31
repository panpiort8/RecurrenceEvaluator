import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SequenceFactoryTest {
    @org.junit.Test
    public void FibonacciTest() {
        List<Double> result = new ArrayList<>();
        List<Double> expected = Arrays.asList(1.0, 1.0, 2.0, 3.0, 5.0, 8.0, 13.0, 21.0, 34.0, 55.0);
        SequenceFactory.getFibonacciStream().limit(10).forEach(result::add);
        assertEquals(expected, result);
    }

    @org.junit.Test
    public void PrimesTest() {
        List<Integer> result = new ArrayList<>();
        List<Integer> expected = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37);
        SequenceFactory.getPrimesStream().limit(12).map(Long::intValue).forEach(result::add);
        assertEquals(expected, result);

        result = new ArrayList<>();
        expected = Arrays.asList(11, 13, 17, 19, 23, 29, 31, 37);
        SequenceFactory.getPrimesStream().skip(4).limit(8).map(Long::intValue).forEach(result::add);
        assertEquals(expected, result);
    }

    @org.junit.Test
    public void CatalanTest() {
        List<Integer> result = new ArrayList<>();
        List<Integer> expected = Arrays.asList(1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862, 16796, 58786);
        SequenceFactory.getCatalanModuloStream().limit(12).map(Long::intValue).forEach(result::add);
        assertEquals(expected, result);

        result = new ArrayList<>();
        expected = Arrays.asList(35357670, 129644790, 477638700);
        SequenceFactory.getCatalanModuloStream().skip(16).limit(3).map(Long::intValue).forEach(result::add);
        assertEquals(expected, result);
    }

    List<Integer> getListModulo(List<Integer> list, long modulo){
        List<Integer> moduled = new ArrayList<>();
        for (Integer x : list)
            moduled.add((int) (x%modulo));
        return moduled;
    }

    @org.junit.Test
    public void CatalanModuloTest() {
        List<Integer> result = new ArrayList<>();
        List<Integer> expected = getListModulo(Arrays.asList(1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862, 16796, 58786), 19);
        SequenceFactory.getCatalansModuloStream(19).limit(12).map(Long::intValue).forEach(result::add);
        assertEquals(expected, result);
    }

    @org.junit.Test
    public void CatalanEfficiencyTest() {
        List<Integer> result = new ArrayList<>();
        SequenceFactory.getCatalanModuloStream().skip(41000).limit(1200).map(Long::intValue).forEach(result::add);
        System.out.println(result);
    }

    @org.junit.Test
    public void PadovanTest() {
        List<Integer> result = new ArrayList<>();
        List<Integer> expected = Arrays.asList(1, 1, 1, 2, 2, 3, 4, 5, 7, 9, 12, 16, 21, 28, 37, 49, 65, 86, 114);
        SequenceFactory.getPadovanSequence().limit(19).map(Double::intValue).forEach(result::add);
        assertEquals(expected, result);
    }
}