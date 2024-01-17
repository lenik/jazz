package net.bodz.violet.fab.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.fab.FabTrackTestParameter;
import net.bodz.violet.fab.FabTrackTestParameterSamples;

public class FabTrackTestParameterMapperTest
        extends AbstractTableTest<FabTrackTestParameter, FabTrackTestParameterMapper> {

    @Override
    public FabTrackTestParameter buildSample()
            throws Exception {
        FabTrackTestParameterSamples a = new FabTrackTestParameterSamples();
        return a.buildWired(tables);
    }

}
