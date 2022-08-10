package net.bodz.violet.edu.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.edu.CourseKitTag;
import net.bodz.violet.edu.CourseKitTagSamples;

public class CourseKitTagMapperTest
        extends AbstractMapperTest<CourseKitTag, CourseKitTagMask, CourseKitTagMapper> {

    @Override
    public CourseKitTag buildSample() {
        return CourseKitTagSamples.build();
    }

}
