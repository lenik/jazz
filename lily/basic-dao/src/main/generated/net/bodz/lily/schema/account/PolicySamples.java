package net.bodz.lily.schema.account;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class PolicySamples
        extends TestSampleBuilder {

    @Override
    public Policy build()
            throws Exception {
        Policy a = new Policy();
        a.setId(1470210804);
        a.setName("Eev lk.");
        a.setControlClass("Yaeicme, uuey hivzojd imco, ond");
        a.setMethodName("");
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
