package net.bodz.bas.util.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import net.bodz.bas.err.UnexpectedException;

public class Cryptos {

    static MessageDigest newDigest(String alg) {
        try {
            MessageDigest digest = MessageDigest.getInstance(alg);
            return digest;
        } catch (NoSuchAlgorithmException e) {
            throw new UnexpectedException(String.format("Message digest %s isn\'t installed", alg));
        }
    }

    public static MessageDigest getMD5() {
        return newDigest("MD5");
    }

    public static MessageDigest getSHA1() {
        return newDigest("SHA1");
    }

    public static byte[] md5(byte[] bin) {
        MessageDigest md5 = getMD5();
        return md5.digest(bin);
    }

    public static byte[] sha1(byte[] bin) {
        MessageDigest sha1 = getSHA1();
        return sha1.digest(bin);
    }

}
