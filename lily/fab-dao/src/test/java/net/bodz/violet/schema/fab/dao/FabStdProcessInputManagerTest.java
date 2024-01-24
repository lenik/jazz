package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.fab.FabStdProcessInput;
import net.bodz.violet.schema.fab.FabStdProcessInputSamples;

public class FabStdProcessInputManagerTest
        extends AbstractManagerTest<FabStdProcessInput, FabStdProcessInputMapper, FabStdProcessInputManager> {

    @Override
    public FabStdProcessInput buildSample()
            throws Exception {
        FabStdProcessInputSamples a = new FabStdProcessInputSamples();
        return a.buildWired(tables);
    }

}
