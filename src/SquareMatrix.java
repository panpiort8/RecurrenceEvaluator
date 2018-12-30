import java.util.Arrays;

public class SquareMatrix {
    private int dim;
    private Double[] tab;

    public SquareMatrix(int dim){
        this.dim = dim;
        tab = new Double[dim * dim];
        for (int i = 0; i < dim; i++)
            for (int j = 0; j < dim; j++)
                set(i, j, 0.0);
    }

    public SquareMatrix(SquareMatrix matrix){
        this.dim = matrix.getDim();
        tab = matrix.getRaw();
    }

    public SquareMatrix(int dim, Double[] elements){
        this.dim = dim;
        if(elements.length != dim * dim){
            throw new RuntimeException("Invalid arguments!");
        }
        tab = elements;
    }

    static public SquareMatrix getId(int n){
        SquareMatrix id = new SquareMatrix(n);
        for (int i = 0; i < n; i++)
            id.set(i, i, 1.0);
        return id;
    }

    public int getDim() {
        return dim;
    }

    public Double[] getRaw(){
        return tab.clone();
    }

    public Double get(int i, int j){
        return tab[dim *j + i];
    }

    public void set(int i, int j, Double val){
        tab[dim *j + i] = val;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SquareMatrix))
            return false;
        SquareMatrix m = (SquareMatrix) o;
        return m.getDim() == getDim() && Arrays.asList(m.tab).equals(Arrays.asList(tab));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < getDim(); j++) {
            for (int i = 0; i < getDim(); i++) {
                builder.append(get(i, j)).append(' ');
            }
            builder.append('\n');
        }
        return builder.toString();
    }

    static public SquareMatrix multiply(SquareMatrix m1, SquareMatrix m2){
        if (m1.getDim() != m2.getDim())
            throw new RuntimeException("Different matrix dimensions!");
        SquareMatrix m = new SquareMatrix(m1.getDim());
        for (int i = 0; i < m.getDim(); i++) {
            for (int j = 0; j < m.getDim(); j++) {
                Double x = 0.0;
                for (int k = 0; k < m.getDim(); k++) {
                    x += m2.get(i, k) * m1.get(k, j);
                }
                m.set(i, j, x);
            }
        }
        return m;
    }

    static public SquareMatrix power(SquareMatrix matrix, long k){
        if(k == 0)
            return SquareMatrix.getId(matrix.getDim());
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
