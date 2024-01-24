package net.bodz.lily.schema.geo.dao;

import net.bodz.lily.schema.geo.Zone;
import net.bodz.lily.schema.geo.ZoneSamples;
import net.bodz.lily.test.AbstractTableTest;

public class ZoneMapperTest
        extends AbstractTableTest<Zone, ZoneMapper> {

    @Override
    public Zone buildSample()
            throws Exception {
        ZoneSamples a = new ZoneSamples();
        return a.buildWired(tables);
    }

}
