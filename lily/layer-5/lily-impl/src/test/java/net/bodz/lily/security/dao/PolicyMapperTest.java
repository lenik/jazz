package net.bodz.lily.security.dao;

import net.bodz.lily.security.Policy;
import net.bodz.lily.security.PolicySamples;
import net.bodz.lily.test.AbstractTableTest;

public class PolicyMapperTest
        extends AbstractTableTest<Policy, PolicyMapper> {

    @Override
    public Policy buildSample()
            throws Exception {
        PolicySamples a = new PolicySamples();
        return a.buildWired(tables);
    }

}
