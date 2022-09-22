package net.bodz.lily.api;

import net.bodz.lily.test.TestSamples;

public class AppApplySamples
        extends TestSamples {

    public static AppApply build() {
        AppApply a = new AppApply();
        a.setSubject("appApply-1");
        a.setRawText("A appApply named appApply-1.");
        return a;
    }

}
