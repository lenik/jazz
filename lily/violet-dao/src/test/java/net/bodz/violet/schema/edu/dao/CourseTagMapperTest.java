package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.edu.CourseTag;
import net.bodz.violet.schema.edu.CourseTagSamples;

public class CourseTagMapperTest
        extends AbstractTableTest<CourseTag, CourseTagMapper> {

    @Override
    public CourseTag buildSample()
            throws Exception {
        CourseTagSamples a = new CourseTagSamples();
        return a.buildWired(tables);
    }

}
