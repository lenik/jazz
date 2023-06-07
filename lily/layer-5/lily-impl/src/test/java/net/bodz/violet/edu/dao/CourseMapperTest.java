package net.bodz.violet.edu.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.Course;
import net.bodz.violet.edu.CourseSamples;

public class CourseMapperTest
        extends AbstractTableTest<Course, CourseMask, CourseMapper> {

    @Override
    public Course buildSample()
            throws Exception {
        CourseSamples a = new CourseSamples();
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.category = tables.pickAny(CourseCategoryMapper.class, "coursecat");
        return a.build();
    }

}
