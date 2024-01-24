package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.fab.FabTrackParty;
import net.bodz.violet.schema.fab.FabTrackPartySamples;

public class FabTrackPartyMapperTest
        extends AbstractTableTest<FabTrackParty, FabTrackPartyMapper> {

    @Override
    public FabTrackParty buildSample()
            throws Exception {
        FabTrackPartySamples a = new FabTrackPartySamples();
        return a.buildWired(tables);
    }

}
