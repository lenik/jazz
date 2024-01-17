package net.bodz.violet.edu.dao;

import net.bodz.lily.contact.dao.PersonMapper;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.TestApply;
import net.bodz.violet.edu.TestApplySamples;

public class TestApplyMapperTest
        extends AbstractTableTest<TestApply, TestApplyCriteriaBuilder, TestApplyMapper> {

    @Override
    public TestApply buildSample()
            throws Exception {
        TestApplySamples a = new TestApplySamples();
        a.person = tables.pickAny(PersonMapper.class, "person");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.paper = tables.pickAny(TestPaperMapper.class, "testpaper");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
