package net.bodz.violet.issue.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.issue.IssueParameter;
import net.bodz.violet.issue.IssueParameterSamples;

public class IssueParameterMapperTest
        extends AbstractTableTest<IssueParameter, IssueParameterMapper> {

    @Override
    public IssueParameter buildSample()
            throws Exception {
        IssueParameterSamples a = new IssueParameterSamples();
        return a.buildWired(tables);
    }

}
