package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.edu.CourseKitFav;
import net.bodz.violet.schema.edu.CourseKitFavSamples;

public class CourseKitFavMapperTest
        extends AbstractTableTest<CourseKitFav, CourseKitFavMapper> {

    @Override
    public CourseKitFav buildSample()
            throws Exception {
        CourseKitFavSamples a = new CourseKitFavSamples();
        return a.buildWired(tables);
    }

}
