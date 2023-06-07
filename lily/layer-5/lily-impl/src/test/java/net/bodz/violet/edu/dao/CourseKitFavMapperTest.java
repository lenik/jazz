package net.bodz.violet.edu.dao;

import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.CourseKitFav;
import net.bodz.violet.edu.CourseKitFavSamples;

public class CourseKitFavMapperTest
        extends AbstractTableTest<CourseKitFav, CourseKitFavMask, CourseKitFavMapper> {

    @Override
    public CourseKitFav buildSample()
            throws Exception {
        CourseKitFavSamples a = new CourseKitFavSamples();
        a.coursekit = tables.pickAny(CourseKitMapper.class, "coursekit");
        a.user = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
