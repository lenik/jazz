package net.bodz.violet.issue.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.issue.IssueParameter;
import net.bodz.violet.issue.IssueParameterSamples;

public class IssueParameterMapperTest
        extends AbstractTableTest<IssueParameter, IssueParameterCriteriaBuilder, IssueParameterMapper> {

    @Override
    public IssueParameter buildSample()
            throws Exception {
        IssueParameterSamples a = new IssueParameterSamples();
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        return a.build();
    }

}
