package net.bodz.violet.fab.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.fab.FabTrackParty;
import net.bodz.violet.fab.FabTrackPartySamples;

public class FabTrackPartyMapperTest
        extends AbstractMapperTest<FabTrackParty, FabTrackPartyMask, FabTrackPartyMapper> {

    @Override
    public FabTrackParty buildSample() {
        return FabTrackPartySamples.build();
    }

}
