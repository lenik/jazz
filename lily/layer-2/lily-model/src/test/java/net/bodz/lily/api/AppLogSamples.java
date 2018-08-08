package net.bodz.lily.api;

import net.bodz.lily.test.TestSamples;

public class AppLogSamples
        extends TestSamples {

    public static AppLog build(App app, Api api) {
        AppLog a = new AppLog();
        a.setLabel("appLog-1");
        a.setDescription("A appLog named appLog-1.");
        a.setApp(app);
        a.setApi(api);
        a.setError("foo.bar.ExpectedError");
        a.setMessage("some message...");
        return a;
    }

}
