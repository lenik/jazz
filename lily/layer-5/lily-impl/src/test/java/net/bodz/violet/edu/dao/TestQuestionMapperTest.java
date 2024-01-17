package net.bodz.violet.edu.dao;

import net.bodz.lily.schema.dao.FormDefMapper;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.TestQuestion;
import net.bodz.violet.edu.TestQuestionSamples;

public class TestQuestionMapperTest
        extends AbstractTableTest<TestQuestion, TestQuestionCriteriaBuilder, TestQuestionMapper> {

    @Override
    public TestQuestion buildSample()
            throws Exception {
        TestQuestionSamples a = new TestQuestionSamples();
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.course = tables.pickAny(CourseMapper.class, "course");
        a.form = tables.pickAny(FormDefMapper.class, "_form");
        a.op = tables.pickAny(UserMapper.class, "user");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
