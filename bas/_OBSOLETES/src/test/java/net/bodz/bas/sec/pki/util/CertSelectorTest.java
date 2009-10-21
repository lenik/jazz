package net.bodz.bas.sec.pki.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;

import net.bodz.bas.io.Files;

import org.junit.Test;

public class CertSelectorTest {

    static File secdir;
    static File cacerts;
    static {
        if (secdir == null) {
            String javaHome = System.getenv("JAVA_HOME"); //$NON-NLS-1$
            if (javaHome != null) {
                File f = new File(javaHome, "jre/lib/security"); //$NON-NLS-1$
                if (f.isDirectory())
                    secdir = f;
            }
        }
        if (secdir != null) {
            cacerts = Files.canoniOf(secdir, "cacerts"); //$NON-NLS-1$
        }
    }

    public static CertSelector get() {
        if (secdir == null)
            return null;
        String cacertsPath = cacerts.getPath();
        String s = "JKS://changeit@" + cacertsPath; //$NON-NLS-1$
        CertSelector cs = new CertSelector(s);
        return cs;
    }

    public static CertSelector get(String alias) {
        if (secdir == null)
            return null;
        String cacertsPath = cacerts.getPath();
        String s = "JKS://changeit@" + cacertsPath + "#" + alias; //$NON-NLS-1$ //$NON-NLS-2$
        CertSelector cs = new CertSelector(s);
        return cs;
    }

    /**
     * <pre>
     * 别名名称： globalsignca
     *     创建日期： 2008-3-27
     *     输入类型： trustedCertEntry
     * 
     *     所有者:CN=GlobalSign Root CA, OU=Root CA, O=GlobalSign nv-sa, C=BE
     *     签发人:CN=GlobalSign Root CA, OU=Root CA, O=GlobalSign nv-sa, C=BE
     *     序列号:40000000001154b5ac394
     *     有效期: Tue Sep 01 20:00:00 CST 1998 至Fri Jan 28 20:00:00 CST 2028
     *     证书指纹:
     *          MD5:3E:45:52:15:09:51:92:E1:B7:5D:37:9F:B1:87:29:8A
     *          SHA1:B1:BC:96:8B:D4:F4:9D:62:2A:A8:9A:81:F2:15:01:52:A4:1D:82:9C
     *          签名算法名称:SHA1withRSA
     * 版本: 3
     */
    @Test
    public void test1() {
        String alias = "globalsignca"; //$NON-NLS-1$
        CertSelector cs = get(alias);
        if (cs == null)
            return;

        assertEquals("JKS", cs.getStoreType()); //$NON-NLS-1$
        assertEquals("changeit", cs.getStorePassword()); //$NON-NLS-1$
        assertEquals(cacerts, cs.getStoreFile());

        assertNull(cs.getCertPassword());
        assertEquals(alias, cs.getCertAlias());

        assertNull(cs.getSubEntry());

        // URL/File convertions.
        // String reformat = cs.toString();
        // assertEquals(s, reformat);
    }

}
