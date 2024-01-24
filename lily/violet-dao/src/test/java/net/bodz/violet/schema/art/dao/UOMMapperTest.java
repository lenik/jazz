package net.bodz.violet.schema.art.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.art.UOM;
import net.bodz.violet.schema.art.UOMSamples;

public class UOMMapperTest
        extends AbstractTableTest<UOM, UOMMapper> {

    @Override
    public UOM buildSample()
            throws Exception {
        UOMSamples a = new UOMSamples();
        return a.buildWired(tables);
    }

}
