package net.bodz.lily.security.login.key;

import org.apache.commons.codec.digest.DigestUtils;

public class FlyingSignatureChecker
        implements ISignatureChecker {

    long window;
    int distance;

    public FlyingSignatureChecker(long window, int distance) {
        this.window = window;
        this.distance = distance;
    }

    public IFlyingCode getFlyingCode(final String text) {
        IFlyingCode flyingCode = new AbstractFlyingCode(window) {
            @Override
            public String getCode(long index) {
                String salt = salt(index);
                String guard = salt + text + salt;
                String sha = DigestUtils.shaHex(guard);
                return sha;
            }
        };
        return flyingCode;
    }

    @Override
    public boolean checkSignature(String textData, String signature) {
        IFlyingCode flyingCode = getFlyingCode(textData);
        FlyingIndex fi = flyingCode.lastIndexOf(signature, distance);
        return fi.exists();
    }

    public String getSalt() {
        long time = System.currentTimeMillis();
        long index = time / window;
        return salt(index);
    }

    String salt(long index) {
        long hash = index * 17;
        hash %= 1_000_000_000;
        return String.valueOf(hash);
    }

}