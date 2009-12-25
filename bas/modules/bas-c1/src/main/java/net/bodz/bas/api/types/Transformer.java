package net.bodz.bas.api.types;

import net.bodz.bas.api.functor.Filt1;

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
