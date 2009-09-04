package net.bodz.dist.pro.util;

import net.bodz.bas.lang.err.SystemException;
import net.bodz.dist.pro.pm.BasicPM;
import net.bodz.dist.pro.pm.ProtectException;
import net.bodz.dist.pro.pm.ProtectionModel;
import net.bodz.swt.gui.pfl.PageTestApp;

import org.junit.Test;

@Activation(prefix = "APT", segments = 3)
public class ActivatePageTest {

    static ProtectionModel           pm;
    static {
        try {
            pm = new BasicPM(0);
        } catch (SystemException e) {
            throw new Error(e);
        }
    }

    private ActivationByTargetString abt;

    public ActivatePageTest() {
        try {
            abt = new ActivationByTargetString(ActivatePageTest.class, pm);
        } catch (ProtectException e) {
            throw new Error(e);
        }
    }

    @Test
    public void test1() throws Exception {
        String code = abt.generateFor("next"); //$NON-NLS-1$
        System.out.println("Activation Code: " + code); //$NON-NLS-1$

        PageTestApp app = new PageTestApp(new ActivatePage(abt, "a:"));
        app.run();
    }

}
