import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SquareMatrix {
    private int n;
    private Double[] tab;

    public SquareMatrix(int n){
        this.n = n;
        tab = new Double[n*n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                set(i, j, 0.0);
    }

    public SquareMatrix(SquareMatrix matrix){
        this.n = matrix.getN();
        tab = matrix.getRaw();
    }

    public SquareMatrix(int n, Double[] elements){
        this.n = n;
        if(elements.length != n*n){
            throw new RuntimeException("Invalid arguments!");
        }
        tab = elements;
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

    static public SquareMatrix getId(int n){
        SquareMatrix id = new SquareMatrix(n);
        for (int i = 0; i < n; i++)
            id.set(i, i, 1.0);
        return id;
    }

    public int getN() {
        return n;
    }

    public Double[] getRaw(){
        return tab.clone();
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
        if(k == 0)
            return SquareMatrix.getId(matrix.getN());
        if(k == 1)
            return new SquareMatrix(matrix);
        if(k % 2 == 0){
            SquareMatrix m1 = SquareMatrix.power(matrix, k/2);
            return SquareMatrix.multiply(m1, m1);
        }
        else{
            SquareMatrix m1 = SquareMatrix.power(matrix, k-1);
            return SquareMatrix.multiply(matrix, m1);
        }
    }
}
