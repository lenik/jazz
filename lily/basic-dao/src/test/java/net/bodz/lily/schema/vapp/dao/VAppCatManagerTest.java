package net.bodz.lily.schema.vapp.dao;

import net.bodz.lily.schema.vapp.VAppCat;
import net.bodz.lily.schema.vapp.VAppCatSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class VAppCatManagerTest
        extends AbstractManagerTest<VAppCat, VAppCatMapper, VAppCatManager> {

    @Override
    public VAppCat buildSample()
            throws Exception {
        VAppCatSamples a = new VAppCatSamples();
        return a.buildWired(tables);
    }

}
