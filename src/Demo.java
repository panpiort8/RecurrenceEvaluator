import java.util.stream.Stream;

public class Demo {
    public static void main(String[]args){
        Stream<Double> sequence = SequenceFactory.getPadovanSequence();
        sequence.limit(10).skip(100).map(Double::longValue).filter(x -> x%2 == 0).forEach(System.out::println);
        sequence = SequenceFactory.getFibonacciStream();
        sequence.skip(3).limit(10).forEach(System.out::println);
    }
}
