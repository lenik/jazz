package net.bodz.violet.edu.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.CourseTag;
import net.bodz.violet.edu.CourseTagSamples;

public class CourseTagMapperTest
        extends AbstractTableTest<CourseTag, CourseTagCriteriaBuilder, CourseTagMapper> {

    @Override
    public CourseTag buildSample()
            throws Exception {
        CourseTagSamples a = new CourseTagSamples();
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.parent = tables.pickAny(CourseTagMapper.class, "coursetag");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
