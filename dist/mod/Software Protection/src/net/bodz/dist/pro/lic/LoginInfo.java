package net.bodz.dist.pro.lic;

public interface LoginInfo {

    String getLoginId();

    byte[] getPublicKey();

    EncryptedData getSessionKey();

}
