package net.bodz.bas.crypto.trans;

import java.util.function.Function;

import net.bodz.bas.crypto.trans.fn.ICodeBin;

public class FlyingAuth
        implements
            IFlyingSupport {

    IFlyingTransient core;
    Function<String, Function<ICodeBin, ? extends ICodeBin>> transformer;
    int distance;
    int allowAhead;

    boolean debug = false;

    public FlyingAuth(IFlyingTransient core, //
            Function<String, Function<ICodeBin, ? extends ICodeBin>> transformer, //
            int distance, int allowAhead) {
        this.core = core;
        this.transformer = transformer;
        this.distance = distance;
        this.allowAhead = allowAhead;
    }

    public int getTimeout(int unit) {
        long ms = core.getWindow() * distance;
        return (int) (ms / unit);
    }

    public IFlyingTransient sign(String content) {
        return core.transform(transformer.apply(content));
    }

    public FlyingIndex checkSign(String content, String sign) {
        IFlyingTransient fSign = sign(content);
        FlyingIndex fi = fSign.lastIndexOf(sign, distance, allowAhead);
        if (debug && ! fi.exists()) {
            IFlyingTransient ft = fSign;
            ft.diag(sign, 10, 10);
        }
        return fi;
    }

    public final ISignatureChecker signChecker = //
            (String textData, String signature) //
            -> checkSign(textData, signature);

}
