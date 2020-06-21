package net.bodz.lily.security.login.key;

import net.bodz.bas.crypto.trans.FlyingIndex;
import net.bodz.bas.crypto.trans.IFlyingSupport.tr;
import net.bodz.bas.crypto.trans.IFlyingTransient;

public class FlyingSignatureChecker
        implements ISignatureChecker {

    IFlyingTransient core;
    int distance;

    public FlyingSignatureChecker(IFlyingTransient core, int distance) {
        this.core = core;
        this.distance = distance;
    }

    public IFlyingTransient fly(String text) {
        return core.transform(tr.sha1Sign(text));
    }

    @Override
    public FlyingIndex checkSignature(String textData, String signature) {
        IFlyingTransient flyingCode = fly(textData);
        FlyingIndex fi = flyingCode.lastIndexOf(signature, distance);
        return fi;
    }

}