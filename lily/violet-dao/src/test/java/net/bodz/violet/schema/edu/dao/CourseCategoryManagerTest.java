package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.edu.CourseCategory;
import net.bodz.violet.schema.edu.CourseCategorySamples;

public class CourseCategoryManagerTest
        extends AbstractManagerTest<CourseCategory, CourseCategoryMapper, CourseCategoryManager> {

    @Override
    public CourseCategory buildSample()
            throws Exception {
        CourseCategorySamples a = new CourseCategorySamples();
        return a.buildWired(tables);
    }

}
