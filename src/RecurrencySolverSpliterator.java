import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class RecurrencySolverSpliterator implements Spliterator<Double> {

    private RecurrencySolver solver;
    private SquareMatrix recMatrix;
    private SquareMatrix currentMatrix;
    private List<Double> arguments;
    private int currentMatrixPower;
    private int matrixDim;
    private long current;
    private long last = Long.MAX_VALUE;
    private boolean skipped = false;

    RecurrencySolverSpliterator(RecurrencySolver solver){
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
            consumer.accept(RecurrencySolver.extractValue(arguments, currentMatrix));
        }
        current++;
        return true;
    }

    @Override
    public Spliterator<Double> trySplit() {
        solver.setTriedToSplit(true);
        return null;
//        if (estimateSize() < 10)
//            return null;
//        long s = estimateSize()/2;
//        RecurrencySolverSpliterator spliterator = new RecurrencySolverSpliterator(this.solver);
//        spliterator.last = s;
//        spliterator.current = this.current;
//        this.current = s+1;
//        return spliterator;
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