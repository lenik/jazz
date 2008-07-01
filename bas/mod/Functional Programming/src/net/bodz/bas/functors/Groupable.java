package net.bodz.bas.functors;

public interface Groupable<T> extends IFunctor<T> {

    Groupable<T> concat(IFunctor<T> functor);

    Groupable<T> label(Object labelKey);

}
