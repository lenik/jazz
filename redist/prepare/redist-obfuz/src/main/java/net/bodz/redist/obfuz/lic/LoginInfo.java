package net.bodz.redist.obfuz.lic;

public interface LoginInfo {

    String getLoginId();

    byte[] getPublicKey();

    EncryptedData getSessionKey();

}
