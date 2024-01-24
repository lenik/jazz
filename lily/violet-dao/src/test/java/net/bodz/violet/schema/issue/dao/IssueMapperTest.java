package net.bodz.violet.schema.issue.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.issue.Issue;
import net.bodz.violet.schema.issue.IssueSamples;

public class IssueMapperTest
        extends AbstractTableTest<Issue, IssueMapper> {

    @Override
    public Issue buildSample()
            throws Exception {
        IssueSamples a = new IssueSamples();
        return a.buildWired(tables);
    }

}
