package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.edu.CourseKit;
import net.bodz.violet.edu.CourseKitSamples;

public class CourseKitManagerTest
        extends AbstractManagerTest<CourseKit, CourseKitMapper, CourseKitManager> {

    @Override
    public CourseKit buildSample()
            throws Exception {
        CourseKitSamples a = new CourseKitSamples();
        return a.buildWired(tables);
    }

}
