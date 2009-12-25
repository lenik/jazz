package net.bodz.bas.closures;

import net.bodz.bas.closures.alt.Filt1;

/**
 * May be replaced by apache-commons in future. 
 */
public abstract class Transformer<T>
        extends Filt1<T, T> {

    @Override
    public T filter(T a) {
        return transform(a);
    }

    public abstract T transform(T input);

}
