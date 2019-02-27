import java.util.stream.Stream;

public class Demo {
    public static void main(String[]args){
        // test are more interesting
        Stream<Double> sequence = SequenceFactory.getPadovanStream();
        sequence.limit(10).skip(100).map(Double::longValue).filter(x -> x%2 == 0).forEach(System.out::println);
        sequence = SequenceFactory.getFibonacciStream();
        sequence.skip(3).limit(10).forEach(System.out::println);
        SequenceFactory.getPrimesStream().limit(10).forEach(System.out::println);
    }
}
