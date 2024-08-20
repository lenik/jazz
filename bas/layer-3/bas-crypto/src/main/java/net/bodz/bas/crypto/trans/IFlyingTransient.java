package net.bodz.bas.crypto.trans;

import java.util.function.Function;

import net.bodz.bas.crypto.trans.fn.ICodeBin;

public interface IFlyingTransient {

    /**
     * Get the size of the flying window.
     *
     * @return window size in millisecond.
     */
    long getWindow();

    String snapshot();

    ICodeBin getCodeAtTime(long time);

    ICodeBin getCode(long index);

    default FlyingIndex lastIndexOf(ICodeBin code, int distance, int allowAhead) {
        long window = getWindow();
        long fromIndex = System.currentTimeMillis() / window;
        return lastIndexOf(fromIndex, code, distance, allowAhead);
    }

    FlyingIndex lastIndexOf(long fromIndex, ICodeBin code, int distance, int allowAhead);

    default FlyingIndex lastIndexOf(String codeStr, int distance, int allowAhead) {
        long window = getWindow();
        long fromIndex = System.currentTimeMillis() / window;
        return lastIndexOf(fromIndex, codeStr, distance, allowAhead);
    }

    FlyingIndex lastIndexOf(long fromIndex, String codeStr, int distance, int allowAhead);

//    IFlyingTransient getCore();

    default <T extends ICodeBin> IFlyingTransient transform(Function<ICodeBin, T> transformer) {
        return new TransformedFlyingTransient<ICodeBin, T>(this, transformer);
    }

    @SuppressWarnings("unchecked")
    default IFlyingTransient transform(Function<ICodeBin, ? extends ICodeBin>... transformers) {
        IFlyingTransient node = this;
        for (Function<ICodeBin, ? extends ICodeBin> fn : transformers) {
            node = node.transform(fn);
        }
        return node;
    }

    void diag(String search, int distance, int allowAhead);

}
