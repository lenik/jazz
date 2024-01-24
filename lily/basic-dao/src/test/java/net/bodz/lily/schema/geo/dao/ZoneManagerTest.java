package net.bodz.lily.schema.geo.dao;

import net.bodz.lily.schema.geo.Zone;
import net.bodz.lily.schema.geo.ZoneSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class ZoneManagerTest
        extends AbstractManagerTest<Zone, ZoneMapper, ZoneManager> {

    @Override
    public Zone buildSample()
            throws Exception {
        ZoneSamples a = new ZoneSamples();
        return a.buildWired(tables);
    }

}
