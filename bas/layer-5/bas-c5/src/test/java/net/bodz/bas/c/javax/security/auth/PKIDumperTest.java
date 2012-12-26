package net.bodz.bas.c.javax.security.auth;

import java.security.KeyStoreException;

import net.bodz.bas.sio.Stdio;

public class PKIDumperTest {

    public void testDumpKeyStore()
            throws KeyStoreException {
        CertSelector cs = CertSelectorTest.get();
        cs.dump(Stdio.cout, 2);
    }

    public void testDumpEntry()
            throws KeyStoreException {
        String alias = "globalsignca"; //$NON-NLS-1$
        CertSelector cs = CertSelectorTest.get(alias);
        cs.dump(Stdio.cout, 2);
    }

    public static void main(String[] args)
            throws Throwable {
        new PKIDumperTest().testDumpKeyStore();
    }

}
