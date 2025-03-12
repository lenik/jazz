package net.bodz.bas.c.java.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collector;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.model.AbstractDecorator;

public class DecoratedStream<E>
        extends AbstractDecorator<Stream<E>>
        implements
            Stream<E> {

    private static final long serialVersionUID = 1L;

    public DecoratedStream(Stream<E> _orig) {
        super(_orig);
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return getWrapped().iterator();
    }

    @NotNull
    @Override
    public Spliterator<E> spliterator() {
        return getWrapped().spliterator();
    }

    @NotNull
    @Override
    public boolean isParallel() {
        return getWrapped().isParallel();
    }

    @NotNull
    @Override
    public Stream<E> sequential() {
        return getWrapped().sequential();
    }

    @NotNull
    @Override
    public Stream<E> parallel() {
        return getWrapped().parallel();
    }

    @NotNull
    @Override
    public Stream<E> unordered() {
        return getWrapped().unordered();
    }

    @NotNull
    @Override
    public Stream<E> onClose(Runnable closeHandler) {
        return getWrapped().onClose(closeHandler);
    }

    @NotNull
    @Override
    public void close() {
        getWrapped().close();
    }

    @NotNull
    @Override
    public Stream<E> filter(Predicate<? super E> predicate) {
        return getWrapped().filter(predicate);
    }

    @NotNull
    @Override
    public <R> Stream<R> map(Function<? super E, ? extends R> mapper) {
        return getWrapped().map(mapper);
    }

    @NotNull
    @Override
    public IntStream mapToInt(ToIntFunction<? super E> mapper) {
        return getWrapped().mapToInt(mapper);
    }

    @NotNull
    @Override
    public LongStream mapToLong(ToLongFunction<? super E> mapper) {
        return getWrapped().mapToLong(mapper);
    }

    @NotNull
    @Override
    public DoubleStream mapToDouble(ToDoubleFunction<? super E> mapper) {
        return getWrapped().mapToDouble(mapper);
    }

    @NotNull
    @Override
    public <R> Stream<R> flatMap(Function<? super E, ? extends Stream<? extends R>> mapper) {
        return getWrapped().flatMap(mapper);
    }

    @NotNull
    @Override
    public IntStream flatMapToInt(Function<? super E, ? extends IntStream> mapper) {
        return getWrapped().flatMapToInt(mapper);
    }

    @NotNull
    @Override
    public LongStream flatMapToLong(Function<? super E, ? extends LongStream> mapper) {
        return getWrapped().flatMapToLong(mapper);
    }

    @NotNull
    @Override
    public DoubleStream flatMapToDouble(Function<? super E, ? extends DoubleStream> mapper) {
        return getWrapped().flatMapToDouble(mapper);
    }

    @NotNull
    @Override
    public Stream<E> distinct() {
        return getWrapped().distinct();
    }

    @NotNull
    @Override
    public Stream<E> sorted() {
        return getWrapped().sorted();
    }

    @NotNull
    @Override
    public Stream<E> sorted(Comparator<? super E> comparator) {
        return getWrapped().sorted(comparator);
    }

    @NotNull
    @Override
    public Stream<E> peek(Consumer<? super E> action) {
        return getWrapped().peek(action);
    }

    @NotNull
    @Override
    public Stream<E> limit(long maxSize) {
        return getWrapped().limit(maxSize);
    }

    @NotNull
    @Override
    public Stream<E> skip(long n) {
        return getWrapped().skip(n);
    }

    @NotNull
    @Override
    public void forEach(Consumer<? super E> action) {
        getWrapped().forEach(action);
    }

    @NotNull
    @Override
    public void forEachOrdered(Consumer<? super E> action) {
        getWrapped().forEachOrdered(action);
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return getWrapped().toArray();
    }

    @NotNull
    @Override
    public <A> A[] toArray(IntFunction<A[]> generator) {
        return getWrapped().toArray(generator);
    }

    @NotNull
    @Override
    public E reduce(E identity, BinaryOperator<E> accumulator) {
        return getWrapped().reduce(identity, accumulator);
    }

    @NotNull
    @Override
    public Optional<E> reduce(BinaryOperator<E> accumulator) {
        return getWrapped().reduce(accumulator);
    }

    @NotNull
    @Override
    public <U> U reduce(U identity, BiFunction<U, ? super E, U> accumulator, BinaryOperator<U> combiner) {
        return getWrapped().reduce(identity, accumulator, combiner);
    }

    @NotNull
    @Override
    public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super E> accumulator, BiConsumer<R, R> combiner) {
        return getWrapped().collect(supplier, accumulator, combiner);
    }

    @NotNull
    @Override
    public <R, A> R collect(Collector<? super E, A, R> collector) {
        return getWrapped().collect(collector);
    }

    @NotNull
    @Override
    public Optional<E> min(Comparator<? super E> comparator) {
        return getWrapped().min(comparator);
    }

    @NotNull
    @Override
    public Optional<E> max(Comparator<? super E> comparator) {
        return getWrapped().max(comparator);
    }

    @NotNull
    @Override
    public long count() {
        return getWrapped().count();
    }

    @NotNull
    @Override
    public boolean anyMatch(Predicate<? super E> predicate) {
        return getWrapped().anyMatch(predicate);
    }

    @NotNull
    @Override
    public boolean allMatch(Predicate<? super E> predicate) {
        return getWrapped().allMatch(predicate);
    }

    @NotNull
    @Override
    public boolean noneMatch(Predicate<? super E> predicate) {
        return getWrapped().noneMatch(predicate);
    }

    @NotNull
    @Override
    public Optional<E> findFirst() {
        return getWrapped().findFirst();
    }

    @NotNull
    @Override
    public Optional<E> findAny() {
        return getWrapped().findAny();
    }

}
