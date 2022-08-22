package net.bodz.violet.fab.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.fab.FabTrackParty;
import net.bodz.violet.fab.FabTrackPartySamples;

public class FabTrackPartyMapperTest
        extends AbstractTableTest<FabTrackParty, FabTrackPartyMask, FabTrackPartyMapper> {

    @Override
    public FabTrackParty buildSample() {
        return FabTrackPartySamples.build();
    }

}
