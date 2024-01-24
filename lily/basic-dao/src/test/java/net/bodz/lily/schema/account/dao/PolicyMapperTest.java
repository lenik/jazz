package net.bodz.lily.schema.account.dao;

import net.bodz.lily.schema.account.Policy;
import net.bodz.lily.schema.account.PolicySamples;
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
