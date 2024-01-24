package net.bodz.violet.schema.issue.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.issue.IssueParameter;
import net.bodz.violet.schema.issue.IssueParameterSamples;

public class IssueParameterMapperTest
        extends AbstractTableTest<IssueParameter, IssueParameterMapper> {

    @Override
    public IssueParameter buildSample()
            throws Exception {
        IssueParameterSamples a = new IssueParameterSamples();
        return a.buildWired(tables);
    }

}
