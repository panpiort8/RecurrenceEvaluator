import Suppliers.CatalanSupplier;
import Suppliers.Support;
import RecurrenceEvaluator.RecurrenceEvaluator;

import java.util.stream.Stream;

public class SequenceFactory {
    public static Stream<Double> getFibonacciStream(){
        RecurrenceEvaluator evaluator = new RecurrenceEvaluator()
                .setRecurrence(new Double[]{1.0, 1.0})
                .setInitialValues(new Double[]{1.0, 1.0});
        return evaluator.stream();
    }

    public static Stream<Integer> getPrimesStream(){
        return Stream.iterate(2, x -> x+1).filter(Support::isPrime);
    }

    public static Stream<Long> getCatalanModuloStream(){
        return Stream.generate(new CatalanSupplier());
    }

    public static Stream<Long> getCatalansModuloStream(long modulo){
        return Stream.generate(new CatalanSupplier(modulo));
    }

    public static Stream<Double> getPadovanStream(){
        RecurrenceEvaluator evaluator = new RecurrenceEvaluator()
                .setRecurrence(new Double[]{0.0, 1.0, 1.0})
                .setInitialValues(new Double[]{1.0, 1.0, 1.0});
        return evaluator.stream();
    }
}
