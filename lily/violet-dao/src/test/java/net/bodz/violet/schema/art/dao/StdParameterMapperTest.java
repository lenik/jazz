package net.bodz.violet.schema.art.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.art.StdParameter;
import net.bodz.violet.schema.art.StdParameterSamples;

public class StdParameterMapperTest
        extends AbstractTableTest<StdParameter, StdParameterMapper> {

    @Override
    public StdParameter buildSample()
            throws Exception {
        StdParameterSamples a = new StdParameterSamples();
        return a.buildWired(tables);
    }

}
