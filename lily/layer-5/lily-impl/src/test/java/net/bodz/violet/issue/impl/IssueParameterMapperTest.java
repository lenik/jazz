package net.bodz.violet.issue.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.issue.IssueParameter;
import net.bodz.violet.issue.IssueParameterSamples;

public class IssueParameterMapperTest
        extends AbstractMapperTest<IssueParameter, IssueParameterMask, IssueParameterMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public IssueParameter buildSample() {
        return IssueParameterSamples.build();
    }

}
