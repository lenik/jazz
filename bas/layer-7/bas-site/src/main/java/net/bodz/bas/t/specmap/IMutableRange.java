package net.bodz.bas.t.specmap;

public interface IMutableRange<T extends Comparable<T>>
        extends
            IRange<T> {

    void setStart(T start);

    void setStop(T stop);

    void setEnd(T end);

}
