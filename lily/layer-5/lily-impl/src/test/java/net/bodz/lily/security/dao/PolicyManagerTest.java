package net.bodz.lily.security.dao;

import net.bodz.lily.security.Policy;
import net.bodz.lily.security.PolicySamples;
import net.bodz.lily.test.AbstractManagerTest;

public class PolicyManagerTest
        extends AbstractManagerTest<Policy, PolicyMapper, PolicyManager> {

    @Override
    public Policy buildSample()
            throws Exception {
        PolicySamples a = new PolicySamples();
        return a.buildWired(tables);
    }

}
