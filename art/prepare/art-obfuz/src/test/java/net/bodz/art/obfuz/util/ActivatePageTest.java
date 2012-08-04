package net.bodz.art.obfuz.util;

import net.bodz.art.obfuz.pm.BasicPM;
import net.bodz.art.obfuz.pm.ProtectionModel;
import net.bodz.swt.c3.test.PageTestApp;

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
        PageTestApp app = new PageTestApp(new ActivatePage(this, "a:"));
        app.run();
    }

}
