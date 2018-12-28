import java.util.Arrays;
import java.util.List;

public class RecurrencySolver {
    private List<Double> arguments;
    private SquareMatrix matrix;
    private int n;

    public void setRecurrency(Double [] recurrency){
        List<Double> rec = Arrays.asList(recurrency);
        this.n = rec.size();
        matrix = new SquareMatrix(n);
        for (int i = 0; i < n; i++) {
            matrix.set(i, 0, rec.get(i));
            matrix.set(0, i, rec.get(i));
        }
    }

    public void setArguments(Double [] arguments){
        this.arguments = Arrays.asList(arguments);
    }

    private SquareMatrix getKthMatrix(int k){
        k = Math.max(k-n+1, 0);
        return SquareMatrix.power(matrix, k);
    }

    public Double getKthValue(int k){
        if(k < n)
            return arguments.get(k);
        SquareMatrix resultMatrix = getKthMatrix(k);
        Double result = 0.0;
        for (int i = 0; i < resultMatrix.getN(); i++)
            result += arguments.get(i) * resultMatrix.get(i, 0);
        return result;
    }
}
