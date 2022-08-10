package net.bodz.violet.edu.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.edu.CourseTag;
import net.bodz.violet.edu.CourseTagSamples;

public class CourseTagMapperTest
        extends AbstractMapperTest<CourseTag, CourseTagMask, CourseTagMapper> {

    @Override
    public CourseTag buildSample() {
        return CourseTagSamples.build();
    }

}
