package net.bodz.redist.obfuz.util;

import net.bodz.redist.obfuz.pm.BasicPM;
import net.bodz.redist.obfuz.pm.ProtectionModel;
import net.bodz.swt.c3.test.PageTester;

import org.junit.Test;

@Activation(productId = "net.bodz.dist.pro.util.ActTest", prefix = "APT", segments = 3)
public class ActivatePageTest
        implements ABTSProvider {

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
    public void test1()
            throws Exception {
        ActivationByTargetString abts = getABTS();
        String code = abts.generateFor("next");
        System.out.println("Activation Code: " + code);
        PageTester app = new PageTester(new ActivatePage(this, "a:"));
        app.run();
    }

}
