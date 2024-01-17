package net.bodz.violet.issue.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.issue.IssueCategory;
import net.bodz.violet.issue.IssueCategorySamples;

public class IssueCategoryMapperTest
        extends AbstractTableTest<IssueCategory, IssueCategoryMapper> {

    @Override
    public IssueCategory buildSample()
            throws Exception {
        IssueCategorySamples a = new IssueCategorySamples();
        return a.buildWired(tables);
    }

}
