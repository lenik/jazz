package net.bodz.lily.vapp.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.lily.vapp.VAppCat;
import net.bodz.lily.vapp.VAppCatSamples;

public class VAppCatMapperTest
        extends AbstractTableTest<VAppCat, VAppCatMapper> {

    @Override
    public VAppCat buildSample()
            throws Exception {
        VAppCatSamples a = new VAppCatSamples();
        return a.buildWired(tables);
    }

}
