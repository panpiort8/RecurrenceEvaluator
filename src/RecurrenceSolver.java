import java.util.*;
import java.util.stream.*;

public class RecurrenceSolver {
    private List<Double> arguments;
    private SquareMatrix matrix;
    private int n;
    private volatile boolean triedToSplit = false;

    void setTriedToSplit(boolean triedToSplit) {
        this.triedToSplit = triedToSplit;
    }

    public boolean isTriedToSplit() {
        return triedToSplit;
    }

    int getN() {
        return n;
    }

    SquareMatrix getMatrix(){
        return new SquareMatrix(matrix);
    }

    List<Double> getArguments(){
        return arguments;
    }

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
        return extractValue(arguments, getKthMatrix(k));

    }

    static Double extractValue(List<Double> arguments, SquareMatrix matrix){
        Double result = 0.0;
        for (int i = 0; i < matrix.getN(); i++)
            result += arguments.get(i) * matrix.get(i, 0);
        return result;
    }

    public RecurrenceSolverSpliterator spliterator(){
        return new RecurrenceSolverSpliterator(this);
    }

    public Stream<Double> stream(){
        return new RecurrencySolverStream(spliterator(), false);
    }

    public Stream<Double> parallelStream(){
        return new RecurrencySolverStream(spliterator(), true);
    }


}


