package net.bodz.violet.issue.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.issue.IssueCategory;
import net.bodz.violet.issue.IssueCategorySamples;

public class IssueCategoryMapperTest
        extends AbstractMapperTest<IssueCategory, IssueCategoryMask, IssueCategoryMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public IssueCategory buildSample() {
        return IssueCategorySamples.build();
    }

}
