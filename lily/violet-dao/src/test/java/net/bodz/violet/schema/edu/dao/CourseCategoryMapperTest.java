package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.edu.CourseCategory;
import net.bodz.violet.schema.edu.CourseCategorySamples;

public class CourseCategoryMapperTest
        extends AbstractTableTest<CourseCategory, CourseCategoryMapper> {

    @Override
    public CourseCategory buildSample()
            throws Exception {
        CourseCategorySamples a = new CourseCategorySamples();
        return a.buildWired(tables);
    }

}
