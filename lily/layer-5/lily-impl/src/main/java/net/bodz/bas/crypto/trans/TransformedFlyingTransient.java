package net.bodz.bas.crypto.trans;

import net.bodz.bas.crypto.trans.fn.ICodeBin;
import net.bodz.bas.fn.ITransformer;

public class TransformedFlyingTransient<S extends ICodeBin, T extends ICodeBin>
        extends AbstractFlyingTransient {

    IFlyingTransient core;
    ITransformer<S, T> transformer;

    public TransformedFlyingTransient(IFlyingTransient core, ITransformer<S, T> transformer) {
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
        S orig = (S) core.getCode(index);
        T dst = transformer.transform(orig);
        return dst;
    }

}
