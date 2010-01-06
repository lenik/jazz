package net.bodz.bas.security;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import net.bodz.bas.exceptions.RuntimizedException;
import net.bodz.bas.exceptions.UnexpectedException;
import net.bodz.bas.io.Files;

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

    static byte[] calc(Object in, MessageDigest digest) throws IOException {
        try {
            for (byte[] block : Files.readByBlock(in))
                digest.update(block);
        } catch (RuntimizedException e) {
            e.rethrow(IOException.class);
        }
        return digest.digest();
    }

    public static byte[] md5(byte[] bin) {
        MessageDigest md5 = getMD5();
        return md5.digest(bin);
    }

    public static byte[] md5(Object in) throws IOException {
        MessageDigest md5 = getMD5();
        return calc(in, md5);
    }

    public static byte[] sha1(byte[] bin) {
        MessageDigest sha1 = getSHA1();
        return sha1.digest(bin);
    }

    public static byte[] sha1(Object in) throws IOException {
        MessageDigest sha1 = getSHA1();
        return calc(in, sha1);
    }

}
