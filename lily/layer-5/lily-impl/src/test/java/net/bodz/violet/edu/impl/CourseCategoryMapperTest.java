package net.bodz.violet.edu.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.edu.CourseCategory;
import net.bodz.violet.edu.CourseCategorySamples;

public class CourseCategoryMapperTest
        extends AbstractMapperTest<CourseCategory, CourseCategoryMask, CourseCategoryMapper> {

    @Override
    public CourseCategory buildSample() {
        return CourseCategorySamples.build();
    }

}
