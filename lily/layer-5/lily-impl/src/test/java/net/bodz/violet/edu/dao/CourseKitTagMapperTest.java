package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.CourseKitTag;
import net.bodz.violet.edu.CourseKitTagSamples;

public class CourseKitTagMapperTest
        extends AbstractTableTest<CourseKitTag, CourseKitTagMapper> {

    @Override
    public CourseKitTag buildSample()
            throws Exception {
        CourseKitTagSamples a = new CourseKitTagSamples();
        return a.buildWired(tables);
    }

}
