package net.bodz.redist.obfuz.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import net.bodz.redist.obfuz.pm.BasicPM;

@Activation(prefix = "PCLT", segments = 5)
public class ActivationByTargetStringTest {

    @Test
    public void test1()
            throws Exception {
        for (int seed = 0; seed < 5; seed++) {
            BasicPM pm = new BasicPM(seed);
            ActivationByTargetString abt = new ActivationByTargetString(getClass(), pm);
            String hostId = abt.getHostId();
            System.out.println("Host ID = " + hostId);

            ActivationByTargetString rev = new ActivationByTargetString(hostId);
            String target = "hello你好";
            String code = rev.generateFor(target);
            System.out.println("Code for " + target + ": " + code);

            System.out.println("Activated: " + abt.isActivated());
            abt.setActivateCode(code);
            String targetLoaded = abt.getTargetString();
            System.out.println("Target: \"" + targetLoaded + "\"");

            assertEquals(target, targetLoaded);
        }
    }

}
