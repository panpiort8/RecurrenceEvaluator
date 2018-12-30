import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.*;
import java.util.stream.*;

public class RecurrencySolverStream implements Stream<Double>{

    private Stream<Double> realStream;
    private RecurrenceSolverSpliterator spliterator;

    RecurrencySolverStream(RecurrenceSolverSpliterator spliterator, boolean parallel){
        this.spliterator = spliterator;
        this.realStream = StreamSupport.stream(spliterator, parallel);
    }

    @Override
    public Stream<Double> limit(long l) {
        if(spliterator.isLimited())
            return realStream.limit(l);
        spliterator.limit(l);
        return new RecurrencySolverStream(spliterator, isParallel());
    }

    @Override
    public Stream<Double> skip(long l) {
        if(spliterator.isSkipped())
            return realStream.skip(l);
        spliterator.skip(l);
        return new RecurrencySolverStream(spliterator, isParallel());
    }

    @Override
    public Stream<Double> filter(Predicate<? super Double> predicate) {
        return realStream.filter(predicate);
    }

    @Override
    public <R> Stream<R> map(Function<? super Double, ? extends R> function) {
        return realStream.map(function);
    }

    @Override
    public IntStream mapToInt(ToIntFunction<? super Double> toIntFunction) {
        return realStream.mapToInt(toIntFunction);
    }

    @Override
    public LongStream mapToLong(ToLongFunction<? super Double> toLongFunction) {
        return realStream.mapToLong(toLongFunction);
    }

    @Override
    public DoubleStream mapToDouble(ToDoubleFunction<? super Double> toDoubleFunction) {
        return realStream.mapToDouble(toDoubleFunction);
    }

    @Override
    public <R> Stream<R> flatMap(Function<? super Double, ? extends Stream<? extends R>> function) {
        return realStream.flatMap(function);
    }

    @Override
    public IntStream flatMapToInt(Function<? super Double, ? extends IntStream> function) {
        return realStream.flatMapToInt(function);
    }

    @Override
    public LongStream flatMapToLong(Function<? super Double, ? extends LongStream> function) {
        return realStream.flatMapToLong(function);
    }

    @Override
    public DoubleStream flatMapToDouble(Function<? super Double, ? extends DoubleStream> function) {
        return realStream.flatMapToDouble(function);
    }

    @Override
    public Stream<Double> distinct() {
        return realStream.distinct();
    }

    @Override
    public Stream<Double> sorted() {
        return realStream.sorted();
    }

    @Override
    public Stream<Double> sorted(Comparator<? super Double> comparator) {
        return realStream.sorted(comparator);
    }

    @Override
    public Stream<Double> peek(Consumer<? super Double> consumer) {
        return realStream.peek(consumer);
    }

    @Override
    public void forEach(Consumer<? super Double> consumer) {
        realStream.forEach(consumer);
    }

    @Override
    public void forEachOrdered(Consumer<? super Double> consumer) {
        realStream.forEachOrdered(consumer);
    }

    @Override
    public Object[] toArray() {
        return realStream.toArray();
    }

    @Override
    public <A> A[] toArray(IntFunction<A[]> intFunction) {
        return realStream.toArray(intFunction);
    }

    @Override
    public Double reduce(Double aDouble, BinaryOperator<Double> binaryOperator) {
        return realStream.reduce(aDouble, binaryOperator);
    }

    @Override
    public Optional<Double> reduce(BinaryOperator<Double> binaryOperator) {
        return realStream.reduce(binaryOperator);
    }

    @Override
    public <U> U reduce(U u, BiFunction<U, ? super Double, U> biFunction, BinaryOperator<U> binaryOperator) {
        return realStream.reduce(u, biFunction, binaryOperator);
    }

    @Override
    public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super Double> biConsumer, BiConsumer<R, R> biConsumer1) {
        return realStream.collect(supplier, biConsumer, biConsumer1);
    }

    @Override
    public <R, A> R collect(Collector<? super Double, A, R> collector) {
        return realStream.collect(collector);
    }

    @Override
    public Optional<Double> min(Comparator<? super Double> comparator) {
        return realStream.min(comparator);
    }

    @Override
    public Optional<Double> max(Comparator<? super Double> comparator) {
        return realStream.max(comparator);
    }

    @Override
    public long count() {
        return realStream.count();
    }

    @Override
    public boolean anyMatch(Predicate<? super Double> predicate) {
        return realStream.anyMatch(predicate);
    }

    @Override
    public boolean allMatch(Predicate<? super Double> predicate) {
        return realStream.anyMatch(predicate);
    }

    @Override
    public boolean noneMatch(Predicate<? super Double> predicate) {
        return realStream.noneMatch(predicate);
    }

    @Override
    public Optional<Double> findFirst() {
        return realStream.findFirst();
    }

    @Override
    public Optional<Double> findAny() {
        return realStream.findAny();
    }

    @Override
    public Iterator<Double> iterator() {
        return realStream.iterator();
    }

    @Override
    public Spliterator<Double> spliterator() {
        return realStream.spliterator();
    }

    @Override
    public boolean isParallel() {
        return realStream.isParallel();
    }

    @Override
    public Stream<Double> sequential() {
        return realStream.sequential();
    }

    @Override
    public Stream<Double> parallel() {
        return realStream.parallel();
    }

    @Override
    public Stream<Double> unordered() {
        return realStream.unordered();
    }

    @Override
    public Stream<Double> onClose(Runnable runnable) {
        return realStream.onClose(runnable);
    }

    @Override
    public void close() {
        realStream.close();
    }
}
