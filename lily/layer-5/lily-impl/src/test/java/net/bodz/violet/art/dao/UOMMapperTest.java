package net.bodz.violet.art.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.UOM;
import net.bodz.violet.art.UOMSamples;

public class UOMMapperTest
        extends AbstractTableTest<UOM, UOMCriteriaBuilder, UOMMapper> {

    @Override
    public UOM buildSample()
            throws Exception {
        UOMSamples a = new UOMSamples();
        a.std = tables.pickAny(UOMMapper.class, "uom");
        return a.build();
    }

}
