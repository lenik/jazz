package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.edu.CourseKitTag;
import net.bodz.violet.schema.edu.CourseKitTagSamples;

public class CourseKitTagMapperTest
        extends AbstractTableTest<CourseKitTag, CourseKitTagMapper> {

    @Override
    public CourseKitTag buildSample()
            throws Exception {
        CourseKitTagSamples a = new CourseKitTagSamples();
        return a.buildWired(tables);
    }

}
