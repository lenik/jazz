package net.bodz.violet.issue.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.issue.Issue;
import net.bodz.violet.issue.IssueSamples;

public class IssueMapperTest
        extends AbstractTableTest<Issue, IssueMapper> {

    @Override
    public Issue buildSample()
            throws Exception {
        IssueSamples a = new IssueSamples();
        return a.buildWired(tables);
    }

}
