package net.bodz.violet.issue.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.issue.IssueCategory;
import net.bodz.violet.issue.IssueCategorySamples;

public class IssueCategoryMapperTest
        extends AbstractTableTest<IssueCategory, IssueCategoryMask, IssueCategoryMapper> {

    @Override
    public IssueCategory buildSample() {
        return IssueCategorySamples.build();
    }

}
