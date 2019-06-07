package net.bodz.lily.api;

import net.bodz.lily.test.TestSamples;

public class AppCreditSamples
        extends TestSamples {

    public static AppCredit build(App app, Api api) {
        AppCredit a = new AppCredit();
        a.setLabel("appCredit-1");
        a.setDescription("A appCredit named appCredit-1.");
        a.setApp(app);
        a.setApi(api);
        a.setCredit(random.nextInt(10000) / 100.0);
        return a;
    }

}
