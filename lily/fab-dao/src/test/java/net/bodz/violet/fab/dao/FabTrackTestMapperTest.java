package net.bodz.violet.fab.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.fab.FabTrackTest;
import net.bodz.violet.fab.FabTrackTestSamples;

public class FabTrackTestMapperTest
        extends AbstractTableTest<FabTrackTest, FabTrackTestMapper> {

    @Override
    public FabTrackTest buildSample()
            throws Exception {
        FabTrackTestSamples a = new FabTrackTestSamples();
        return a.buildWired(tables);
    }

}
