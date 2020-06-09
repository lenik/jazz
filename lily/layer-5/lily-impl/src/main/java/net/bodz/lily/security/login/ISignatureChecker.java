package net.bodz.lily.security.login;

public interface ISignatureChecker {

    /**
     * @return random or unique key used to encode(text) to target digest.
     */
    boolean checkSignature(Object data, Object signature);

}
