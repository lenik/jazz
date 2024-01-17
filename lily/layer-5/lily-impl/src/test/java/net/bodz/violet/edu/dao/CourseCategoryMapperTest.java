package net.bodz.violet.edu.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.CourseCategory;
import net.bodz.violet.edu.CourseCategorySamples;

public class CourseCategoryMapperTest
        extends AbstractTableTest<CourseCategory, CourseCategoryCriteriaBuilder, CourseCategoryMapper> {

    @Override
    public CourseCategory buildSample()
            throws Exception {
        CourseCategorySamples a = new CourseCategorySamples();
        a.parent = tables.pickAny(CourseCategoryMapper.class, "coursecat");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
