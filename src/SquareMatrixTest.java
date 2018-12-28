import org.junit.Assert;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

class Support{
    static Double[] getFlat(int n, Double x){
        Double[] tab = new Double[n*n];
        IntStream.range(0, n*n).forEach(index -> tab[index] = x);
        return tab;
    }
}

public class SquareMatrixTest {
    @org.junit.Test
    public void SimpleMultiplyTest() {
        SquareMatrix m1 = new SquareMatrix(4, Support.getFlat(4, 1.0));
        SquareMatrix m2 = new SquareMatrix(4, Support.getFlat(4, 1.0));
        SquareMatrix m = SquareMatrix.multiply(m1, m2);
        SquareMatrix exp = new SquareMatrix(4, Support.getFlat(4, 4.0));
        assertEquals(exp, m);
        m1 = new SquareMatrix(10, Support.getFlat(10, 2.0));
        m2 = new SquareMatrix(10, Support.getFlat(10, 8.0));
        m = SquareMatrix.multiply(m1, m2);
        exp = new SquareMatrix(10, Support.getFlat(10, 160.0));
        assertEquals(exp, m);
        m1 = new SquareMatrix(3, new Double[]{1.0, 2.0, 1.0, 9.0, 0.0, 9.0, 6.0, 1.0, 3.0});
        m2 = new SquareMatrix(3, new Double[]{1.0, 1.0, 0.0, 5.0, 0.0, 0.0, 3.0, 0.0, 9.0});
        m = SquareMatrix.multiply(m1, m2);
        exp = new SquareMatrix(3, new Double[]{14.0, 1.0, 9.0, 36.0, 9.0, 81.0, 20.0, 6.0, 27.0});
        System.out.println(m);
        System.out.println(exp);
        assertEquals(exp, m);
    }

    @org.junit.Test
    public void SimplePowerTest() {
        SquareMatrix m1 = new SquareMatrix(2, Support.getFlat(2, 1.0));
        SquareMatrix exp = new SquareMatrix(2, Support.getFlat(2, 524288.0));
        SquareMatrix m = SquareMatrix.power(m1, 20);
        System.out.println(m);
        assertEquals(exp, m);
        m1 = new SquareMatrix(2, Support.getFlat(2, 1.0));
        exp = new SquareMatrix(2, Support.getFlat(2, 1048576.0));
        m = SquareMatrix.power(m1, 21);
        System.out.println(m);
        assertEquals(exp, m);
    }
}