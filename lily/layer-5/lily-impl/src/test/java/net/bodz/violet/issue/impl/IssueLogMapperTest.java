package net.bodz.violet.issue.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.issue.Issue;
import net.bodz.violet.issue.IssueLog;
import net.bodz.violet.issue.IssueLogSamples;

public class IssueLogMapperTest
        extends AbstractMapperTest<IssueLog, IssueLogMask, IssueLogMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public IssueLog buildSample() {
        Issue issue = tables.pickAny(IssueMapper.class, "issue");
        return IssueLogSamples.build(issue);
    }

}
