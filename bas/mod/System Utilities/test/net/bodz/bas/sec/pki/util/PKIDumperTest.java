package net.bodz.bas.sec.pki.util;

import java.security.KeyStoreException;

import net.bodz.bas.io.CharOuts;

public class PKIDumperTest {

    public void testDumpKeyStore() throws KeyStoreException {
        CertSelector cs = CertSelectorTest.get();
        cs.dump(CharOuts.stdout, 2);
    }

    public void testDumpEntry() throws KeyStoreException {
        String alias = "globalsignca";
        CertSelector cs = CertSelectorTest.get(alias);
        cs.dump(CharOuts.stdout, 2);
    }

    public static void main(String[] args) throws Throwable {
        new PKIDumperTest().testDumpKeyStore();
    }

}
