package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.edu.CourseKitTag;
import net.bodz.violet.schema.edu.CourseKitTagSamples;

public class CourseKitTagManagerTest
        extends AbstractManagerTest<CourseKitTag, CourseKitTagMapper, CourseKitTagManager> {

    @Override
    public CourseKitTag buildSample()
            throws Exception {
        CourseKitTagSamples a = new CourseKitTagSamples();
        return a.buildWired(tables);
    }

}
