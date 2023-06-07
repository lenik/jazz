package net.bodz.violet.issue.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.issue.IssuePhase;
import net.bodz.violet.issue.IssuePhaseSamples;

public class IssuePhaseMapperTest
        extends AbstractTableTest<IssuePhase, IssuePhaseMask, IssuePhaseMapper> {

    @Override
    public IssuePhase buildSample()
            throws Exception {
        IssuePhaseSamples a = new IssuePhaseSamples();
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
