package net.bodz.lily.security.impl;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.Policy;
import net.bodz.lily.security.PolicySamples;
import net.bodz.lily.security.User;
import net.bodz.lily.test.AbstractTableTest;

public class PolicyMapperTest
        extends AbstractTableTest<Policy, PolicyMask, PolicyMapper> {

    @Override
    public Policy buildSample() {
        Group group = tables.pickAny(GroupMapper.class, "group");
        User user = tables.pickAny(UserMapper.class, "user");
        return PolicySamples.build(group, user);
    }

}
