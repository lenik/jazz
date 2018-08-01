package net.bodz.violet.plan.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.plan.DiaryParty;
import net.bodz.violet.plan.DiaryPartySamples;

public class DiaryPartyMapperTest
        extends AbstractMapperTest<DiaryParty, DiaryPartyMask, DiaryPartyMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public DiaryParty buildSample() {
        return DiaryPartySamples.build();
    }

}
