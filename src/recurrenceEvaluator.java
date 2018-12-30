import java.util.*;
import java.util.stream.*;

public class recurrenceEvaluator {
    private List<Double> initialValues;
    private SquareMatrix matrix;
    private int dim = 0;

    int getDim() {
        return dim;
    }

    SquareMatrix getMatrix(){
        return new SquareMatrix(matrix);
    }

    List<Double> getInitialValues(){
        return initialValues;
    }

    // {b_1, b_2, ..., b_k} represents recurrence given by: a_n = b_1 * a_(n-k) + b_2 * a_(n-k+1) + ... + b_k * a_(n-1)
    public RecurrenceSolver setRecurrence(Double [] recurrence){
        if(initialValues != null && initialValues.size() != recurrence.length)
            throw new RuntimeException("'Dimension' of recurrence must be equal to length of list of initial values.");
        List<Double> rec = Arrays.asList(recurrence);
        this.dim = rec.size();
        matrix = new SquareMatrix(dim);
        for (int i = 0; i < dim; i++) {
            matrix.set(i, 0, rec.get(i));
            matrix.set(0, i, rec.get(i));
        }
        return this;
    }

    // {a_0, a_1, ..., a_(k-1)} - first k values of recurrence
    public RecurrenceSolver setInitialValues(Double [] initialValues){
        if(dim > 0 && dim != initialValues.length)
            throw new RuntimeException("Length of list of initial values must be equal to 'dimension' of recurrence.");
        this.initialValues = Arrays.asList(initialValues);
        return this;
    }

    private SquareMatrix getKthMatrix(int k){
        k = Math.max(k- dim +1, 0);
        return SquareMatrix.power(matrix, k);
    }

    public Double getKthValue(int k){
        if(k < dim)
            return initialValues.get(k);
        return extractValue(initialValues, getKthMatrix(k));

    }

    static Double extractValue(List<Double> arguments, SquareMatrix matrix){
        Double result = 0.0;
        for (int i = 0; i < matrix.getDim(); i++)
            result += arguments.get(i) * matrix.get(i, 0);
        return result;
    }

    public RecurrenceSolverSpliterator spliterator(){
        return new RecurrenceSolverSpliterator(this);
    }

    public Stream<Double> stream(){
        return new RecurrenceSolverStream(spliterator(), false);
    }

    public Stream<Double> parallelStream(){
        return new RecurrenceSolverStream(spliterator(), true);
    }

}


