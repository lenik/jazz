package net.bodz.violet.edu.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.CourseCategory;
import net.bodz.violet.edu.CourseCategorySamples;

public class CourseCategoryMapperTest
        extends AbstractTableTest<CourseCategory, CourseCategoryMask, CourseCategoryMapper> {

    @Override
    public CourseCategory buildSample() {
        return CourseCategorySamples.build();
    }

}
