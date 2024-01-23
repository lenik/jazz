package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.edu.CourseKitCategory;
import net.bodz.violet.edu.CourseKitCategorySamples;

public class CourseKitCategoryManagerTest
        extends AbstractManagerTest<CourseKitCategory, CourseKitCategoryMapper, CourseKitCategoryManager> {

    @Override
    public CourseKitCategory buildSample()
            throws Exception {
        CourseKitCategorySamples a = new CourseKitCategorySamples();
        return a.buildWired(tables);
    }

}
