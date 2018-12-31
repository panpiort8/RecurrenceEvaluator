import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SequenceFactory {
    public static Stream<Double> getFibonacciStream(){
        RecurrenceEvaluator evaluator = new RecurrenceEvaluator()
                .setRecurrence(new Double[]{1.0, 1.0})
                .setInitialValues(new Double[]{1.0, 1.0});
        return new RecurrenceEvaluatorStream(evaluator.spliterator(), false);
    }

    public static Stream<Long> getPrimesStream(){
        return StreamSupport.stream(new PrimesSpliterator(), false);
    }

    public static Stream<Long> getCatalanModuloStream(){
        return StreamSupport.stream(new CatalanSpliterator(), false);
    }

    public static Stream<Long> getCatalansModuloStream(long modulo){
        return StreamSupport.stream(new CatalanSpliterator(modulo), false);
    }

    public static Stream<Double> getPadovanSequence(){
        RecurrenceEvaluator evaluator = new RecurrenceEvaluator()
                .setRecurrence(new Double[]{0.0, 1.0, 1.0})
                .setInitialValues(new Double[]{1.0, 1.0, 1.0});
        return new RecurrenceEvaluatorStream(evaluator.spliterator(), false);
    }
}
