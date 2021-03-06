package user.bean.pkcs11;

import java.security.KeyStore;
import java.security.Provider.Service;

import sun.security.pkcs11.SunPKCS11;

import net.bodz.bas.c.javax.security.auth.Providers;
import net.bodz.bas.t.iterator.Iterables;

@SuppressWarnings("all")
public class SunPKCS11Test {

    public static void main(String[] args)
            throws Throwable {
        String lib = "C:/lapiota/local/lib/ngp11v211.dll";
        SunPKCS11 p11 = (SunPKCS11) Providers.parse("PKCS11://" + lib + "#*?name=ft11");
        for (Service serv : p11.getServices()) {
            System.out.println(serv);
        }
        KeyStore ks = KeyStore.getInstance("PKCS11", p11);
        ks.aliases();
        for (String alias : Iterables.otp(ks.aliases()))
            System.out.println(alias);
    }

}
