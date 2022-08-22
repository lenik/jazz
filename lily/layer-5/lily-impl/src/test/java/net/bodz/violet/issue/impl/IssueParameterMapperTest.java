package net.bodz.violet.issue.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.issue.IssueParameter;
import net.bodz.violet.issue.IssueParameterSamples;

public class IssueParameterMapperTest
        extends AbstractTableTest<IssueParameter, IssueParameterMask, IssueParameterMapper> {

    @Override
    public IssueParameter buildSample() {
        return IssueParameterSamples.build();
    }

}
