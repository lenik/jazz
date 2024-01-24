package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.edu.CourseKit;
import net.bodz.violet.schema.edu.CourseKitSamples;

public class CourseKitMapperTest
        extends AbstractTableTest<CourseKit, CourseKitMapper> {

    @Override
    public CourseKit buildSample()
            throws Exception {
        CourseKitSamples a = new CourseKitSamples();
        return a.buildWired(tables);
    }

}
