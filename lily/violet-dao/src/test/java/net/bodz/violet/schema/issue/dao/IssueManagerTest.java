package net.bodz.violet.schema.issue.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.issue.Issue;
import net.bodz.violet.schema.issue.IssueSamples;

public class IssueManagerTest
        extends AbstractManagerTest<Issue, IssueMapper, IssueManager> {

    @Override
    public Issue buildSample()
            throws Exception {
        IssueSamples a = new IssueSamples();
        return a.buildWired(tables);
    }

}
