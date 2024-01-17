package net.bodz.violet.issue.dao;

import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.issue.IssueFav;
import net.bodz.violet.issue.IssueFavSamples;

public class IssueFavMapperTest
        extends AbstractTableTest<IssueFav, IssueFavCriteriaBuilder, IssueFavMapper> {

    @Override
    public IssueFav buildSample()
            throws Exception {
        IssueFavSamples a = new IssueFavSamples();
        a.user = tables.pickAny(UserMapper.class, "user");
        a.issue = tables.pickAny(IssueMapper.class, "issue");
        return a.build();
    }

}
