package net.bodz.violet.issue.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.issue.Issue;
import net.bodz.violet.issue.IssueCategory;
import net.bodz.violet.issue.IssuePhase;
import net.bodz.violet.issue.IssueSamples;

public class IssueMapperTest
        extends AbstractMapperTest<Issue, IssueMask, IssueMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public Issue buildSample() {
        IssueCategory category = tables.pickAny(IssueCategoryMapper.class, "issuecat");
        IssuePhase phase = tables.pickAny(IssuePhaseMapper.class, "issuephase");
        return IssueSamples.build(category, phase);
    }

}
