package net.bodz.dist.lm;

public interface LoginInfo {

    String getLoginId();

    byte[] getPublicKey();

    EncryptedData getSessionKey();

}
