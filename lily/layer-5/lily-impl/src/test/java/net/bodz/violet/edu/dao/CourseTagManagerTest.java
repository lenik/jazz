package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.edu.CourseTag;
import net.bodz.violet.edu.CourseTagSamples;

public class CourseTagManagerTest
        extends AbstractManagerTest<CourseTag, CourseTagMapper, CourseTagManager> {

    @Override
    public CourseTag buildSample()
            throws Exception {
        CourseTagSamples a = new CourseTagSamples();
        return a.buildWired(tables);
    }

}
