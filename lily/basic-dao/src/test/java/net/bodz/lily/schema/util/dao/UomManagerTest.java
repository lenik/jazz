package net.bodz.lily.schema.util.dao;

import net.bodz.lily.schema.util.Uom;
import net.bodz.lily.schema.util.UomSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class UomManagerTest
        extends AbstractManagerTest<Uom, UomMapper, UomManager> {

    @Override
    public Uom buildSample()
            throws Exception {
        UomSamples a = new UomSamples();
        return a.buildWired(tables);
    }

}
