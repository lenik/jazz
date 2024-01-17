package net.bodz.violet.edu.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.TestQuestionTag;
import net.bodz.violet.edu.TestQuestionTagSamples;

public class TestQuestionTagMapperTest
        extends AbstractTableTest<TestQuestionTag, TestQuestionTagCriteriaBuilder, TestQuestionTagMapper> {

    @Override
    public TestQuestionTag buildSample()
            throws Exception {
        TestQuestionTagSamples a = new TestQuestionTagSamples();
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.parent = tables.pickAny(TestQuestionTagMapper.class, "testqtag");
        return a.build();
    }

}
