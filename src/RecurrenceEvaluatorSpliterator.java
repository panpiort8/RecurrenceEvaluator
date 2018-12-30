import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class RecurrenceEvaluatorSpliterator implements Spliterator<Double> {

    private RecurrenceSolver solver;
    private SquareMatrix recurrenceMatrix;
    private List<Double> initialValues;
    private int matrixDim;

    private SquareMatrix currentRecurrenceMatrix;
    private int currentMatrixPower = 1;
    private long currentElement = 0;
    private long lastElement = Long.MAX_VALUE;
    private boolean skipped = false;

    RecurrenceEvaluatorSpliterator(RecurrenceSolver solver){
        this.solver = solver;

        // It may be more efficient to have it's own copy of solver
        this.matrixDim = solver.getDim();
        this.initialValues = solver.getInitialValues();
        this.currentRecurrenceMatrix = solver.getMatrix();
        this.recurrenceMatrix = solver.getMatrix();
    }

    public boolean isLimited(){
        return lastElement != Long.MAX_VALUE;
    }

    public boolean isSkipped() {
        return skipped;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Double> consumer) {
        if (estimateSize() <= 0)
            return false;
        if(currentElement < matrixDim)
            consumer.accept(initialValues.get((int) currentElement));
        else {
            long wantedPower = currentElement - matrixDim + 1;
            long remainPower = wantedPower - currentMatrixPower;
            currentRecurrenceMatrix = SquareMatrix.multiply(currentRecurrenceMatrix, SquareMatrix.power(recurrenceMatrix, remainPower));
            currentMatrixPower = (int) wantedPower;
            consumer.accept(RecurrenceSolver.extractValue(initialValues, currentRecurrenceMatrix));
        }
        currentElement++;
        return true;
    }

    @Override
    public Spliterator<Double> trySplit() {
        if (estimateSize() < 20)
            return null;
        long s = estimateSize()/2;
        RecurrenceSolverSpliterator spliterator = new RecurrenceSolverSpliterator(this.solver);
        spliterator.lastElement = this.currentElement + s;
        spliterator.currentElement = this.currentElement;
        this.currentElement = spliterator.lastElement + 1;
        return spliterator;
    }

    @Override
    public long estimateSize() {
        return lastElement == Long.MAX_VALUE ? Long.MAX_VALUE : lastElement - currentElement + 1;
    }

    @Override
    public int characteristics() {
        return isLimited() ? CONCURRENT | SUBSIZED | SIZED : CONCURRENT;
    }

    //used in stream to make stream.limit(n) faster
    public void limit(long l){
        lastElement = currentElement +l-1;
    }

    //used in stream to make stream.skip(n) faster
    public void skip(long l){
        skipped = true;
        currentElement += l;
        if(isLimited())
            lastElement += l;
    }

}