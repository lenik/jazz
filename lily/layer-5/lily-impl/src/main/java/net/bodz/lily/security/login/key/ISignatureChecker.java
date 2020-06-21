package net.bodz.lily.security.login.key;

import net.bodz.bas.crypto.trans.FlyingIndex;

public interface ISignatureChecker {

    /**
     * @param textData
     * @param signature
     *            signature string to be checked.
     */
    FlyingIndex checkSignature(String textData, String signature);

}
