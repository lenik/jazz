package net.bodz.violet.edu.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.CourseKitTag;
import net.bodz.violet.edu.CourseKitTagSamples;

public class CourseKitTagMapperTest
        extends AbstractTableTest<CourseKitTag, CourseKitTagMask, CourseKitTagMapper> {

    @Override
    public CourseKitTag buildSample()
            throws Exception {
        CourseKitTagSamples a = new CourseKitTagSamples();
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.parent = tables.pickAny(CourseKitTagMapper.class, "coursekittag");
        return a.build();
    }

}
