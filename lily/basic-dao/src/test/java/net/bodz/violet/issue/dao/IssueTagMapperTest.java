package net.bodz.violet.issue.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.issue.IssueTag;
import net.bodz.violet.issue.IssueTagSamples;

public class IssueTagMapperTest
        extends AbstractTableTest<IssueTag, IssueTagMapper> {

    @Override
    public IssueTag buildSample()
            throws Exception {
        IssueTagSamples a = new IssueTagSamples();
        return a.buildWired(tables);
    }

}
