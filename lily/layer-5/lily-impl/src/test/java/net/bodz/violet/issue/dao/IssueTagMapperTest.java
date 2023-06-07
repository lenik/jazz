package net.bodz.violet.issue.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.issue.IssueTag;
import net.bodz.violet.issue.IssueTagSamples;

public class IssueTagMapperTest
        extends AbstractTableTest<IssueTag, IssueTagMask, IssueTagMapper> {

    @Override
    public IssueTag buildSample()
            throws Exception {
        IssueTagSamples a = new IssueTagSamples();
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.parent = tables.pickAny(IssueTagMapper.class, "issuetag");
        return a.build();
    }

}
