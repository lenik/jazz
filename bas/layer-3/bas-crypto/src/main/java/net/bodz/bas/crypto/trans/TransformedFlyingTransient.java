package net.bodz.bas.crypto.trans;

import java.util.function.Function;

import net.bodz.bas.crypto.trans.fn.ICodeBin;

public class TransformedFlyingTransient<S extends ICodeBin, T extends ICodeBin>
        extends AbstractFlyingTransient {

    IFlyingTransient core;
    Function<S, T> transformer;

    public TransformedFlyingTransient(IFlyingTransient core, Function<S, T> transformer) {
        super(core.getWindow());
        this.core = core;
        this.transformer = transformer;
    }

    @Override
    public IFlyingTransient getCore() {
        return core;
    }

    @Override
    public T getCode(long index) {
        @SuppressWarnings("unchecked")
        S orig = (S) core.getCode(index);
        T dst = transformer.apply(orig);
        return dst;
    }

}
