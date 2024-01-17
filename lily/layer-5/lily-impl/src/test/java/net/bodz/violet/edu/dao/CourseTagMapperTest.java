package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.CourseTag;
import net.bodz.violet.edu.CourseTagSamples;

public class CourseTagMapperTest
        extends AbstractTableTest<CourseTag, CourseTagMapper> {

    @Override
    public CourseTag buildSample()
            throws Exception {
        CourseTagSamples a = new CourseTagSamples();
        return a.buildWired(tables);
    }

}
