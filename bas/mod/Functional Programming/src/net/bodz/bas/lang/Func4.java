package net.bodz.bas.lang;

public interface Func4<T, A, B, C, D> {

    T eval(A a, B b, C c, D d) throws EvalException;

}
