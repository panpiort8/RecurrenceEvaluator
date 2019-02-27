package NonRecurrenceSpliterators;

import java.util.Spliterator;
import java.util.function.Consumer;

public class CatalanSpliterator implements Spliterator<Long> {

    private long modulo = 1000000009;

    private long current = 1;
    private long n = 0;

    public CatalanSpliterator(){ }

    public CatalanSpliterator(Long modulo){
        if(!Support.isPrime(modulo))
            throw new RuntimeException("We want modulo to be prime.");
        this.modulo = modulo;
    }


    @Override
    public boolean tryAdvance(Consumer<? super Long> consumer) {
        if(n != 0){
            long result = ( 2*((2*(n-1))%modulo + 1 ) * current) % modulo;
            result *=  Support.modInverse(n+1, modulo);
            result %= modulo;
            current = result;
        }
        consumer.accept(current);
        n++;
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
