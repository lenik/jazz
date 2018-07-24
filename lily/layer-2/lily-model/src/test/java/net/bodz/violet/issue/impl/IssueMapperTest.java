package net.bodz.violet.issue.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.model.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.issue.Issue;
import net.bodz.violet.issue.IssueSamples;

public class IssueMapperTest
        extends AbstractMapperTest<Issue, IssueMask, IssueMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public Issue buildSample() {
        return IssueSamples.build();
    }

}
