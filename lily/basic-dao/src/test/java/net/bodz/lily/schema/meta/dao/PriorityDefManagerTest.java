package net.bodz.lily.schema.meta.dao;

import net.bodz.lily.schema.meta.PriorityDef;
import net.bodz.lily.schema.meta.PriorityDefSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class PriorityDefManagerTest
        extends AbstractManagerTest<PriorityDef, PriorityDefMapper, PriorityDefManager> {

    @Override
    public PriorityDef buildSample()
            throws Exception {
        PriorityDefSamples a = new PriorityDefSamples();
        return a.buildWired(tables);
    }

}
