package net.bodz.lily.schema.util.dao;

import net.bodz.lily.schema.util.Uom;
import net.bodz.lily.schema.util.UomSamples;
import net.bodz.lily.test.AbstractTableTest;

public class UomMapperTest
        extends AbstractTableTest<Uom, UomMapper> {

    @Override
    public Uom buildSample()
            throws Exception {
        UomSamples a = new UomSamples();
        return a.buildWired(tables);
    }

}
