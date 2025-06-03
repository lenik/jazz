package net.bodz.bas.make;

public interface IParameterizedTarget<P, T extends IKeyData<TK, TT>, TK, TT> {

    Class<? extends P> getParameterType();

    Class<? extends T> getTargetType();

    T getTarget(P parameter, IDataBinding binding);

}
