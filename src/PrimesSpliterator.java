import java.util.Spliterator;
import java.util.function.Consumer;

public class PrimesSpliterator implements Spliterator<Long> {

    private long currentNumber = 2;

    @Override
    public boolean tryAdvance(Consumer<? super Long> consumer) {
        if (currentNumber == 2){
            consumer.accept(currentNumber++);
            return true;
        }
        while (!Support.isPrime(currentNumber))
            currentNumber += 2;
        consumer.accept(currentNumber);
        currentNumber += 2;
        return true;
    }

    @Override
    public Spliterator<Long> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return Long.MAX_VALUE;
    }

    @Override
    public int characteristics() {
        return 0;
    }
}
