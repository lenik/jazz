package net.bodz.violet.issue.dao;

import net.bodz.lily.schema.dao.FormDefMapper;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.issue.Issue;
import net.bodz.violet.issue.IssueSamples;

public class IssueMapperTest
        extends AbstractTableTest<Issue, IssueMask, IssueMapper> {

    @Override
    public Issue buildSample()
            throws Exception {
        IssueSamples a = new IssueSamples();
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.op = tables.pickAny(UserMapper.class, "user");
        a.category = tables.pickAny(IssueCategoryMapper.class, "issuecat");
        a.phase = tables.pickAny(IssuePhaseMapper.class, "issuephase");
        a.form = tables.pickAny(FormDefMapper.class, "_form");
        return a.build();
    }

}
