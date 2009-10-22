package net.bodz.dist.pro.util;

import net.bodz.dist.pro.pm.BasicPM;
import net.bodz.dist.pro.pm.ProtectionModel;
import net.bodz.swt.gui.pfl.PageTestApp;

import org.junit.Test;

@Activation(productId = "net.bodz.dist.pro.util.ActTest", prefix = "APT", segments = 3)
public class ActivatePageTest implements ABTSProvider {

    @Override
    public ActivationByTargetString getABTS() {
        ProtectionModel pm;
        ActivationByTargetString abt;
        try {
            pm = new BasicPM(0);
            abt = new ActivationByTargetString(ActivatePageTest.class, pm);
            return abt;
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    @Test
    public void test1() throws Exception {
        ActivationByTargetString abts = getABTS();
        String code = abts.generateFor("next"); //$NON-NLS-1$
        System.out.println("Activation Code: " + code); //$NON-NLS-1$
        PageTestApp app = new PageTestApp(new ActivatePage(this, "a:"));
        app.run();
    }

}
