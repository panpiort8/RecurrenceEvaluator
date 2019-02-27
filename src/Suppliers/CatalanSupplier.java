package Suppliers;

import java.util.function.Supplier;

public class CatalanSupplier implements Supplier<Long> {
    private long modulo = 1000000009;
    private long current = 1;
    private long n = 0;

    public CatalanSupplier(){ }

    public CatalanSupplier(Long modulo){
        if(!Support.isPrime(modulo))
            throw new RuntimeException("We want modulo to be prime.");
        this.modulo = modulo;
    }

    @Override
    public Long get() {
        if(n != 0){
            long result = ( 2*((2*(n-1))%modulo + 1 ) * current) % modulo;
            result *=  Support.modInverse(n+1, modulo);
            result %= modulo;
            current = result;
        }
        n++;
        return current;
    }
}
