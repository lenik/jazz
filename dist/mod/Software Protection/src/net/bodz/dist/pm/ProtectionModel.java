package net.bodz.dist.pm;

import javax.crypto.Cipher;

import net.bodz.dist.seals.Sequence;

public interface ProtectionModel {

    CiscVM getActivationVM();

    /**
     * combinable-key cipher.
     * 
     * A CK-cipher meets:
     * <code>X==decode(encode(encode(X, K1), K2), K1+K2)</code>
     * 
     * where K1+K2 using {@link #keyAdd(byte[], byte[])} function.
     */
    Cipher getCKCipher();

    byte[] keyAdd(byte[] k1, byte[] k2);

    byte[] keyInv(byte[] k);

    Sequence getMasterSeq();

    Sequence getUserSequence();

    void precrypt();

}
