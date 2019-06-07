package net.bodz.lily.api;

import net.bodz.lily.test.TestSamples;

public class ApiGrantedSamples
        extends TestSamples {

    public static ApiGranted build(App app, Api api) {
        ApiGranted a = new ApiGranted();
        a.setApp(app);
        a.setApi(api);
        a.setMode(random.nextInt(0x1000000));
        return a;
    }

}
