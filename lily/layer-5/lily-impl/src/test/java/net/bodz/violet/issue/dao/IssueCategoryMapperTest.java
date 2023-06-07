package net.bodz.violet.issue.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.issue.IssueCategory;
import net.bodz.violet.issue.IssueCategorySamples;

public class IssueCategoryMapperTest
        extends AbstractTableTest<IssueCategory, IssueCategoryMask, IssueCategoryMapper> {

    @Override
    public IssueCategory buildSample()
            throws Exception {
        IssueCategorySamples a = new IssueCategorySamples();
        a.parent = tables.pickAny(IssueCategoryMapper.class, "issuecat");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
