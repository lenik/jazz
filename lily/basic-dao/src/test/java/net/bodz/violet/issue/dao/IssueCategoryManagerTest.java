package net.bodz.violet.issue.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.issue.IssueCategory;
import net.bodz.violet.issue.IssueCategorySamples;

public class IssueCategoryManagerTest
        extends AbstractManagerTest<IssueCategory, IssueCategoryMapper, IssueCategoryManager> {

    @Override
    public IssueCategory buildSample()
            throws Exception {
        IssueCategorySamples a = new IssueCategorySamples();
        return a.buildWired(tables);
    }

}
