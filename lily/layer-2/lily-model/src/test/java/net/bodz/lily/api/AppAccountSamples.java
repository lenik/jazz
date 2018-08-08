package net.bodz.lily.api;

import net.bodz.lily.test.TestSamples;

public class AppAccountSamples
        extends TestSamples {

    public static AppAccount build(App app, Api api, AppAccountType type) {
        AppAccount a = new AppAccount();
        a.setLabel("appAccount-1");
        a.setDescription("A appAccount named appAccount-1.");
        a.setApp(app);
        a.setApi(api);
        a.setType(type);
        a.setQuantity(random.nextInt(10000) / 100.0);
        return a;
    }

}
