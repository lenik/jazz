package net.bodz.dist.pro.util;

import net.bodz.bas.lang.err.SystemException;
import net.bodz.dist.pro.pm.BasicPM;
import net.bodz.dist.pro.pm.ProtectException;
import net.bodz.dist.pro.pm.ProtectionModel;
import net.bodz.dist.pro.util.ActivatePage;
import net.bodz.dist.pro.util.Activation;
import net.bodz.dist.pro.util.ActivationByTargetString;
import net.bodz.swt.gui.pfl.PageComposite;
import net.bodz.swt.gui.pfl.PageTestApp;

import org.eclipse.swt.widgets.Composite;
import org.junit.Test;

@Activation(prefix = "APT", segments = 3)
public class ActivatePageTest extends PageTestApp {

    static ProtectionModel pm;
    static {
        try {
            pm = new BasicPM(0);
        } catch (SystemException e) {
            throw new Error(e);
        }
    }

    @Override
    protected PageComposite createPage(Composite parent, int style) {
        ActivationByTargetString abt;
        try {
            abt = new ActivationByTargetString(ActivatePageTest.class, pm);
        } catch (ProtectException e) {
            throw new RuntimeException(e);
        }
        return new ActivatePage(parent, style, abt);
    }

    @Test
    public void test1() throws Exception {
        ActivationByTargetString abt = new ActivationByTargetString(getClass(), pm);
        String code = abt.generateFor("next"); //$NON-NLS-1$
        System.out.println("Activation Code: " + code); //$NON-NLS-1$
        run();
    }

}
