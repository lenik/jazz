package net.bodz.violet.issue.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.issue.Issue;
import net.bodz.violet.issue.IssueSamples;

public class IssueManagerTest
        extends AbstractManagerTest<Issue, IssueMapper, IssueManager> {

    @Override
    public Issue buildSample()
            throws Exception {
        IssueSamples a = new IssueSamples();
        return a.buildWired(tables);
    }

}
