package net.bodz.bas.crypto.trans;

import net.bodz.bas.crypto.trans.fn.ICodeBin;
import net.bodz.bas.fn.ITransformer;

public abstract class AbstractFlyingTransient
        implements IFlyingTransient {

    long window;

    public AbstractFlyingTransient(long window) {
        this.window = window;
    }

    @Override
    public long getWindow() {
        return window;
    }

    @Override
    public final ICodeBin getCodeForNow() {
        return getCodeAtTime(System.currentTimeMillis());
    }

    @Override
    public final ICodeBin getCodeAtTime(long time) {
        long index = time / window;
        return getCode(index);
    }

    @Override
    public abstract ICodeBin getCode(long index);

    @Override
    public final FlyingIndex lastIndexOf(ICodeBin code, int distance) {
        long fromIndex = System.currentTimeMillis() / window;
        return lastIndexOf(fromIndex, code, distance);
    }

    @Override
    public FlyingIndex lastIndexOf(long fromIndex, ICodeBin code, int distance) {
        long pos = fromIndex;
        for (int i = 0; i < distance; i++) {
            ICodeBin c = getCode(pos);
            if (code.equals(c))
                return new FlyingIndex(pos, window);
            pos--;
        }
        return FlyingIndex.NULL;
    }

    @Override
    public final FlyingIndex lastIndexOf(String codeStr, int distance) {
        long fromIndex = System.currentTimeMillis() / window;
        return lastIndexOf(fromIndex, codeStr, distance);
    }

    @Override
    public FlyingIndex lastIndexOf(long fromIndex, String codeStr, int distance) {
        long pos = fromIndex;
        for (int i = 0; i < distance; i++) {
            String c = getCode(pos).toString();
            if (codeStr.equals(c))
                return new FlyingIndex(pos, window);
            pos--;
        }
        return FlyingIndex.NULL;
    }

    @Override
    public IFlyingTransient getCore() {
        return null;
    }

    @Override
    public IFlyingTransient transform(ITransformer<? extends ICodeBin, ? extends ICodeBin> transformer) {
        return new TransformedFlyingTransient<>(this, transformer);
    }

}
