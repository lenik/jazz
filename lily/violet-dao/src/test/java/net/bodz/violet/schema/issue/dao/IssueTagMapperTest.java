package net.bodz.violet.schema.issue.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.issue.IssueTag;
import net.bodz.violet.schema.issue.IssueTagSamples;

public class IssueTagMapperTest
        extends AbstractTableTest<IssueTag, IssueTagMapper> {

    @Override
    public IssueTag buildSample()
            throws Exception {
        IssueTagSamples a = new IssueTagSamples();
        return a.buildWired(tables);
    }

}
