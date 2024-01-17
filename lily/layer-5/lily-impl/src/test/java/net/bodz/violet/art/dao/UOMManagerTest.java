package net.bodz.violet.art.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.art.UOM;
import net.bodz.violet.art.UOMSamples;

public class UOMManagerTest
        extends AbstractManagerTest<UOM, UOMMapper, UOMManager> {

    @Override
    public UOM buildSample()
            throws Exception {
        UOMSamples a = new UOMSamples();
        return a.buildWired(tables);
    }

}
