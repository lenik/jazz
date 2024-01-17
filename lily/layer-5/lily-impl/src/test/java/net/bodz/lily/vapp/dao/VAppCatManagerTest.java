package net.bodz.lily.vapp.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.lily.vapp.VAppCat;
import net.bodz.lily.vapp.VAppCatSamples;

public class VAppCatManagerTest
        extends AbstractManagerTest<VAppCat, VAppCatMapper, VAppCatManager> {

    @Override
    public VAppCat buildSample()
            throws Exception {
        VAppCatSamples a = new VAppCatSamples();
        return a.buildWired(tables);
    }

}
