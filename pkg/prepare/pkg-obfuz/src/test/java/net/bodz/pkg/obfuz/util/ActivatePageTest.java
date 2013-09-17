package net.bodz.pkg.obfuz.util;

import org.junit.Test;

import net.bodz.pkg.obfuz.pm.BasicPM;
import net.bodz.pkg.obfuz.pm.IProtectionModel;
import net.bodz.swt.test.PageTester;

@Activation(productId = "net.bodz.dist.pro.util.ActTest", prefix = "APT", segments = 3)
public class ActivatePageTest
        implements ABTSProvider {

    @Override
    public ActivationByTargetString getABTS() {
        IProtectionModel pm;
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
