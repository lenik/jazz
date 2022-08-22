package net.bodz.violet.issue.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.issue.Issue;
import net.bodz.violet.issue.IssueCategory;
import net.bodz.violet.issue.IssuePhase;
import net.bodz.violet.issue.IssueSamples;

public class IssueMapperTest
        extends AbstractTableTest<Issue, IssueMask, IssueMapper> {

    @Override
    public Issue buildSample() {
        IssueCategory category = tables.pickAny(IssueCategoryMapper.class, "issuecat");
        IssuePhase phase = tables.pickAny(IssuePhaseMapper.class, "issuephase");
        return IssueSamples.build(category, phase);
    }

}
