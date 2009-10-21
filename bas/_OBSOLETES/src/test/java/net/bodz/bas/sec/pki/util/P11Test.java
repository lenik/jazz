package net.bodz.bas.sec.pki.util;

import java.security.KeyStore;
import java.security.Provider.Service;

import net.bodz.bas.types.util.Iterates;
import sun.security.pkcs11.SunPKCS11;

public class P11Test {

    public static void main(String[] args) throws Throwable {
        String lib = "C:/lapiota/local/lib/ngp11v211.dll"; //$NON-NLS-1$
        SunPKCS11 p11 = (SunPKCS11) Providers.parse("PKCS11://" + lib //$NON-NLS-1$
                + "#*?name=ft11"); //$NON-NLS-1$
        for (Service serv : p11.getServices()) {
            System.out.println(serv);
        }
        KeyStore ks = KeyStore.getInstance("PKCS11", p11); //$NON-NLS-1$
        ks.aliases();
        for (String alias : Iterates.once(ks.aliases()))
            System.out.println(alias);
    }

}
