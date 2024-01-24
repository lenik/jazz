package net.bodz.lily.schema.vapp.dao;

import net.bodz.lily.schema.vapp.VAppCat;
import net.bodz.lily.schema.vapp.VAppCatSamples;
import net.bodz.lily.test.AbstractTableTest;

public class VAppCatMapperTest
        extends AbstractTableTest<VAppCat, VAppCatMapper> {

    @Override
    public VAppCat buildSample()
            throws Exception {
        VAppCatSamples a = new VAppCatSamples();
        return a.buildWired(tables);
    }

}
