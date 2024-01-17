package net.bodz.lily.util.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.lily.util.Uom;
import net.bodz.lily.util.UomSamples;

public class UomManagerTest
        extends AbstractManagerTest<Uom, UomMapper, UomManager> {

    @Override
    public Uom buildSample()
            throws Exception {
        UomSamples a = new UomSamples();
        return a.buildWired(tables);
    }

}
