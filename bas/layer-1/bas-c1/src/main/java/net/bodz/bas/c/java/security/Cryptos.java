package net.bodz.bas.c.java.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cryptos {

    static MessageDigest newDigest(String alg) {
        try {
            MessageDigest digest = MessageDigest.getInstance(alg);
            return digest;
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(String.format("Message digest %s isn\'t installed", alg));
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

    public static String md5(String s) {
        return md5(s, "utf-8");
    }

    public static String md5(String s, String encoding) {
        try {
            byte[] bin = s.getBytes(encoding);
            byte[] md5 = md5(bin);
            return toHex(md5);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Invalid encoding", e);
        }
    }

    public static String sha1(String s) {
        return sha1(s, "utf-8");
    }

    public static String sha1(String s, String encoding) {
        try {
            byte[] bin = s.getBytes(encoding);
            byte[] sha1 = sha1(bin);
            return toHex(sha1);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Invalid encoding", e);
        }
    }

    static char[] hextab = "0123456789abcdef".toCharArray();

    static String toHex(byte... bin) {
        StringBuilder sb = new StringBuilder(bin.length * 2);
        for (int i = 0; i < bin.length; i++) {
            int by = bin[i] & 0xff;
            sb.append(hextab[by >> 4]);
            sb.append(hextab[by & 0xf]);
        }
        return sb.toString();
    }

}
