package net.bodz.bas.t.specmap;

public interface IRange<T extends Comparable<T>>
        extends
            Comparable<IRange<T>> {

    T getStart();

    T getStop();

    T getEnd();

    boolean isStartEnd();

    boolean contains(T val);

    boolean contains(IRange<T> range);

    boolean containsStartEnd(T start, T end);

    boolean containsStartStop(T start, T stop);

    boolean isLessThan(T val);

    boolean isLessOrContains(T val);

    boolean isGreaterThan(T val);

    boolean isGreaterOrContains(T val);

}
