package net.bodz.lily.security.login.key;

public interface ISignatureChecker {

    /**
     * @param textData
     * @param signature
     *            signature string to be checked.
     */
    boolean checkSignature(String textData, String signature);

}
