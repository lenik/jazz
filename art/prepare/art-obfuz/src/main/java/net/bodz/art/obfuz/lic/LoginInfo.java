package net.bodz.art.obfuz.lic;

public interface LoginInfo {

    String getLoginId();

    byte[] getPublicKey();

    EncryptedData getSessionKey();

}
