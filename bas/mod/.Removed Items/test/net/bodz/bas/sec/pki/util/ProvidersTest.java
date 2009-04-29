package net.bodz.bas.sec.pki.util;

import java.io.File;
import java.security.KeyStore;
import java.security.Provider;
import java.security.Security;

import junit.framework.TestCase;
import net.bodz.bas.io.CharOuts;
import net.bodz.bas.lang.err.ParseException;

import org.junit.Test;

public class ProvidersTest extends TestCase {

    File    lapiota;
    File    library;
    boolean usbkey;

    public ProvidersTest() {
        String lap = System.getenv("LAPIOTA"); //$NON-NLS-1$
        if (lap != null) {
            lapiota = new File(lap);
            library = new File(lap, "local/lib/ngp11v211.dll"); //$NON-NLS-1$
            if (library.exists())
                usbkey = true;
        }
    }

    @Test
    public void testUsbkey() throws Exception {
        if (!usbkey) {
            System.err.println("Ignored."); //$NON-NLS-1$
            return;
        }
        String name = "ft11"; //$NON-NLS-1$
        String curl = "PKCS11://" + library + "#*?name=" + name; //$NON-NLS-1$ //$NON-NLS-2$
        Provider provider;
        try {
            provider = Providers.parse(curl);
        } catch (ParseException e) {
            // maybe no slot available, skip the test.
            return;
        }
        assertNotNull(provider);

        Provider reuse1 = Security.getProvider("SunPKCS11-" + name); //$NON-NLS-1$
        assertEquals(provider, reuse1);

        Provider reuse2 = Providers.parse(curl);
        assertEquals(provider, reuse2);

        PKIDumper dumper = new PKIDumper(CharOuts.stdout, 4);
        dumper.dumpProvider("", provider, "kl"); //$NON-NLS-1$ //$NON-NLS-2$
        CharOuts.stdout.flush();

        // // get keystore using builder
        // sprov.load((InputStream) null);
        // sprov.login(subject, handler)

        System.out.println("-- dump keystore thru CertSelector --"); //$NON-NLS-1$
        String password = "kl"; //$NON-NLS-1$
        curl = "PKCS11://" + password + "@"; //$NON-NLS-1$ //$NON-NLS-2$
        CertSelector cs = new CertSelector(curl, provider);
        cs.dump(CharOuts.stdout, 2);

        KeyStore keyStore = cs.getKeyStore();
        System.out.println(keyStore);
    }

}
