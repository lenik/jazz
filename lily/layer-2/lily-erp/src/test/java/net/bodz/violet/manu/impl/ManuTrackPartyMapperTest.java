package net.bodz.violet.manu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.manu.ManuTrackParty;
import net.bodz.violet.manu.ManuTrackPartySamples;

public class ManuTrackPartyMapperTest
        extends AbstractMapperTest<ManuTrackParty, ManuTrackPartyMask, ManuTrackPartyMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ManuTrackParty buildSample() {
        return ManuTrackPartySamples.build();
    }

}
