package net.bodz.violet.edu.dao;

import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.TestQuestionFav;
import net.bodz.violet.edu.TestQuestionFavSamples;

public class TestQuestionFavMapperTest
        extends AbstractTableTest<TestQuestionFav, TestQuestionFavCriteriaBuilder, TestQuestionFavMapper> {

    @Override
    public TestQuestionFav buildSample()
            throws Exception {
        TestQuestionFavSamples a = new TestQuestionFavSamples();
        a.testq = tables.pickAny(TestQuestionMapper.class, "testq");
        a.user = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
