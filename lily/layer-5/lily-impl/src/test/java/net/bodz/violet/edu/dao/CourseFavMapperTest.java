package net.bodz.violet.edu.dao;

import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.CourseFav;
import net.bodz.violet.edu.CourseFavSamples;

public class CourseFavMapperTest
        extends AbstractTableTest<CourseFav, CourseFavCriteriaBuilder, CourseFavMapper> {

    @Override
    public CourseFav buildSample()
            throws Exception {
        CourseFavSamples a = new CourseFavSamples();
        a.user = tables.pickAny(UserMapper.class, "user");
        a.course = tables.pickAny(CourseMapper.class, "course");
        return a.build();
    }

}
