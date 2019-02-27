package NonRecurrenceSpliterators;

public class Support {
    static boolean isPrime(long number){
        if(number == 2)
            return true;
        for (int i = 2; i <= Math.sqrt(number) + 1; i++)
            if(number % i == 0)
                return false;
        return true;
    }

    static class LongBox{
        private long value = 0;

        public void setValue(long value) {
            this.value = value;
        }

        public long getValue() {
            return value;
        }
    }

    static long modInverse(long a, long m) {
        LongBox x = new LongBox();
        LongBox y = new LongBox();
        long g = gcdExtended(a, m, x, y);
        if (g != 1)
            throw new RuntimeException("Inverse doesn't exist");
        return (x.getValue()%m + m) % m;
    }

    static long gcdExtended(long a, long b, LongBox x, LongBox y) {
        // Base Case
        if (a == 0) {
            x.setValue(0);
            y.setValue(1);
            return b;
        }

        LongBox x1 = new LongBox();
        LongBox y1 = new LongBox();
        long gcd = gcdExtended(b%a, a, x1, y1);

        // Update value and y using results of recursive
        // call
        x.setValue(y1.getValue() - (b/a) * x1.getValue());
        y.setValue(x1.getValue());

        return gcd;
    }
}
