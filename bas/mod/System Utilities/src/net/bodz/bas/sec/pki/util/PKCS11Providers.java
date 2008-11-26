package net.bodz.bas.sec.pki.util;

import java.io.InputStream;
import java.security.Provider;
import java.security.Security;

import sun.security.pkcs11.SunPKCS11;

public class PKCS11Providers {

    public static Provider load(PKCS11Config config) {
        // generate config file
        String path = null;
        InputStream in = null;
        SunPKCS11 provider = new SunPKCS11(in);
        Security.addProvider(provider);
        return null;
    }

}
