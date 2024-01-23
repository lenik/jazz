package net.bodz.violet.fab.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.fab.FabTrackParty;
import net.bodz.violet.fab.FabTrackPartySamples;

public class FabTrackPartyManagerTest
        extends AbstractManagerTest<FabTrackParty, FabTrackPartyMapper, FabTrackPartyManager> {

    @Override
    public FabTrackParty buildSample()
            throws Exception {
        FabTrackPartySamples a = new FabTrackPartySamples();
        return a.buildWired(tables);
    }

}
