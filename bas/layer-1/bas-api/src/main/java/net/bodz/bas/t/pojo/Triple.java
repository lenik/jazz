package net.bodz.bas.t.pojo;

import java.io.Serializable;

import net.bodz.bas.c.object.Nullables;

/**
 * <a href="http://english.stackexchange.com/questions/39824/">Why `Triple` not `Trio`?</a>
 */
public class Triple<T1, T2, T3>
        extends Pair<T1, T2>
        implements Serializable {

    private static final long serialVersionUID = 1L;

    public T3 third;

    public Triple() {
        super();
    }

    public Triple(T1 first) {
        super(first);
    }

    public Triple(T1 first, T2 second) {
        super(first, second);
    }

    public Triple(T1 first, T2 second, T3 third) {
        super(first, second);
        this.third = third;
    }

    public T3 getThird() {
        return third;
    }

    public void setThird(T3 third) {
        this.third = third;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;
        if (object == null)
            return false;
        if (object.getClass() != Triple.class)
            return false;
        return partialEquals((Triple<?, ?, ?>) object);
    }

    public boolean partialEquals(Triple<?, ?, ?> o) {
        if (!super.partialEquals(o))
            return false;
        if (!Nullables.equals(third, o.third))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0xbeca678d;
        if (first != null)
            hash += first.hashCode();
        if (second != null)
            hash += second.hashCode();
        if (third != null)
            hash += third.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ", " + third + ")";
    }

    public static <T1, T2, T3> Triple<T1, T2, T3> of(T1 first, T2 second, T3 third) {
        return new Triple<T1, T2, T3>(first, second, third);
    }

}
