package net.bodz.bas.crypto.trans;

import net.bodz.bas.crypto.trans.fn.ICodeBin;
import net.bodz.bas.fn.ITransformer;

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

    FlyingIndex lastIndexOf(ICodeBin code, int distance, int allowAhead);

    FlyingIndex lastIndexOf(long fromIndex, ICodeBin code, int distance, int allowAhead);

    FlyingIndex lastIndexOf(String codeStr, int distance, int allowAhead);

    FlyingIndex lastIndexOf(long fromIndex, String codeStr, int distance, int allowAhead);

    IFlyingTransient getCore();

    IFlyingTransient transform(ITransformer<? extends ICodeBin, ? extends ICodeBin> transformer);

    @SuppressWarnings("unchecked")
    IFlyingTransient transform(ITransformer<? extends ICodeBin, ? extends ICodeBin>... transformers);

    void diag(String search, int distance, int allowAhead);

}
