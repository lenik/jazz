package net.bodz.violet.art.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.UOM;
import net.bodz.violet.art.UOMSamples;

public class UOMMapperTest
        extends AbstractTableTest<UOM, UOMMapper> {

    @Override
    public UOM buildSample()
            throws Exception {
        UOMSamples a = new UOMSamples();
        return a.buildWired(tables);
    }

}
