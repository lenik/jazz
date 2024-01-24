package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.edu.CourseKitFav;
import net.bodz.violet.schema.edu.CourseKitFavSamples;

public class CourseKitFavManagerTest
        extends AbstractManagerTest<CourseKitFav, CourseKitFavMapper, CourseKitFavManager> {

    @Override
    public CourseKitFav buildSample()
            throws Exception {
        CourseKitFavSamples a = new CourseKitFavSamples();
        return a.buildWired(tables);
    }

}
