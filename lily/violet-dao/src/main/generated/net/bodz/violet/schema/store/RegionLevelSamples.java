package net.bodz.violet.schema.store;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class RegionLevelSamples
        extends TestSampleBuilder {

    @Override
    public RegionLevel build()
            throws Exception {
        RegionLevel a = new RegionLevel();
        a.setDummy(1712582940);
        return a;
    }

    @Override
    public RegionLevelSamples wireAny(IRandomPicker picker) {
        return this;
    }

    @Override
    public RegionLevel buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
