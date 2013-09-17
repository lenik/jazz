package net.bodz.pkg.obfuz.lic;

public interface LoginInfo {

    String getLoginId();

    byte[] getPublicKey();

    EncryptedData getSessionKey();

}
