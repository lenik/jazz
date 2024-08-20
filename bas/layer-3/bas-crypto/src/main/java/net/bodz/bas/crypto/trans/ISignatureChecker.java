package net.bodz.bas.crypto.trans;

@FunctionalInterface
public interface ISignatureChecker {

    /**
     * @param textData
     * @param signature
     *            signature string to be checked.
     */
    FlyingIndex checkSignature(String textData, String signature);

}
