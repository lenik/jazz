package net.bodz.violet.schema.issue.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.issue.IssueCategory;
import net.bodz.violet.schema.issue.IssueCategorySamples;

public class IssueCategoryMapperTest
        extends AbstractTableTest<IssueCategory, IssueCategoryMapper> {

    @Override
    public IssueCategory buildSample()
            throws Exception {
        IssueCategorySamples a = new IssueCategorySamples();
        return a.buildWired(tables);
    }

}
