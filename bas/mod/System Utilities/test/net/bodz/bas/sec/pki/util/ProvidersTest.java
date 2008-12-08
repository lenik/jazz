package net.bodz.bas.sec.pki.util;

import java.io.File;
import java.security.KeyStore;
import java.security.Provider;
import java.security.Security;

import junit.framework.TestCase;
import net.bodz.bas.io.CharOuts;

import org.junit.Test;

public class ProvidersTest extends TestCase {

    File    lapiota;
    File    library;
    boolean usbkey;

    public ProvidersTest() {
        String lap = System.getenv("LAPIOTA");
        if (lap != null) {
            lapiota = new File(lap);
            library = new File(lap, "local/lib/ngp11v211.dll");
            if (library.exists())
                usbkey = true;
        }
    }

    @Test
    public void testUsbkey() throws Exception {
        if (!usbkey) {
            System.err.println("Ignored.");
            return;
        }
        String name = "ft11";
        String curl = "PKCS11://" + library + "#*?name=" + name;
        Provider provider = Providers.parse(curl);
        assertNotNull(provider);

        Provider reuse1 = Security.getProvider("SunPKCS11-" + name);
        assertEquals(provider, reuse1);

        Provider reuse2 = Providers.parse(curl);
        assertEquals(provider, reuse2);

        PKIDumper dumper = new PKIDumper(CharOuts.stdout, 4);
        dumper.dumpProvider("", provider, "kl");
        CharOuts.stdout.flush();

        // // get keystore using builder
        // sprov.load((InputStream) null);
        // sprov.login(subject, handler)

        System.out.println("-- dump keystore thru CertSelector --");
        String password = "kl";
        curl = "PKCS11://" + password + "@";
        CertSelector cs = new CertSelector(curl, provider);
        cs.dump(CharOuts.stdout, 2);

        KeyStore keyStore = cs.getKeyStore();
        System.out.println(keyStore);
    }

}
