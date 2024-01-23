package net.bodz.violet.issue.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.issue.IssueParameter;
import net.bodz.violet.issue.IssueParameterSamples;

public class IssueParameterManagerTest
        extends AbstractManagerTest<IssueParameter, IssueParameterMapper, IssueParameterManager> {

    @Override
    public IssueParameter buildSample()
            throws Exception {
        IssueParameterSamples a = new IssueParameterSamples();
        return a.buildWired(tables);
    }

}
