package net.bodz.lily.security;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class PolicySamples
        extends TestSampleBuilder {

    @Override
    public Policy build()
            throws Exception {
        Policy a = new Policy();
        a.setId(1470210804);
        a.setControlClass("Eev lkjp_yaeicme, uuey hivzojd imco, onde, vzig ke&be@eeqooq.");
        a.setMethodName("ufo'ioruoa, xqoc. mutu ulo, phue, lqd, ekcva ubuab jsdo vajejo.");
        a.setAllowBits(1498033901);
        a.setDenyBits(317561887);
        return a;
    }

    @Override
    public PolicySamples wireAny(IRandomPicker picker) {
        return this;
    }

    @Override
    public Policy buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
