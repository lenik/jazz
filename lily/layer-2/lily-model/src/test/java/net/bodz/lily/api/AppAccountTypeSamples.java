package net.bodz.lily.api;

import net.bodz.lily.test.TestSamples;

public class AppAccountTypeSamples
        extends TestSamples {

    public static AppAccountType build() {
        AppAccountType a = new AppAccountType();
        a.setLabel("appAccountType-1");
        a.setDescription("A appAccountType named appAccountType-1.");
        return a;
    }

}
