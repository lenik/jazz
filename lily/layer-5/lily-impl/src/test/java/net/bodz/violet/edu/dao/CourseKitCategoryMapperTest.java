package net.bodz.violet.edu.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.CourseKitCategory;
import net.bodz.violet.edu.CourseKitCategorySamples;

public class CourseKitCategoryMapperTest
        extends AbstractTableTest<CourseKitCategory, CourseKitCategoryMask, CourseKitCategoryMapper> {

    @Override
    public CourseKitCategory buildSample()
            throws Exception {
        CourseKitCategorySamples a = new CourseKitCategorySamples();
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.parent = tables.pickAny(CourseKitCategoryMapper.class, "coursekitcat");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        return a.build();
    }

}
