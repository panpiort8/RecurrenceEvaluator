import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class RecurrenceSolverSpliterator implements Spliterator<Double> {

    private RecurrenceSolver solver;
    private SquareMatrix recMatrix;
    private List<Double> arguments;
    private int matrixDim;

    private SquareMatrix currentMatrix;
    private int currentMatrixPower;
    private long current;
    private long last = Long.MAX_VALUE;
    private boolean skipped = false;

    RecurrenceSolverSpliterator(RecurrenceSolver solver){
        this.current = 0;
        this.currentMatrixPower = 1;
        this.solver = solver;
        this.matrixDim = solver.getN();
        this.arguments = solver.getArguments();
        this.currentMatrix = solver.getMatrix();
        this.recMatrix = solver.getMatrix();
    }

    public void limit(long l){
        last = current+l-1;
    }

    public void skip(long l){
        skipped = true;
        current += l;
        if(isLimited())
            last += l;
    }

    public boolean isLimited(){
        return last != Long.MAX_VALUE;
    }

    public boolean isSkipped() {
        return skipped;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Double> consumer) {
        if (estimateSize() <= 0)
            return false;
        if(current < matrixDim)
            consumer.accept(arguments.get((int)current));
        else {
            long wantedPower = current - matrixDim + 1;
            long remainPower = wantedPower - currentMatrixPower;
            currentMatrix = SquareMatrix.multiply(currentMatrix, SquareMatrix.power(recMatrix, remainPower));
            currentMatrixPower = (int) wantedPower;
            consumer.accept(RecurrenceSolver.extractValue(arguments, currentMatrix));
        }
        current++;
        return true;
    }

    @Override
    public Spliterator<Double> trySplit() {
        if (estimateSize() < 20)
            return null;
        long s = estimateSize()/2;
        RecurrenceSolverSpliterator spliterator = new RecurrenceSolverSpliterator(this.solver);
        spliterator.last = this.current + s;
        spliterator.current = this.current;
        this.current = spliterator.last + 1;
        return spliterator;
    }

    @Override
    public long estimateSize() {
        return last == Long.MAX_VALUE ? Long.MAX_VALUE : last - current + 1;
    }

    @Override
    public int characteristics() {
        return isLimited() ? CONCURRENT | SUBSIZED | SIZED : CONCURRENT;
    }


}