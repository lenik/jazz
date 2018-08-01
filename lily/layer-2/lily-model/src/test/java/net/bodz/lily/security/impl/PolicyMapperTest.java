package net.bodz.lily.security.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.security.Policy;
import net.bodz.lily.security.PolicySamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;

public class PolicyMapperTest
        extends AbstractMapperTest<Policy, PolicyMask, PolicyMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public Policy buildSample() {
        return PolicySamples.build();
    }

}
