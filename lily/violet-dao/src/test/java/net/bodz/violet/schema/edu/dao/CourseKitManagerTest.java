package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.edu.CourseKit;
import net.bodz.violet.schema.edu.CourseKitSamples;

public class CourseKitManagerTest
        extends AbstractManagerTest<CourseKit, CourseKitMapper, CourseKitManager> {

    @Override
    public CourseKit buildSample()
            throws Exception {
        CourseKitSamples a = new CourseKitSamples();
        return a.buildWired(tables);
    }

}
