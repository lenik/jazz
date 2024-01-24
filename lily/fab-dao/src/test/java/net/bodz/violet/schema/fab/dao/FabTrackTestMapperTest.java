package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.fab.FabTrackTest;
import net.bodz.violet.schema.fab.FabTrackTestSamples;

public class FabTrackTestMapperTest
        extends AbstractTableTest<FabTrackTest, FabTrackTestMapper> {

    @Override
    public FabTrackTest buildSample()
            throws Exception {
        FabTrackTestSamples a = new FabTrackTestSamples();
        return a.buildWired(tables);
    }

}
