package net.bodz.violet.shop.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.shop.Membership;
import net.bodz.violet.shop.MembershipSamples;

public class MembershipMapperTest
        extends AbstractMapperTest<Membership, MembershipMask, MembershipMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public Membership buildSample() {
        return MembershipSamples.build();
    }

}
