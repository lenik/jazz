package net.bodz.lily.api;

import net.bodz.lily.test.TestSamples;

public class AppSamples
        extends TestSamples {

    public static App build(AppApply apply) {
        App a = new App();
        a.setLabel("app-1");
        a.setDescription("A app named app-1.");
        a.setSecret("aSecRet");
        a.setApply(apply);
        return a;
    }

}
