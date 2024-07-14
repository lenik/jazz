package net.bodz.lily.schema.util.dao;

import net.bodz.lily.schema.util.UomRow;
import net.bodz.lily.schema.util.UomRowSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class UomRowManagerTest
        extends AbstractManagerTest<UomRow, UomRowMapper, UomRowManager> {

    @Override
    public UomRow buildSample()
            throws Exception {
        UomRowSamples a = new UomRowSamples();
        return a.buildWired(tables);
    }

}
