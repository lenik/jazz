package net.bodz.art.obfuz.util;

import static org.junit.Assert.assertEquals;
import net.bodz.art.obfuz.pm.BasicPM;
import net.bodz.art.obfuz.util.Activation;
import net.bodz.art.obfuz.util.ActivationByTargetString;

import org.junit.Test;

@Activation(prefix = "PCLT", segments = 5)
public class ActivationByTargetStringTest {

    @Test
    public void test1() throws Exception {
        for (int seed = 0; seed < 5; seed++) {
            BasicPM pm = new BasicPM(seed);
            ActivationByTargetString abt = new ActivationByTargetString(getClass(), pm);
            String hostId = abt.getHostId();
            System.out.println("Host ID = " + hostId); //$NON-NLS-1$

            ActivationByTargetString rev = new ActivationByTargetString(hostId);
            String target = "hello你好"; //$NON-NLS-1$
            String code = rev.generateFor(target);
            System.out.println("Code for " + target + ": " + code); //$NON-NLS-1$ //$NON-NLS-2$

            System.out.println("Activated: " + abt.isActivated()); //$NON-NLS-1$
            abt.setActivateCode(code);
            String targetLoaded = abt.getTargetString();
            System.out.println("Target: \"" + targetLoaded + "\""); //$NON-NLS-1$ //$NON-NLS-2$

            assertEquals(target, targetLoaded);
        }
    }

}
