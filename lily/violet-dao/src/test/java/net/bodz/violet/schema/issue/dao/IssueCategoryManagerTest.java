package net.bodz.violet.schema.issue.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.issue.IssueCategory;
import net.bodz.violet.schema.issue.IssueCategorySamples;

public class IssueCategoryManagerTest
        extends AbstractManagerTest<IssueCategory, IssueCategoryMapper, IssueCategoryManager> {

    @Override
    public IssueCategory buildSample()
            throws Exception {
        IssueCategorySamples a = new IssueCategorySamples();
        return a.buildWired(tables);
    }

}
