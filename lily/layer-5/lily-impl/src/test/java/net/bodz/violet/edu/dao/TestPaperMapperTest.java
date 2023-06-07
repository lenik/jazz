package net.bodz.violet.edu.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.TestPaper;
import net.bodz.violet.edu.TestPaperSamples;

public class TestPaperMapperTest
        extends AbstractTableTest<TestPaper, TestPaperMask, TestPaperMapper> {

    @Override
    public TestPaper buildSample()
            throws Exception {
        TestPaperSamples a = new TestPaperSamples();
        a.course = tables.pickAny(CourseMapper.class, "course");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
