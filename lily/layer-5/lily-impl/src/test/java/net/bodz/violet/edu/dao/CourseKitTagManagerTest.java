package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.edu.CourseKitTag;
import net.bodz.violet.edu.CourseKitTagSamples;

public class CourseKitTagManagerTest
        extends AbstractManagerTest<CourseKitTag, CourseKitTagMapper, CourseKitTagManager> {

    @Override
    public CourseKitTag buildSample()
            throws Exception {
        CourseKitTagSamples a = new CourseKitTagSamples();
        return a.buildWired(tables);
    }

}
