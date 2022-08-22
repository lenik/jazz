package net.bodz.violet.edu.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.CourseTag;
import net.bodz.violet.edu.CourseTagSamples;

public class CourseTagMapperTest
        extends AbstractTableTest<CourseTag, CourseTagMask, CourseTagMapper> {

    @Override
    public CourseTag buildSample() {
        return CourseTagSamples.build();
    }

}
