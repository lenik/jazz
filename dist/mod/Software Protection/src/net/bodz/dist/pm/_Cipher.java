package net.bodz.dist.pm;

import java.security.Provider;

import javax.crypto.Cipher;
import javax.crypto.CipherSpi;

public class _Cipher extends Cipher {

    public _Cipher(CipherSpi arg0, Provider arg1, String arg2) {
        super(arg0, arg1, arg2);
    }

}
