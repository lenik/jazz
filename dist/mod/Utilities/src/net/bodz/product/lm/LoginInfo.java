package net.bodz.product.lm;

public interface LoginInfo {

    String getLoginId(); 
    byte[] getPublicKey(); 
    EncryptedData getSessionKey(); 
    
}
