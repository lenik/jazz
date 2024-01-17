package net.bodz.violet.edu.dao;

import net.bodz.lily.schema.dao.FormDefMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.TestQuestionTalk;
import net.bodz.violet.edu.TestQuestionTalkSamples;

public class TestQuestionTalkMapperTest
        extends AbstractTableTest<TestQuestionTalk, TestQuestionTalkCriteriaBuilder, TestQuestionTalkMapper> {

    @Override
    public TestQuestionTalk buildSample()
            throws Exception {
        TestQuestionTalkSamples a = new TestQuestionTalkSamples();
        a.form = tables.pickAny(FormDefMapper.class, "_form");
        a.op = tables.pickAny(UserMapper.class, "user");
        a.parent = tables.pickAny(TestQuestionTalkMapper.class, "testq_msg");
        a.question = tables.pickAny(TestQuestionMapper.class, "testq");
        return a.build();
    }

}
