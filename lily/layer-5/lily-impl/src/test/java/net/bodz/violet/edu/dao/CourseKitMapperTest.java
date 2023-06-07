package net.bodz.violet.edu.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.CourseKit;
import net.bodz.violet.edu.CourseKitSamples;

public class CourseKitMapperTest
        extends AbstractTableTest<CourseKit, CourseKitMask, CourseKitMapper> {

    @Override
    public CourseKit buildSample()
            throws Exception {
        CourseKitSamples a = new CourseKitSamples();
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.category = tables.pickAny(CourseKitCategoryMapper.class, "coursekitcat");
        a.course = tables.pickAny(CourseMapper.class, "course");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        return a.build();
    }

}
