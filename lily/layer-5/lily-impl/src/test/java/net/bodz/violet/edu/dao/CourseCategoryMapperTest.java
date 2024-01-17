package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.CourseCategory;
import net.bodz.violet.edu.CourseCategorySamples;

public class CourseCategoryMapperTest
        extends AbstractTableTest<CourseCategory, CourseCategoryMapper> {

    @Override
    public CourseCategory buildSample()
            throws Exception {
        CourseCategorySamples a = new CourseCategorySamples();
        return a.buildWired(tables);
    }

}
