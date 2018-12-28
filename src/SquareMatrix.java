import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SquareMatrix {
    private int n;
    private Double[] tab;

    public SquareMatrix(int n){
        this.n = n;
        tab = new Double[n*n];
    }

    public SquareMatrix(int n, Double[] elements){
        this.n = n;
        if(elements.length != n*n){
            throw new RuntimeException("Invalid arguments!");
        }
        tab = elements;
    }

    static public SquareMatrix getId(int n){
        SquareMatrix id = new SquareMatrix(n);
        for (int i = 0; i < n; i++) {
            id.set(i, i, 1.0);
        }
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SquareMatrix))
            return false;
        SquareMatrix m = (SquareMatrix) o;
        return m.getN() == getN() && Arrays.asList(m.tab).equals(Arrays.asList(tab));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < getN(); j++) {
            for (int i = 0; i < getN(); i++) {
                builder.append(get(i, j)).append(' ');
            }
            builder.append('\n');
        }
        return builder.toString();
    }


    public int getN() {
        return n;
    }

    public Double get(int i, int j){
        return tab[n*j + i];
    }

    public void set(int i, int j, Double val){
        tab[n*j + i] = val;
    }


    static public SquareMatrix multiply(SquareMatrix m1, SquareMatrix m2){
        if (m1.getN() != m2.getN())
            throw new RuntimeException("Different matrix dimensions!");
        SquareMatrix m = new SquareMatrix(m1.getN());
        for (int i = 0; i < m.getN(); i++) {
            for (int j = 0; j < m.getN(); j++) {
                Double x = 0.0;
                for (int k = 0; k < m.getN(); k++) {
                    x += m2.get(i, k) * m1.get(k, j);
                }
                m.set(i, j, x);
            }
        }
        return m;
    }

    static public SquareMatrix power(SquareMatrix matrix, int k){
        return null;
    }
}
