package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.CourseKitFav;
import net.bodz.violet.edu.CourseKitFavSamples;

public class CourseKitFavMapperTest
        extends AbstractTableTest<CourseKitFav, CourseKitFavMapper> {

    @Override
    public CourseKitFav buildSample()
            throws Exception {
        CourseKitFavSamples a = new CourseKitFavSamples();
        return a.buildWired(tables);
    }

}
