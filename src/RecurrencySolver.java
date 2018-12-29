import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class RecurrencySolver{
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

    public RecurrencySolverSpliterator spliterator(){
        return new RecurrencySolverSpliterator(this);
    }

    public Stream<Double> stream(){
        return StreamSupport.stream(spliterator(), false);
    }

    public Stream<Double> parallelStream(){
        return StreamSupport.stream(spliterator(), true);
    }



}

class RecurrencySolverSpliterator implements Spliterator<Double>{

    private RecurrencySolver solver;
    private SquareMatrix recMatrix;
    private SquareMatrix currentMatrix;
    private List<Double> arguments;
    private int currentMatrixPower;
    private int matrixDim;
    private int current;
    private long last = Long.MAX_VALUE;

    RecurrencySolverSpliterator(RecurrencySolver solver){
        this.current = 0;
        this.currentMatrixPower = 1;
        this.solver = solver;
        this.matrixDim = solver.getN();
        this.arguments = solver.getArguments();
        this.currentMatrix = solver.getMatrix();
        this.recMatrix = solver.getMatrix();
    }

    @Override
    public boolean tryAdvance(Consumer<? super Double> consumer) {
        if (estimateSize() <= 0)
            return false;
        if(current < matrixDim)
            consumer.accept(arguments.get(current));
        else {
            int wantedPower = current - matrixDim + 1;
            int remainPower = wantedPower - currentMatrixPower;
            currentMatrix = SquareMatrix.multiply(currentMatrix, SquareMatrix.power(recMatrix, remainPower));
            currentMatrixPower = wantedPower;
            consumer.accept(RecurrencySolver.extractValue(arguments, currentMatrix));
        }
        current++;
        return true;
    }

    @Override
    public Spliterator<Double> trySplit() {
        solver.setTriedToSplit(true);
        return null;
    }

    @Override
    public long estimateSize() {
        return last == Long.MAX_VALUE ? Long.MAX_VALUE : last - current + 1;
    }

    @Override
    public int characteristics() {
        return CONCURRENT;
    }
}
