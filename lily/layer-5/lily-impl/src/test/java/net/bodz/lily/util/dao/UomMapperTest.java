package net.bodz.lily.util.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.lily.util.Uom;
import net.bodz.lily.util.UomSamples;

public class UomMapperTest
        extends AbstractTableTest<Uom, UomCriteriaBuilder, UomMapper> {

    @Override
    public Uom buildSample()
            throws Exception {
        UomSamples a = new UomSamples();
        a.std = tables.pickAny(UomMapper.class, "uom");
        return a.build();
    }

}
